package com.example.aps5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Quiz extends Application {

    private int pontos = 0;
    private int questaoAtual = 0;

    private Label questaoLabel;
    private RadioButton[] botaoResposta;
    private Button botaoProximo;

    // Criação das perguntas com a resposta e o número da alternativa correta
    private Questao[] questoes = {
            new Questao("Qual é o químico pesado utilizado para separar o ouro no garimpo?",
                    "Chumbo", "Urânio", "Mercúrio", 3),
            new Questao("Quais são os impactos ambientais causados pelo garimpo ilegal?",
                    "Poluição do ar e do solo", "Desmatamento, contaminação da água, poluição do solo", "Nenhum, é uma prática segura", 2),
            new Questao("Qual a região mais afetada pelo garimpo?",
                    "Nordeste", "Norte", "Centro-Oeste", 2)
    };

    private static class Questao {
        public String pergunta;
        public String[] respostas;
        public int respostaCerta;

        // Definição da sequência das Strings
        public Questao(String pergunta, String resposta1, String resposta2, String resposta3, int respostaCerta) {
            this.pergunta = pergunta;
            this.respostas = new String[]{resposta1, resposta2, resposta3};
            this.respostaCerta = respostaCerta;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Button botaoVoltar = new Button("Voltar ao Menu Principal");
        botaoVoltar.setOnAction(e -> {
            // Cria uma instância da janela MenuPrincipal
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

        // Layout do Quiz
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        questaoLabel = new Label(questoes[questaoAtual].pergunta);
        questaoLabel.setWrapText(true);
        questaoLabel.setMaxWidth(2000);
        grid.add(questaoLabel, 0, 0, 3, 1);

        ToggleGroup grupoRespostas = new ToggleGroup();
        botaoResposta = new RadioButton[3];
        for (int i = 0; i < botaoResposta.length; i++) {
            botaoResposta[i] = new RadioButton(questoes[questaoAtual].respostas[i]);
            botaoResposta[i].setToggleGroup(grupoRespostas);
            grid.add(botaoResposta[i], i, 1);
        }
        botaoProximo = new Button("Próxima pergunta");
        botaoProximo.setOnAction(e -> verResposta());
        grid.add(botaoProximo, 1, 2);
        grid.add (botaoVoltar, 0, 20 );
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz de Garimpo Ilegal");
        primaryStage.show();
    }

    // Checagem da resposta e mudanças posteriores a escolha
    private void verResposta() {
        int selectedAnswer = -1;
        for (int i = 0; i < botaoResposta.length; i++) {
            if (botaoResposta[i].isSelected()) {
                selectedAnswer = i + 1;
                break;
            }
        }

        if (selectedAnswer == questoes[questaoAtual].respostaCerta) {
            botaoResposta[selectedAnswer - 1].setBackground(new Background(new BackgroundFill(Color.GREEN            , CornerRadii.EMPTY, Insets.EMPTY)));
            pontos++;
        } else {
            botaoResposta[selectedAnswer - 1].setBackground(new Background(new BackgroundFill(Color.RED,
                    CornerRadii.EMPTY, Insets.EMPTY)));
        }

        for (int i = 0; i < botaoResposta.length; i++) {
            botaoResposta[i].setDisable(true);
        }

        botaoProximo.setText("Próxima pergunta");
        botaoProximo.setOnAction(e -> proximaQuestao());
    }

    // Botão para seguir para a proxima pergunta
    private void proximaQuestao() {
        questaoAtual++;
        if (questaoAtual < questoes.length) {
            questaoLabel.setText(questoes[questaoAtual].pergunta);
            for (int i = 0; i < botaoResposta.length; i++) {
                botaoResposta[i].setText(questoes[questaoAtual].respostas[i]);
                botaoResposta[i].setSelected(false);
                botaoResposta[i].setBackground(Background.EMPTY);
                botaoResposta[i].setDisable(false);
            }
            botaoProximo.setText("Responder");
            botaoProximo.setOnAction(e -> verResposta());
        } else {
            showResult();
        }
    }

    // Tela de resultados
    private void showResult() {
        Label resultadoLabel = new Label("Você acertou " + pontos + " de " + questoes.length + " perguntas.");
        resultadoLabel.setAlignment(Pos.CENTER);
        resultadoLabel.setPadding(new Insets(55, 55, 55, 55));

        Label mensagemLabel = new Label("O garimpo ilegal é uma atividade extremamente prejudicial ao meio ambiente e à sociedade. Além de causar desmatamento, poluição de rios e solos, contaminação pelo mercúrio e outros, o garimpo ilegal também pode ser associado a violência, trabalho infantil, tráfico de drogas e outros crimes. Não contribua com essa prática e denuncie!");
        mensagemLabel.setWrapText(true);
        mensagemLabel.setMaxWidth(3000);
        mensagemLabel.setPadding(new Insets(55, 55, 55, 55));

        Button botaoVoltar = new Button("Voltar ao Menu Principal");
        botaoVoltar.setOnAction(e -> {
            // Cria uma instância da janela MenuPrincipal
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

        // Tela de resultados estilo
        GridPane resultadostGrid = new GridPane();
        resultadostGrid.setAlignment(Pos.CENTER);
        resultadostGrid.setHgap(10);
        resultadostGrid.setVgap(10);
        resultadostGrid.setPadding(new Insets(25, 25, 25, 25));
        resultadostGrid.add(resultadoLabel, 0, 0);
        resultadostGrid.add(mensagemLabel, 0, 1);
        resultadostGrid.add(botaoVoltar, 0, 2);

        Scene resultScene = new Scene(resultadostGrid, 800, 600);
        Stage primaryStage = (Stage) questaoLabel.getScene().getWindow();
        primaryStage.setScene(resultScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

