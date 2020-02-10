package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankKrwiApp extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));
        //Tworzenie odslony - sceny
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Bank Krwi");
        stage.show();

    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(BankKrwiApp.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
}
