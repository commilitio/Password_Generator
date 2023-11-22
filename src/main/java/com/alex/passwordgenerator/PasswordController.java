package com.alex.passwordgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class PasswordController {
    @FXML
    public Label generatedPassword;
    @FXML
    private TextField textField;
    @FXML
    private Button generateButton;
    @FXML
    private CheckBox bigLettersCheckbox;
    @FXML
    private CheckBox numbersCheckbox;
    @FXML
    private CheckBox specialCharCheckbox;


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
        if (text.matches(".*[a-zA-Z]+.*") || text.matches(".*[^0-9]+.*")){
            JOptionPane.showMessageDialog(null, "Only integers allowed! (range 4-32)",
                    "bad input",JOptionPane.WARNING_MESSAGE);
            textField.clear();
            generateButton.setDisable(true);
        }
        else if (Integer.parseInt(text) < 4 || Integer.parseInt(text) > 32){
            JOptionPane.showMessageDialog(null, "Required password length is 4-32)",
                    "bad password length",JOptionPane.WARNING_MESSAGE);
            textField.clear();
            generateButton.setDisable(true);
        }
    }


    @FXML
    private void handleBigLettersCheckBox(){
        if (bigLettersCheckbox.isSelected()){
            // action on textField
        }
    }


    @FXML
    private void handleNumbersCheckBox(){
        if (numbersCheckbox.isSelected()){

        }
    }


    @FXML
    private void handleSpecialCharCheckBox(){
        if (specialCharCheckbox.isSelected()){

        }
    }



}























