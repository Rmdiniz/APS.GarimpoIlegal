package com.example.aps5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Anos extends Application {

    private final double garimpo2016 = 12.87;

    private Text titulo;
    private Label resultadoLabel;
    private TextField anoInput;
    private Button botaoConfirma;
    private Button botaoVoltar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GarimpaNaoAnual");

        // Layout do painel
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Título
        titulo = new Text("Veja o Garimpo Ilegal pelos Anos");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titulo, 0, 0, 2, 1);

        // Lugar para entrada do ano pelo usuário
        Label anoLabel = new Label("Insira o ano:");
        grid.add(anoLabel, 0, 1);

        anoInput = new TextField();
        grid.add(anoInput, 1, 1);

        // Botão que confirma o dado inserido e mostra o resultado
        botaoConfirma = new Button("Mostrar os dados");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(botaoConfirma);
        grid.add(hbBtn, 1, 2);

        // Label do resultado
        resultadoLabel = new Label();
        grid.add(resultadoLabel, 0, 3, 2, 1);

        // Botão para voltar ao menu
        botaoVoltar = new Button("Voltar ao Menu Principal");
        HBox hbBackBtn = new HBox(10);
        hbBackBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBackBtn.getChildren().add(botaoVoltar);
        grid.add(hbBackBtn, 0, 4, 2, 1);

        // Ação do botão do resultado
        botaoConfirma.setOnAction(e -> {
            try {
                int ano = Integer.parseInt(anoInput.getText());

                if (ano < 2016) {
                    resultadoLabel.setText("Não temos dados antes de 2016.");
                } else if (ano == 2016) {
                    resultadoLabel.setText(String.format("Área do garimpo em %d: %.2f km²", ano, garimpo2016));
                } else if (ano > 2022) {
                    resultadoLabel.setText("Não temos dados do futuro... ainda, então ajude a diminuí-los.");
                } else {
                    double area = getGarimpoArea(ano);
                    double aumento = (area - garimpo2016) / garimpo2016 * 100;
                    resultadoLabel.setText(String.format("Área do garimpo em %d: %.2f km² (%.2f%% a mais que 2016)", ano, area, aumento));
                }

            } catch (NumberFormatException ex) {
                resultadoLabel.setText("Digite um ano válido.");
            }
        });

        // Ação do botão de voltar ao menu
        botaoVoltar.setOnAction(e -> {
            // Criar uma instância da janela MenuPrincipal
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            Stage menuStage = new Stage();
            try {
                menuPrincipal.start(menuStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            // Fecha a janela atual
            Stage currentStage = (Stage) botaoVoltar.getScene().getWindow();
            currentStage.close();
        });

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para obter a área de garimpo do ano determinado
    private double getGarimpoArea(int anof) {
        switch (anof) {
            case 2017: return 48.72;
            case 2018: return 79.17;
            case 2019: return 97.24;
            case 2020: return 92.38;
            case 2021: return 114.26;
            case 2022: return 62.1;
            default: throw new IllegalArgumentException("Não há dados para o ano " + anof);
        }
    }
}

