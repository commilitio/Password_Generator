package com.alex.passwordgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class PasswordController {
    @FXML
    public Label generatedPassword;
    @FXML
    public Label passwordStrength;
    @FXML
    private TextField textField;
    @FXML
    private Button generateButton;
    @FXML
    private Button savePasswordButton;
    @FXML
    private Button copyPasswordButton;
    @FXML
    private CheckBox bigLettersCheckbox;
    @FXML
    private CheckBox numbersCheckbox;
    @FXML
    private CheckBox specialCharCheckbox;
    private String password;

    @FXML
    public void initialize(){               // sets buttons invisible just from the beggining
        generateButton.setDisable(true);
        savePasswordButton.setDisable(true);
        copyPasswordButton.setDisable(true);
    }


    @FXML
    private void handleTextFieldClick() {       // main role: clear TextField with password length when clicked on
        textField.clear();
        generateButton.setDisable(true);
        savePasswordButton.setDisable(true);
        copyPasswordButton.setDisable(true);
        generatedPassword.setVisible(false);
        passwordStrength.setVisible(false);
    }


    @FXML
    private void handleExitButtonClick(){
        System.exit(1);
    }



    private boolean isPasswordWeak() {                              // passwordStrength Label criterias
        boolean noBigLetters = !bigLettersCheckbox.isSelected();
        boolean noDigits = !numbersCheckbox.isSelected();
        boolean noSpecialChar = !specialCharCheckbox.isSelected();

        return noBigLetters && noDigits && noSpecialChar ||
                noBigLetters && noDigits && !noSpecialChar && password.length() <= 12 ||
                noBigLetters && !noDigits && noSpecialChar && password.length() <= 12 ||
                !noBigLetters && noDigits && noSpecialChar && password.length() <= 12 ||
                noBigLetters && !noDigits && !noSpecialChar && password.length() <= 8 ||
                !noBigLetters && noDigits && !noSpecialChar && password.length() <= 8 ||
                !noBigLetters && !noDigits && noSpecialChar && password.length() <= 8 ||
                !noBigLetters && !noDigits && !noSpecialChar && password.length() < 6;
    }


    private boolean isPasswordMedium(){
        boolean noBigLetters = !bigLettersCheckbox.isSelected();
        boolean noDigits = !numbersCheckbox.isSelected();
        boolean noSpecialChar = !specialCharCheckbox.isSelected();

        return noBigLetters && noDigits && !noSpecialChar && password.length() > 12 && password.length() <= 20 ||
                noBigLetters && !noDigits && noSpecialChar && password.length() > 12 && password.length() <= 20||
                !noBigLetters && noDigits && noSpecialChar && password.length() > 12 && password.length() <= 20||
                noBigLetters && !noDigits && !noSpecialChar && password.length() > 8 && password.length() <= 12||
                !noBigLetters && noDigits && !noSpecialChar && password.length() > 8 && password.length() <= 12||
                !noBigLetters && !noDigits && noSpecialChar && password.length() > 8 && password.length() <= 12||
                !noBigLetters && !noDigits && !noSpecialChar && password.length() >= 6 && password.length() < 10;
    }

    private boolean isPasswordStrong(){
        boolean noBigLetters = !bigLettersCheckbox.isSelected();
        boolean noDigits = !numbersCheckbox.isSelected();
        boolean noSpecialChar = !specialCharCheckbox.isSelected();

        return noBigLetters && noDigits && !noSpecialChar && password.length() > 20 ||
                noBigLetters && !noDigits && noSpecialChar && password.length() > 20 ||
                !noBigLetters && noDigits && noSpecialChar && password.length() > 20 ||
                noBigLetters && !noDigits && !noSpecialChar && password.length() > 12 ||
                !noBigLetters && noDigits && !noSpecialChar && password.length() > 12 ||
                !noBigLetters && !noDigits && noSpecialChar && password.length() > 12 ||
                !noBigLetters && !noDigits && !noSpecialChar && password.length() >= 10;
    }



    @FXML
    private void handleSavePasswordButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text files (.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showSaveDialog(new Stage());

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
            writer.write(password);
            writer.close();
        }
        catch (IIOException e){
            e.getMessage();
        }
    }


    @FXML
    private void handleCopyPasswordButtonClick(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(password);
        clipboard.setContents(selection, null);
    }


    @FXML
    private void handleKeyReleased() {              // checks whether password conditions have been met
        String text = textField.getText();
        boolean disableButton = text.isEmpty() || text.trim().isEmpty();
        generateButton.setDisable(disableButton);
        if (text.matches(".*[a-zA-Z]+.*") || text.matches(".*[^1-9]+.*")){
            JOptionPane.showMessageDialog(null, "Only integers allowed!",
                    "Bad Input",JOptionPane.WARNING_MESSAGE);
            textField.clear();
            generateButton.setDisable(true);
        }
        else if (Integer.parseInt(text) > 32){
            JOptionPane.showMessageDialog(null, "Password length limit is 32",
                    "Bad Password Length",JOptionPane.WARNING_MESSAGE);
            textField.clear();
            generateButton.setDisable(true);
        }
    }



    @FXML
    private void handleGenerateButtonClick(){
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = lowerCase.toUpperCase();
        String digits = "0123456789";
        String specialChar = "!@#$%^&*()-_=+[{]};:'\'',<.>/?|`~";

        int passwordLength = Integer.parseInt(textField.getText());

        if (bigLettersCheckbox.isSelected())
            lowerCase += upperCase;
        if (numbersCheckbox.isSelected())
            lowerCase += digits;
        if (specialCharCheckbox.isSelected())
            lowerCase += specialChar;

        for (int i = 0; i < passwordLength; i++) {
            char randomChar = lowerCase.charAt(random.nextInt(lowerCase.length()));
            password.append(randomChar);
        }

        Paint backgroundPaint = null;

        if (isPasswordWeak()) {
            passwordStrength.setText("Password strength: weak");
            backgroundPaint = Color.LIGHTPINK;
        } else if (isPasswordMedium()) {
            passwordStrength.setText("Password strength: medium");
            backgroundPaint = Color.LIGHTYELLOW;
        } else if (isPasswordStrong()) {
            passwordStrength.setText("Password strength: strong");
            backgroundPaint = Color.LIGHTGREEN;
        }
        BackgroundFill backgroundFill = new BackgroundFill(backgroundPaint, null, null);
        Background background = new Background(backgroundFill);
        passwordStrength.setBackground(background);


        generatedPassword.setText("password: \n" + String.valueOf(password));       // shows generated password on label
        this.password = password.toString();                                        // saves generated password to the class field
        savePasswordButton.setDisable(false);
        copyPasswordButton.setDisable(false);
        generatedPassword.setVisible(true);
        passwordStrength.setVisible(true);
    }
}
























