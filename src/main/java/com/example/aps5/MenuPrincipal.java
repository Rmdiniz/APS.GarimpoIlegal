package com.example.aps5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuPrincipal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Criação dos botões
        Button jogarQuizBtn = new Button("Jogar Quiz");
        Button aumentoAnosBtn = new Button("Veja o Aumento Pelos Anos");
        Button calculadoraBtn = new Button("Calcule o Impacto do Garimpo");
        Button verMaisBtn = new Button("Sobre o Garimpo");

        // Botão da calculadora leva a uma página externa
        calculadoraBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI("https://miningcalculator.conservation-strategy.org/calculator"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Botão de verMais leva a uma página externa
        verMaisBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI("https://brasilescola.uol.com.br/geografia/garimpo.htm#:~:text=O%20ouro%20%C3%A9%20o%20principal,%2C%20quartzo%2C%20mica%20e%20feldspato."));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Botão do Quiz leva a janela do Quiz
        jogarQuizBtn.setOnAction(event -> {
            Quiz quiz = new Quiz();
            quiz.start(new Stage());
            primaryStage.close();
        });

        // Botão dos Anos leva a janela do aumento anual
        aumentoAnosBtn.setOnAction(event -> {
            Anos anos = new Anos();
            anos.start(new Stage());
            primaryStage.close();
        });

        // Estilo dos botões
        aumentoAnosBtn.setStyle("-fx-font-size: 30px; -fx-pref-width: 600px;");
        jogarQuizBtn.setStyle("-fx-font-size: 30px; -fx-pref-width: 600px;");
        calculadoraBtn.setStyle("-fx-font-size: 30px; -fx-pref-width: 600px;");
        verMaisBtn.setStyle("-fx-font-size: 20px; -fx-pref-width: 600px;");

        // Vbox para colocar os botões
        VBox vbox = new VBox(20, jogarQuizBtn, aumentoAnosBtn, calculadoraBtn, verMaisBtn);
        vbox.setPadding(new Insets(30));
        vbox.setAlignment(Pos.CENTER);

        // Definir o plano de fundo do Menu
        BorderPane root = new BorderPane(vbox);
        root.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));

        // Criação e definição da cena
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // Título da janela
        primaryStage.setTitle("GarimpaNao");

        // Exibir a janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
