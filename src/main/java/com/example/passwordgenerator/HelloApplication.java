/*
    8 typow layout'u (ukladu)
    - GridPane, AnchorPane, StackPane, hbox, vbox, FlowPane, TilePane, BorderPane


GridPane jest układem opartym na siatce (grid), w którym elementy są rozmieszczane w określonych kolumnach i wierszach.
Każdy element w GridPane może zajmować jedno lub więcej pól siatki.
AnchorPane:

AnchorPane umożliwia przypisanie elementów do jednego lub więcej krawędzi (anchoring), dzięki czemu są one przytwierdzone do określonych pozycji w kontenerze.
Pozwala na łatwe ustalanie, gdzie elementy powinny być umieszczone w relacji do krawędzi kontenera.
StackPane:

StackPane to układ, w którym elementy są układane jeden na drugim, tworząc stos.
Elementy w StackPane są rozmieszczane w taki sposób, że jeden jest na wierzchu drugiego.
HBox (Horizontal Box):

HBox rozmieszcza elementy w poziomej linii.
Jest przydatny, gdy chcesz, aby elementy były ułożone obok siebie w jednym rzędzie.
VBox (Vertical Box):

VBox rozmieszcza elementy w pionowej linii.
Jest używany do ustawiania elementów w jednej kolumnie, jedno pod drugim.
FlowPane:

FlowPane układa elementy w przepływie, czyli w sposób dynamiczny dostosowujący się do dostępnego miejsca.
Jest używany, gdy chcesz, aby elementy ustawione były w linii, ale w razie potrzeby "przepływały" do kolejnego wiersza.
TilePane:

TilePane rozmieszcza elementy w formie "kafelków" (tiles), czyli w sposób siatkowy.
Elementy w TilePane są ustawiane w regularnych kolumnach i wierszach.
BorderPane:

BorderPane dzieli obszar na pięć regionów: górny, dolny, lewy, prawy i centralny.
Jest to przydatne, gdy chcesz, aby pewne elementy interfejsu użytkownika były rozmieszczone w określonych obszarach kontenera.

 */

package com.example.passwordgenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // zamiast w pliku hello-view.fxml okreslac wyglad ramki, mozemy tutaj w kodzie to zmodyfikowac i opisac

//        GridPane root = new GridPane();
//        root.setAlignment(Pos.CENTER);              // pozycja napisu
//        root.setVgap(10);
//        root.setHgap(10);
//
//        Label greeting = new Label("Welcome to the JavaFx!");           // etykieta wyswietlana
//        root.getChildren().add(greeting);                        // dodalismy kontrole nad powyzsza etykieta dla GridPane
//        greeting.setTextFill(Color.DARKGREEN);                   // kolor etykiety
//        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 60));        // czcionka, pogrubienie, rozmiar

        Scene scene = new Scene(fxmlLoader.load(), 720, 340);          // szer, wys
        stage.setTitle("Password Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}




















