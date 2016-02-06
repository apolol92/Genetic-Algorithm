package com.company;

import genetic_algorithm.GeneticAlgorithm;
import genetic_algorithm.Node;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by apolol92 on 06.02.2016.
 * This class is the Application-Class. It shows a gui. In this gui you can create checkpoints for the roundtrip and
 * calculate the roundtrip.
 */
public class Main extends Application {
    //Game title
    public static String GAME_TITLE = "Travelling Salesman Problem";
    //Graph
    ArrayList<Node> nodes = new ArrayList<Node>();
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage theStage) {
        //Set title of stage
        theStage.setTitle(GAME_TITLE);
        //Create Layout
        VBox root = new VBox();
        //Create Scene
        Scene theScene = new Scene( root );
        //Set Scene
        theStage.setScene(theScene);
        //Create canvas
        Canvas canvas = new Canvas(400,400);
        //Set mouse event.. used for setting points
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX() + " " + event.getY());
                //Add point to graph
                nodes.add(new Node(event.getX(), event.getY()));
                //Draw point
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill( Color.BLACK );
                gc.fillOval(event.getX() - 15, event.getY() - 15, 30, 30);

            }
        });
        //Create button to calculate roundtrip
        Button btCreateRoundtrip = new Button("Create Roundtrip");
        btCreateRoundtrip.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Calculate
                GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
                GeneticAlgorithm.setGenSize(nodes.size());
                ArrayList<Node> roundtrip = GeneticAlgorithm.calculate(nodes, 20, 20);
                //Draw Roundtrip
                GraphicsContext gc = canvas.getGraphicsContext2D();
                //gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
                for(int i = 0; i < roundtrip.size()-1; i++) {
                    gc.setLineWidth(5);
                    gc.setFill(Color.BLACK);
                    gc.strokeLine(roundtrip.get(i).getX(), roundtrip.get(i).getY(), roundtrip.get(i+1).getX(), roundtrip.get(i+1).getY());
                }
                gc.strokeLine(roundtrip.get(roundtrip.size() - 1).getX(), roundtrip.get(roundtrip.size() - 1).getY(), roundtrip.get(0).getX(), roundtrip.get(0).getY());
            }
        });
        //Clean graph and canvas button
        Button btClean = new Button("Clean all");
        btClean.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nodes = new ArrayList<Node>();
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
                gc.fillRect(0, 0, 512, 512);
            }
        });
        //Add components to layout
        root.getChildren().add(canvas);
        root.getChildren().add(btCreateRoundtrip);
        root.getChildren().add(btClean);
        //Change Canvas color
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
        gc.fillRect(0, 0, 512, 512);
        //Show stage..
        theStage.show();


    }




}
