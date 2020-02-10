package edu.ib;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class RegBankPlacowkiController {

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
    private JFXComboBox<String> centerComboBox;

    @FXML
    private JFXButton findCentersButton;

    @FXML
    private FontAwesomeIconView closeSign;

    private DBUtil dbUtil;
    private RegBankTypyKrwiDAO regBankTypyKrwiDAO;

    private RegBankLoginController rblc;

    @FXML
    void closeSignClicked(MouseEvent event) throws IOException, SQLException {
        RegBankTypyKrwiController rbtkc = new RegBankTypyKrwiController();
        rbtkc.fxmlLoad("BankKrwiLogin.fxml", "Logowanie do regionalnego banku krwiodawstwa", event);
//        dbUtil.dbDisconnect();
    }

    @FXML
    void findCentersButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String center = centerComboBox.getValue();

        dbUtil = new DBUtil("regbank", "bank1234");
        regBankTypyKrwiDAO = new RegBankTypyKrwiDAO(dbUtil);

        dbUtil.dbConnect();

        try {
            if(!center.equals(null)) {

                centerTableView.getItems().clear();
                ObservableList<RegBankPlacowki> centersData = regBankTypyKrwiDAO.searchCenter(center);
                populateCenters(centersData);
            }
            else {
                centerComboBox.getStyleClass().add("wrong-credentials");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getting centers from DB.");
            throw e;
        }
    }

    private void populateCenters(ObservableList<RegBankPlacowki> centersData) {
        centerTableView.setItems(centersData);
    }

    @FXML
    void goToGrupaKrwiButtonClicked(ActionEvent event) throws IOException {
        rblc = new RegBankLoginController();
        rblc.fxmlLoad("BankKrwiMain.fxml", "Regionalny bank krwi", 1200, 800, event);
    }

    @FXML
    void goToTransfuzjeButtonClicked(ActionEvent event) throws IOException {
        RegBankLoginController rblc = new RegBankLoginController();
        rblc.fxmlLoad("BankKrwiTransfusion.fxml", "Regionalny bank krwi", 1200, 800, event);
    }

    @FXML
    void initialize() {
        assert goToGrupaKrwiButton != null : "fx:id=\"goToGrupaKrwiButton\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert goToPlacowkiButton != null : "fx:id=\"goToPlacowkiButton\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert goToTransfuzjeButton != null : "fx:id=\"goToTransfuzjeButton\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert centerTableView != null : "fx:id=\"centerTableView\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert bloodIDColumn != null : "fx:id=\"bloodIDColumn\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert amountOfBloodUnitsColumn != null : "fx:id=\"amountOfBloodUnitsColumn\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert bloodTypeColumn != null : "fx:id=\"bloodTypeColumn\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert centerNameColumn != null : "fx:id=\"centerNameColumn\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert howManyBloodUnitsColumn != null : "fx:id=\"howManyBloodUnitsColumn\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert centerComboBox != null : "fx:id=\"centerComboBox\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert findCentersButton != null : "fx:id=\"findCentersButton\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";
        assert closeSign != null : "fx:id=\"closeSign\" was not injected: check your FXML file 'BankKrwiCenters.fxml'.";

        centerComboBox.getItems().addAll("RCKiK Białystok", "RCKiK Bydgoszcz", "RCKiK Gdańsk", "RCKiK Kalisz", "RCKiK Katowice", "RCKiK Kielce", "RCKiK Kraków",
                "RCKiK Lublin", "RCKiK Łódz", "RCKiK Olsztyn", "RCKiK Opole", "RCKiK Poznań", "RCKiK Racibórz", "RCKiK Radom", "RCKiK Rzeszów", "RCKiK Słupsk", "RCKiK Szczecin",
                "RCKiK Wałbrzych", "RCKiK Warszawa", "RCKiK Wrocław", "RCKiK Zielona Góra");
    }
}

