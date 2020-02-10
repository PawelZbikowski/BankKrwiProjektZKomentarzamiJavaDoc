package edu.ib;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class RegBankLoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField passwordFieldBank;

    @FXML
    private JFXButton loginButtonBank;

    @FXML
    private JFXButton cancelButtonBank;

    @FXML
    private JFXTextField userFieldBank;

    PreferencesRegBank preferencesRegBank;

    private DBUtil dbUtil;

    @FXML
    void cancelButtonCentrumBank(ActionEvent event) throws IOException {
        StartSceneController ssc2 = new StartSceneController();
        ssc2.fxmlLoad("StartScene.fxml", "Bank Krwi", event);
        //fxmlLoad("StartScene.fxml", "Bank Krwi", event);
    }

    @FXML
    void loginButtonBankClicked(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String uname = userFieldBank.getText();
        String pword = DigestUtils.sha1Hex(passwordFieldBank.getText());

        if (uname.equals(preferencesRegBank.getUsername()) && pword.equals(preferencesRegBank.getPassword())) {
            dbUtil = new DBUtil(userFieldBank.getText(), passwordFieldBank.getText());
            dbUtil.dbConnect();
            System.out.println("Jejeje");
            fxmlLoad("BankKrwiMain.fxml", "Regionalny bank krwi", 1200, 800, event);
        } else {
            userFieldBank.getStyleClass().add("wrong-credentials");
            passwordFieldBank.getStyleClass().add("wrong-credentials");
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
        PreferencesRegBank.initConfig();
        preferencesRegBank = PreferencesRegBank.getPreferences();

        assert passwordFieldBank != null : "fx:id=\"passwordFieldBank\" was not injected: check your FXML file 'BankKrwiLogin.fxml'.";
        assert loginButtonBank != null : "fx:id=\"loginButtonBank\" was not injected: check your FXML file 'BankKrwiLogin.fxml'.";
        assert cancelButtonBank != null : "fx:id=\"cancelButtonBank\" was not injected: check your FXML file 'BankKrwiLogin.fxml'.";
        assert userFieldBank != null : "fx:id=\"userFieldBank\" was not injected: check your FXML file 'BankKrwiLogin.fxml'.";

    }
}
