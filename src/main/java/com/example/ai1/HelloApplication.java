package com.example.ai1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class HelloApplication extends Application {

    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series;
    private TextArea ResultArea;
    private TextField popSizeField, mutationField;
    private Label resultLabel;

    static final int GENE_LENGTH = 32;
    private String target = "";
    private String[] population;
    private Random random = new Random(42);

    @Override
    public void start(Stage stage) {
        stage.setTitle("AI Project 1: Genetic Algorithm");

        popSizeField = new TextField("100");
        mutationField = new TextField("0.01");
        Button startBtn = new Button("Start Evolution");

        HBox inputBar = new HBox(10,
                new Label("Pop Size:"), popSizeField,
                new Label("Mutation Rate:"), mutationField,
                startBtn
        );
        inputBar.setStyle("-fx-padding: 10; -fx-alignment: center;");


        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis(0, 32, 5);
        xAxis.setLabel("Generation");
        yAxis.setLabel("Best Fitness");

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false);

        series = new XYChart.Series<>();
        series.setName("Fitness Progress");
        lineChart.getData().add(series);

        ResultArea = new TextArea();
        ResultArea.setEditable(false);
        ResultArea.setPrefHeight(200);

        resultLabel = new Label("Status: Ready");

        startBtn.setOnAction(e -> runGeneticAlgorithm());

        VBox layout = new VBox(10, inputBar, lineChart, ResultArea, resultLabel);
        Scene scene = new Scene(layout, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void runGeneticAlgorithm() {

        series.getData().clear();
        ResultArea.clear();

        int popSize = Integer.parseInt(popSizeField.getText());
        double mutationRate = Double.parseDouble(mutationField.getText());

        population = new String[popSize];

        // pascode !!
        target = "";
        for (int i = 0; i < GENE_LENGTH; i++)
            target += random.nextInt(2);

        ResultArea.appendText("Target Passcode: " + target + "\n");

        //
        for (int i = 0; i < popSize; i++) {
            String individual = "";
            for (int j = 0; j < GENE_LENGTH; j++)
                individual += random.nextInt(2);
            population[i] = individual;
        }

        long startTime = System.currentTimeMillis();
        int generation = 0;

        while (true) {

            generation++;

            Population best1 = new Population(-1, "");
            Population best2 = new Population(-1, "");

            for (int i = 0; i < popSize; i++) {
                int fitness = getFitness(target, population[i]);

                if (fitness > best1.getFitness()) {
                    best2 = new Population(best1.getFitness(), best1.getGenes());
                    best1 = new Population(fitness, population[i]);
                } else if (fitness > best2.getFitness()) {
                    best2 = new Population(fitness, population[i]);
                }
            }

            series.getData().add(new XYChart.Data<>(generation, best1.getFitness()));

            if (generation % 10 == 0 || best1.getFitness() == GENE_LENGTH) {
                ResultArea.appendText(
                        "Generation " + generation +
                                " | Best Fitness: " + best1.getFitness() + "\n"
                );
            }

            if (best1.getFitness() == GENE_LENGTH) {
                long totalTime = System.currentTimeMillis() - startTime;
                resultLabel.setText(
                        "Found in Gen: " + generation + " | Time: " + totalTime + " ms"
                );
                ResultArea.appendText("--- Solution Found! ---\n");
                ResultArea.appendText("Result: " + best1.getGenes() + "\n");
                break;
            }

            String[] nextGen = new String[popSize];

            for (int i = 0; i < popSize; i++) {
                String child = crossover(best1.getGenes(), best2.getGenes());
                nextGen[i] = mutate(child, mutationRate);
            }

            population = nextGen;

            if (generation > 5000) {
                ResultArea.appendText("Stopped: Exceeded 5000 generations.\n");
                break;
            }
        }
    }

    private int getFitness(String source, String destination) {
        int score = 0;
        for (int i = 0; i < GENE_LENGTH; i++) {
            if (source.charAt(i) == destination.charAt(i))
                score++;
        }
        return score;
    }

    private String crossover(String p1, String p2) {
        int point = random.nextInt(GENE_LENGTH);
        return p1.substring(0, point) + p2.substring(point);
    }

    private String mutate(String child, double rate) {
        char[] genes = child.toCharArray();

        for (int i = 0; i < GENE_LENGTH; i++) {
            if (random.nextDouble() < rate) {
                if (genes[i] == '0') {
                    genes[i] = '1';
                } else {
                    genes[i] = '0';
                }            }
        }
        return new String(genes);
    }

    public static void main(String[] args) {
        launch(args);
    }
}