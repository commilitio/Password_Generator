package com.example.passwordgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PasswordController {
    @FXML
    private TextField textField;
    @FXML
    private Button generateButton;

    @FXML
    public void initialize(){               // przy uruchamianiu apki juz ustawia przycisk na niedostepny
        generateButton.setDisable(true);
    }


    @FXML
    private void handleTextFieldClick() {
        textField.clear();                      // czysci zawartosc pola textField
    }

    @FXML
    private void handleKeyReleased() {
        String text = textField.getText();                              // pobiera zawartosc pola textField
        boolean disableButton = text.isEmpty() || text.trim().isEmpty();        // disableButton = true jesli brak tekstu w textField (trim usuwa biale znaki- spacje, tab)
        generateButton.setDisable(disableButton);                       // setDisable = true jesli disableButton = true

    }
}























