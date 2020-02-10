package edu.ib;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class CentrumKrwiLoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField userFieldCentrum;

    @FXML
    private JFXPasswordField passwordFieldCentrum;

    @FXML
    private JFXButton loginButtonCentrum;

    @FXML
    private JFXButton cancelButtonCentrum;

    public JFXTextField getUserFieldCentrum() {
        return userFieldCentrum;
    }

    public JFXPasswordField getPasswordFieldCentrum() {
        return passwordFieldCentrum;
    }

    PreferencesCentrum preferencesCentrum;

    private DBUtil dbUtil;

    @FXML
    void cancelButtonCentrumClicked(ActionEvent event) throws IOException {
        //fxmlLoad("StartScene.fxml", "Bank Krwi", event);
        StartSceneController ssc = new StartSceneController();
        ssc.fxmlLoad("StartScene.fxml", "Bank Krwi", event);
    }

    @FXML
    public void loginButtonCentrumClicked(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        String uname = userFieldCentrum.getText();
        String pword = DigestUtils.sha1Hex(passwordFieldCentrum.getText());

        if (uname.equals(preferencesCentrum.getUsername()) && pword.equals(preferencesCentrum.getPassword())) {
            dbUtil = new DBUtil(userFieldCentrum.getText(), passwordFieldCentrum.getText());
            dbUtil.dbConnect();
            System.out.println("Jejeje");
            fxmlLoad("CentrumKrwiMain.fxml", "Centrum krwiodawstwa", 1200, 800, event);
        } else {
            userFieldCentrum.getStyleClass().add("wrong-credentials");
            passwordFieldCentrum.getStyleClass().add("wrong-credentials");
        }

    }

    void fxmlLoad(String fxml, String title, int width, int height, ActionEvent event) throws IOException {
        Parent login_parent = FXMLLoader.load(getClass().getResource("/fxml/" + fxml));
        Scene login_scene = new Scene(login_parent, width, height);
        Stage login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        login_stage.setScene(login_scene);
        login_stage.setTitle(title);
        login_stage.show();
    }


    @FXML
    void initialize() {
        PreferencesCentrum.initConfig();
        preferencesCentrum = PreferencesCentrum.getPreferences();

        assert userFieldCentrum != null : "fx:id=\"userFieldCentrum\" was not injected: check your FXML file 'CentrumKrwiLogin.fxml'.";
        assert passwordFieldCentrum != null : "fx:id=\"passwordFieldCentrum\" was not injected: check your FXML file 'CentrumKrwiLogin.fxml'.";
        assert loginButtonCentrum != null : "fx:id=\"loginButtonCentrum\" was not injected: check your FXML file 'CentrumKrwiLogin.fxml'.";
        assert cancelButtonCentrum != null : "fx:id=\"cancelButtonCentrum\" was not injected: check your FXML file 'CentrumKrwiLogin.fxml'.";

    }
}

