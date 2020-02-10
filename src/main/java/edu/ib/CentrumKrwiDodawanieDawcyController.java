package edu.ib;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CentrumKrwiDodawanieDawcyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FontAwesomeIconView closeSign;

    @FXML
    private JFXTextField peselTextField;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextField surnameTextField;

    @FXML
    private JFXComboBox<String> sexComboBox;

    @FXML
    private JFXTextField cityTextField;

    @FXML
    private JFXTextField adressTextField;

    @FXML
    private JFXTextField weightTextField;

    @FXML
    private DatePicker lastDonationDatePicker;

    @FXML
    private DatePicker dayOfDonationDatePicker;

    @FXML
    private JFXComboBox<String> centerNameComboBox;

    @FXML
    private JFXComboBox<String> bloodTypeComboBox;

    @FXML
    private JFXButton addDonorButton;

    @FXML
    private Label warningLabel;

    @FXML
    private Label acceptLabel;

    private DBUtil dbUtil;
    private CentrumKrwiodawstwaDAO centrumKrwiodawstwaDAO;


    @FXML
    void addDonorButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil("centrumkrwiodawstwa", "krew1234");
        centrumKrwiodawstwaDAO = new CentrumKrwiodawstwaDAO(dbUtil);

        dbUtil.dbConnect();

        String pesel = peselTextField.getText();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String sex = sexComboBox.getValue();
        String placeOfBirth = cityTextField.getText();
        String address = adressTextField.getText();
        String weight = weightTextField.getText();
        String lastDonation = null;
        String dayOfDonation = null;
        try {
            lastDonation = lastDonationDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (NullPointerException ex) {

        }
        try {
            dayOfDonation = dayOfDonationDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (NullPointerException ex) {

        }
        //String lastDonation = lastDonationDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //String dayOfDonation = dayOfDonationDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String center = centerNameComboBox.getValue();
        String bloodType = bloodTypeComboBox.getValue();

        try {

            if (!pesel.equals(null) && pesel.length() == 11 && !name.equals(null) && !surname.equals(null) && !sex.equals(null) && !placeOfBirth.equals(null) &&
                    !address.equals(null) && !weight.equals(null) && !lastDonation.equals(null) && !dayOfDonation.equals(null) &&
                    !center.equals(null) && !bloodType.equals(null)) {

                int weightInt = Integer.parseInt(weight);
                String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
                String capSurname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
                centrumKrwiodawstwaDAO.insertDonor(pesel, capName, capSurname, sex, placeOfBirth, address, weightInt, lastDonation, dayOfDonation, center, bloodType);
                warningLabel.setVisible(false);
                acceptLabel.setText("Dawca " +  capName + " " + capSurname + " został(a) pomyślnie dodany/a");
                acceptLabel.setVisible(true);
                //showAllButtonClicked(event);
            } else {
//                warningLabel.setText("Proszę poprawnie uzupełnić wszystkie pola!");
//                warningLabel.getStyleClass().add("wrong-label");
                acceptLabel.setVisible(false);
                warningLabel.setVisible(true);
            }

        } catch (SQLException e) {
            System.out.println("Error occurred while calling procedure nowa_osoba_data_pobrania.");
            throw e;
        }
    }

    @FXML
    void closeSignClicked(MouseEvent event) {
        ((Stage)nameTextField.getScene().getWindow()).close();
    }

    @FXML
    void peselKeyTyped(KeyEvent event) {
        peselTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,11}?")) {
                    peselTextField.setText(oldValue);
                }

            }
        });
    }

    @FXML
    void weightKeyTyped(KeyEvent event) {
        weightTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    weightTextField.setText(oldValue);
                }

            }
        });

    }

    @FXML
    void initialize() {
        assert closeSign != null : "fx:id=\"closeSign\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert peselTextField != null : "fx:id=\"peselTextField\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert surnameTextField != null : "fx:id=\"surnameTextField\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert sexComboBox != null : "fx:id=\"sexComboBox\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert cityTextField != null : "fx:id=\"cityTextField\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert adressTextField != null : "fx:id=\"adressTextField\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert weightTextField != null : "fx:id=\"weightTextField\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert lastDonationDatePicker != null : "fx:id=\"lastDonationDatePicker\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert dayOfDonationDatePicker != null : "fx:id=\"dayOfDonationDatePicker\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert centerNameComboBox != null : "fx:id=\"centerNameComboBox\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert bloodTypeComboBox != null : "fx:id=\"bloodTypeComboBox\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";
        assert addDonorButton != null : "fx:id=\"addDonorButton\" was not injected: check your FXML file 'CentrumKrwiDodawanieDawcy.fxml'.";

        sexComboBox.getItems().addAll("F", "M");
        centerNameComboBox.getItems().addAll("RCKiK Białystok", "RCKiK Bydgoszcz", "RCKiK Gdańsk", "RCKiK Kalisz", "RCKiK Katowice", "RCKiK Kielce", "RCKiK Kraków",
                "RCKiK Lublin", "RCKiK Łódz", "RCKiK Olsztyn", "RCKiK Opole", "RCKiK Poznań", "RCKiK Racibórz", "RCKiK Radom", "RCKiK Rzeszów", "RCKiK Słupsk", "RCKiK Szczecin",
                "RCKiK Wałbrzych", "RCKiK Warszawa", "RCKiK Wrocław", "RCKiK Zielona Góra");
        bloodTypeComboBox.getItems().addAll("0-", "0+", "A-", "A+", "B-", "B+", "AB-", "AB+");
    }
}
