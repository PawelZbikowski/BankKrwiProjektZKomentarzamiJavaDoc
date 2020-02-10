package edu.ib;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

public class RegBankTypyKrwiController {

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
    private TableView bloodTypeTableView;

    @FXML
    private TableColumn<RegBankTypyKrwi, Integer> IDbloodColumnTable1;

    @FXML
    private TableColumn<RegBankTypyKrwi, String> dayOfDonationColumnTable1;

    @FXML
    private TableColumn<RegBankTypyKrwi, String> freshnessColumnTable1;

    @FXML
    private TableColumn<RegBankTypyKrwi, String> groupColumnTable1;

    @FXML
    private TableColumn<RegBankTypyKrwi, String> rHColumnTable1;

    @FXML
    private TableView archiveTableView;

    @FXML
    private TableColumn<RegBankArchiwa, String> archiveDateColumnTable2;

    @FXML
    private TableColumn<RegBankArchiwa, String> peselColumnTable2;

    @FXML
    private TableColumn<RegBankArchiwa, Integer> bloodIDColumnTable2;

    @FXML
    private JFXComboBox<String> bloodGroupComboBox;

    @FXML
    private JFXComboBox<String> rhComboBox;

    @FXML
    private JFXButton findBloodTypeButton;

    @FXML
    private JFXButton showArchiveButton;

    @FXML
    private FontAwesomeIconView closeSign;

    private DBUtil dbUtil;
    private RegBankTypyKrwiDAO regBankTypyKrwiDAO;

    private RegBankLoginController rblc;

    @FXML
    void closeSignClicked(MouseEvent event) throws IOException, SQLException {
        fxmlLoad("BankKrwiLogin.fxml", "Logowanie do regionalnego banku krwiodawstwa", event);
//        dbUtil.dbDisconnect();
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
    void findBloodTypeButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String bloodGroup = bloodGroupComboBox.getValue();
        String rH = rhComboBox.getValue();

        dbUtil = new DBUtil("regbank", "bank1234");
        regBankTypyKrwiDAO = new RegBankTypyKrwiDAO(dbUtil);

        dbUtil.dbConnect();

        try {
            if(!rH.equals(null) && !bloodGroup.equals(null)) {

                bloodTypeTableView.getItems().clear();
                ObservableList<RegBankTypyKrwi> donorsData = regBankTypyKrwiDAO.searchBloodType(bloodGroup, rH);
                populateDonors(donorsData);
            }
            else {
                bloodGroupComboBox.getStyleClass().add("wrong-credentials");
                rhComboBox.getStyleClass().add("wrong-credentials");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getting blood types from DB.");
            throw e;
        }
    }

    private void populateDonors(ObservableList<RegBankTypyKrwi> bloodTypeData) {
        bloodTypeTableView.setItems(bloodTypeData);
    }

    @FXML
    void goToPlacowkiButtonClicked(ActionEvent event) throws IOException {
        rblc = new RegBankLoginController();
        rblc.fxmlLoad("BankKrwiCenters.fxml", "Regionalny bank krwi", 1200, 800, event);
    }

    @FXML
    void goToTransfuzjeButtonClicked(ActionEvent event) throws IOException {
        RegBankLoginController rblc = new RegBankLoginController();
        rblc.fxmlLoad("BankKrwiTransfusion.fxml", "Regionalny bank krwi", 1200, 800, event);
    }

    @FXML
    void showArchiveButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        dbUtil = new DBUtil("regbank", "bank1234");
        regBankTypyKrwiDAO = new RegBankTypyKrwiDAO(dbUtil);

        dbUtil.dbConnect();
        try {

            archiveTableView.getItems().clear();
            ObservableList<RegBankArchiwa> archiveData = regBankTypyKrwiDAO.showArchives();
            populateDonorsArchives(archiveData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting archives from DB.");
            throw e;
        }
    }

    private void populateDonorsArchives(ObservableList<RegBankArchiwa> archivesData) {
        archiveTableView.setItems(archivesData);
    }

    @FXML
    void initialize() {
        assert goToGrupaKrwiButton != null : "fx:id=\"goToGrupaKrwiButton\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert goToPlacowkiButton != null : "fx:id=\"goToPlacowkiButton\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert goToTransfuzjeButton != null : "fx:id=\"goToTransfuzjeButton\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert bloodTypeTableView != null : "fx:id=\"bloodTypeTableView\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert IDbloodColumnTable1 != null : "fx:id=\"IDbloodColumnTable1\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert dayOfDonationColumnTable1 != null : "fx:id=\"dayOfDonationColumnTable1\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert freshnessColumnTable1 != null : "fx:id=\"freshnessColumnTable1\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert groupColumnTable1 != null : "fx:id=\"groupColumnTable1\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert rHColumnTable1 != null : "fx:id=\"rHColumnTable1\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert archiveTableView != null : "fx:id=\"archiveTableView\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert archiveDateColumnTable2 != null : "fx:id=\"archiveDateColumnTable2\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert peselColumnTable2 != null : "fx:id=\"peselColumnTable2\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert bloodIDColumnTable2 != null : "fx:id=\"bloodIDColumnTable2\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert bloodGroupComboBox != null : "fx:id=\"bloodGroupComboBox\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert rhComboBox != null : "fx:id=\"rhComboBox\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert findBloodTypeButton != null : "fx:id=\"findBloodTypeButton\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";
        assert showArchiveButton != null : "fx:id=\"showArchiveButton\" was not injected: check your FXML file 'BankKrwiMain.fxml'.";

        bloodGroupComboBox.getItems().addAll("0", "A", "B", "AB");
        rhComboBox.getItems().addAll("-", "+");
    }
}

