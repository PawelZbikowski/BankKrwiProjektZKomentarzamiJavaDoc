package edu.ib;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class RegBankTransfuzjeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton goToGrupaKrwiButton;

    @FXML
    private JFXButton goToPlacowkiButton;

    @FXML
    private JFXButton goToTransfuzjeButton;

    @FXML
    private JFXComboBox<String> centerComboBox;

    @FXML
    private JFXComboBox<String> bloodTypeComboBox;

    @FXML
    private JFXButton callTransfusion;

    @FXML
    private FontAwesomeIconView closeSign;

    @FXML
    private TableView centerTableView;

    @FXML
    private TableColumn<RegBankPlacowki, Integer> bloodIDColumn;

    @FXML
    private TableColumn<RegBankPlacowki, Integer> amountOfBloodUnitsColumn;

    @FXML
    private TableColumn<RegBankPlacowki, String> bloodTypeColumn;

    @FXML
    private TableColumn<RegBankPlacowki, String> centerNameColumn;

    @FXML
    private TableColumn<RegBankPlacowki, String> howManyBloodUnitsColumn;

    @FXML
    private Label gitLabel;

    private DBUtil dbUtil;
    private RegBankTypyKrwiDAO regBankTypyKrwiDAO;

    @FXML
    void callTransfusionClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        dbUtil = new DBUtil("regbank", "bank1234");
        regBankTypyKrwiDAO = new RegBankTypyKrwiDAO(dbUtil);

        dbUtil.dbConnect();

        String center = centerComboBox.getValue();
        String bloodType = bloodTypeComboBox.getValue();

        try {

            if (!center.equals(null) && !bloodType.equals(null)) {

                regBankTypyKrwiDAO.transfusion(center, bloodType);
                //showAllButtonClicked(event);
            } else {
//                warningLabel.setText("Proszę poprawnie uzupełnić wszystkie pola!");
//                warningLabel.getStyleClass().add("wrong-label");
                gitLabel.setVisible(false);
                gitLabel.setText("Niepowodzenie");
            }
            gitLabel.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Error occurred while calling procedure pobranie_do_transfuzji.");
            throw e;
        }

    }

    @FXML
    void closeSignClicked(MouseEvent event) throws IOException {
        RegBankTypyKrwiController rbtkc = new RegBankTypyKrwiController();
        rbtkc.fxmlLoad("BankKrwiLogin.fxml", "Logowanie do regionalnego banku krwiodawstwa", event);
    }

    @FXML
    void goToGrupaKrwiButtonClicked(ActionEvent event) throws IOException {
        RegBankLoginController rblc = new RegBankLoginController();
        rblc.fxmlLoad("BankKrwiMain.fxml", "Regionalny bank krwi", 1200, 800, event);
    }

    @FXML
    void goToPlacowkiButtonClicked(ActionEvent event) throws IOException {
        RegBankLoginController rblc = new RegBankLoginController();
        rblc.fxmlLoad("BankKrwiCenters.fxml", "Regionalny bank krwi", 1200, 800, event);
    }

    @FXML
    void initialize() {
        assert goToGrupaKrwiButton != null : "fx:id=\"goToGrupaKrwiButton\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert goToPlacowkiButton != null : "fx:id=\"goToPlacowkiButton\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert goToTransfuzjeButton != null : "fx:id=\"goToTransfuzjeButton\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert centerComboBox != null : "fx:id=\"centerComboBox\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert bloodTypeComboBox != null : "fx:id=\"bloodTypeComboBox\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert callTransfusion != null : "fx:id=\"callTransfusion\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert closeSign != null : "fx:id=\"closeSign\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert centerTableView != null : "fx:id=\"centerTableView\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert bloodIDColumn != null : "fx:id=\"bloodIDColumn\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert amountOfBloodUnitsColumn != null : "fx:id=\"amountOfBloodUnitsColumn\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert bloodTypeColumn != null : "fx:id=\"bloodTypeColumn\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert centerNameColumn != null : "fx:id=\"centerNameColumn\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";
        assert howManyBloodUnitsColumn != null : "fx:id=\"howManyBloodUnitsColumn\" was not injected: check your FXML file 'BankKrwiTransfusion.fxml'.";

        centerComboBox.getItems().addAll("RCKiK Białystok", "RCKiK Bydgoszcz", "RCKiK Gdańsk", "RCKiK Kalisz", "RCKiK Katowice", "RCKiK Kielce", "RCKiK Kraków",
                "RCKiK Lublin", "RCKiK Łódz", "RCKiK Olsztyn", "RCKiK Opole", "RCKiK Poznań", "RCKiK Racibórz", "RCKiK Radom", "RCKiK Rzeszów", "RCKiK Słupsk", "RCKiK Szczecin",
                "RCKiK Wałbrzych", "RCKiK Warszawa", "RCKiK Wrocław", "RCKiK Zielona Góra");

        bloodTypeComboBox.getItems().addAll("0-", "0+", "A-", "A+", "B-", "B+", "AB-", "AB+");
    }
}
