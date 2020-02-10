package edu.ib;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CentrumKrwiMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FontAwesomeIconView closeSign;

    @FXML
    private TableView centrumKrwiodawstwaTable;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> donation_dayCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> PESELCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> nameCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> surnameCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> sexCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> dateBrthCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> placeBrthCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> adressCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, Integer> weightCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> lastDonationCol;

    @FXML
    private TableColumn<CentrumKrwiodawstwa, String> bloodTypeCol;

    @FXML
    private JFXComboBox<String> findComboBox;

    @FXML
    private JFXButton showAllButton;

    @FXML
    private JFXButton findButton;

    @FXML
    private JFXButton addDonorButton;

    @FXML
    private JFXTextField findTextField;

    private DBUtil dbUtil;
    private CentrumKrwiodawstwaDAO centrumKrwiodawstwaDAO;

    @FXML
    void addDonorButtonClicked(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CentrumKrwiDodawanieDawcy.fxml"));
        //Tworzenie odslony - sceny
        Scene scene = new Scene(root, 800, 800);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dodawanie dawcy");
        stage.show();

    }

    @FXML
    void closeSignClicked(MouseEvent event) throws IOException, SQLException {

        fxmlLoad("CentrumKrwiLogin.fxml", "Logowanie do centrum krwiodawstwa", event);
        dbUtil.dbDisconnect();
    }

    @FXML
    void findButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {

        String parameter = (String) findComboBox.getValue();
        String name = findTextField.getText();

        dbUtil = new DBUtil("centrumkrwiodawstwa", "krew1234");
        centrumKrwiodawstwaDAO = new CentrumKrwiodawstwaDAO(dbUtil);

        dbUtil.dbConnect();

        try {
            if(!name.equals(null)) {

                centrumKrwiodawstwaTable.getItems().clear();
                ObservableList<CentrumKrwiodawstwa> donorsData = centrumKrwiodawstwaDAO.searchParameters(parameter,name);
                populateDonors(donorsData);
            }
            else {
                findTextField.getStyleClass().add("wrong-credentials");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getting wines from DB.");
            throw e;
        }


    }

    @FXML
    void showAllButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {

//        CentrumKrwiLoginController loginController = new CentrumKrwiLoginController();
//        String user = loginController.getUserFieldCentrum().getText();
//        String password = loginController.getPasswordFieldCentrum().getText();

        dbUtil = new DBUtil("centrumkrwiodawstwa", "krew1234");
        centrumKrwiodawstwaDAO = new CentrumKrwiodawstwaDAO(dbUtil);

        dbUtil.dbConnect();
        try {

            centrumKrwiodawstwaTable.getItems().clear();
            ObservableList<CentrumKrwiodawstwa> donorsData = centrumKrwiodawstwaDAO.showAllDonors();
            populateDonors(donorsData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting wines from DB.");
            throw e;
        }

    }

    private void populateDonors(ObservableList<CentrumKrwiodawstwa> donorsData) {
        centrumKrwiodawstwaTable.setItems(donorsData);
    }

    void fxmlLoad(String fxml, String title, MouseEvent event) throws IOException {
        Parent login_parent = FXMLLoader.load(getClass().getResource("/fxml/" + fxml));
        Scene login_scene = new Scene(login_parent, 800, 600);
        Stage login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        login_stage.setScene(login_scene);
        login_stage.setTitle(title);
        login_stage.show();
    }

    @FXML
    void initialize() {
        assert closeSign != null : "fx:id=\"closeSign\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert centrumKrwiodawstwaTable != null : "fx:id=\"centrumKrwiodawstwaTable\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert donation_dayCol != null : "fx:id=\"donation_dayCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert PESELCol != null : "fx:id=\"PESELCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert surnameCol != null : "fx:id=\"surnameCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert sexCol != null : "fx:id=\"sexCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert dateBrthCol != null : "fx:id=\"dateBrthCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert placeBrthCol != null : "fx:id=\"placeBrthCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert adressCol != null : "fx:id=\"adressCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert weightCol != null : "fx:id=\"weightCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert lastDonationCol != null : "fx:id=\"lastDonationCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert bloodTypeCol != null : "fx:id=\"bloodTypeCol\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert findComboBox != null : "fx:id=\"findComboBox\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert showAllButton != null : "fx:id=\"showAllButton\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert findButton != null : "fx:id=\"findButton\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert addDonorButton != null : "fx:id=\"addDonorButton\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";
        assert findTextField != null : "fx:id=\"findTextField\" was not injected: check your FXML file 'CentrumKrwiMain.fxml'.";

        findComboBox.getItems().addAll("Data_pobrania", "PESEL", "Imie", "Nazwisko", "Plec", "Data_urodzenia", "Miejsce_urodzenia", "" +
                "Adres", "Waga", "Ostatnie_oddanie", "Grupa_krwi");
    }
}
