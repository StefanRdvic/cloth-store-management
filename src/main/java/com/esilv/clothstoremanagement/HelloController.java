package com.esilv.clothstoremanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

// todo : delete
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}