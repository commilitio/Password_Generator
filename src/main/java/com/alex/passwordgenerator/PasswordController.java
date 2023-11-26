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
    public void initialize(){               // przy uruchamianiu apki juz ustawia przycisk na niedostepny
        generateButton.setDisable(true);
        savePasswordButton.setDisable(true);
        copyPasswordButton.setDisable(true);
    }


    @FXML
    private void handleTextFieldClick() {
        textField.clear();                      // czysci zawartosc pola textField
    }


    @FXML
    private void handleExitButtonClick(){
        System.exit(1);
    }



    private boolean isPasswordWeak() {
        return !bigLettersCheckbox.isSelected() && !numbersCheckbox.isSelected() && !specialCharCheckbox.isSelected() ||
                password.length() <= 6 ||
                !bigLettersCheckbox.isSelected() && password.length() <= 8 ||
                !numbersCheckbox.isSelected() && password.length() <= 8 ||
                !specialCharCheckbox.isSelected() && password.length() <= 8;
    }


    private boolean isPasswordMedium(){
        return !bigLettersCheckbox.isSelected() && password.length() <= 12 ||
                !numbersCheckbox.isSelected() && password.length() <= 12 ||
                !specialCharCheckbox.isSelected() && password.length() <= 12 ||
                bigLettersCheckbox.isSelected() && !numbersCheckbox.isSelected() && !specialCharCheckbox.isSelected() &&
                        password.length() >= 12 ||
                !bigLettersCheckbox.isSelected() && numbersCheckbox.isSelected() && !specialCharCheckbox.isSelected() &&
                        password.length() >= 12 ||
                !bigLettersCheckbox.isSelected() && !numbersCheckbox.isSelected() && specialCharCheckbox.isSelected() &&
                        password.length() >= 12;
    }

    private boolean isPasswordStrong(){
        return bigLettersCheckbox.isSelected() && numbersCheckbox.isSelected() && specialCharCheckbox.isSelected() &&
                password.length() > 8 ||
                bigLettersCheckbox.isSelected() && numbersCheckbox.isSelected() && password.length() > 12 ||
                bigLettersCheckbox.isSelected() && specialCharCheckbox.isSelected() && password.length() > 12 ||
                numbersCheckbox.isSelected() && specialCharCheckbox.isSelected() && password.length() > 12;
    }



    @FXML
    private void handleSavePasswordButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text files (.txt)", "*.txt");     // filtr dla konkretnego typu plikow (descritpion, extension)
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showSaveDialog(new Stage());        // wyswietla okno dialogowe wyboru plikow

        try {                                                               // zapis hasla do pliku
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
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();     // pobiera schowek systemowy
        StringSelection selection = new StringSelection(password);          // pozwala na przechowywanie tekstu w formie Transferable
        clipboard.setContents(selection, null);                         // ustawia zawartosc schowka - Transferable required
    }


    @FXML
    private void handleKeyReleased() {
        String text = textField.getText();                              // pobiera zawartosc pola textField
        boolean disableButton = text.isEmpty() || text.trim().isEmpty();        // disableButton = true jesli brak tekstu w textField (trim usuwa biale znaki- spacje, tab)
        generateButton.setDisable(disableButton);                       // setDisable = true jesli disableButton = true
        if (text.matches(".*[a-zA-Z]+.*") || text.matches(".*[^0-9]+.*")){
            JOptionPane.showMessageDialog(null, "Only integers allowed!",
                    "bad input",JOptionPane.WARNING_MESSAGE);
            textField.clear();
            generateButton.setDisable(true);
        }
        else if (Integer.parseInt(text) > 32){
            JOptionPane.showMessageDialog(null, "Password length limit is 32",
                    "bad password length",JOptionPane.WARNING_MESSAGE);
            textField.clear();
            generateButton.setDisable(true);
        }
    }
    /*
        .* - Oznacza dowolny ciąg znaków (w tym żaden lub wiele znaków przed i po interesującym nas wzorcu).
        [a-zA-Z] - Oznacza zakres znaków, których oczekujemy. W tym przypadku to litery od 'a' do 'z' i od 'A' do 'Z'.
        [^0-9] - Oznacza negację zakresu znaków. To oznacza, że szukamy znaku, który nie należy do zakresu cyfr od '0' do '9'.
        + - Oznacza, że musi wystąpić co najmniej jeden znak zdefiniowany w zakresie [a-zA-Z]
     */



/*
    Klasa StringBuilder w języku Java służy do dynamicznego tworzenia i modyfikowania ciągów znaków
    (łańcuchów). Jedną z głównych cech StringBuilder jest to, że jest ona mutowalna, co oznacza, że można
    modyfikować zawartość ciągu znaków, dodając, usuwając lub zmieniając znaki, bez konieczności tworzenia
    nowych obiektów, co jest efektywne pod względem pamięci i czasu wykonania.
    Warto używać StringBuilder szczególnie w sytuacjach, gdzie zachodzi potrzeba dynamicznego modyfikowania
    ciągów znaków, takich jak budowanie długich napisów czy tworzenie złożonych ciągów znaków w pętlach.

    SecureRandom zapewnia kryptograficznie bezpieczne losowanie, co jest ważne w zastosowaniach, gdzie
    istnieje wymaganie dotyczące wysokiego poziomu losowości, takich jak generowanie kluczy
    kryptograficznych, tokenów uwierzytelniających, czy haseł.
 */
    @FXML
    private void handleGenerateButtonClick(){
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = lowerCase.toUpperCase();
        String digits = "0123456789";
        String specialChar = "!@#$%^&*()-_=+[{]};:'\'',<.>/?|`~";
        int passwordLength = Integer.parseInt(textField.getText());     // pobieramy i zapisujemy dlugosc hasla

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


        generatedPassword.setText("password: \n" + String.valueOf(password));       // wysylamy do Label
        this.password = password.toString();                                        // zapisuje haslo w polu klasy
        savePasswordButton.setDisable(false);
        copyPasswordButton.setDisable(false);
    }
}
























