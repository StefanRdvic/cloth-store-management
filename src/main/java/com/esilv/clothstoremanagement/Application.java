package com.esilv.clothstoremanagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the application launcher
 * author: Stefan Radovanovic
 * author: Yannick li
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMaxWidth(1000);
        stage.setMinWidth(1000);
        stage.setMinHeight(480);
        stage.setMaxHeight(480);
        stage.setResizable(false);
        stage.setTitle("Cloth store management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}