package com.example.appdevf1.physicsengine2d;

import flat_graphics.World;
import flat_graphics.graphics2d.shapes.Circle2d;
import flat_graphics.graphics2d.shapes.Line2d;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import physics2D.vector2d.Vector;

public class HelloController {
    public Canvas mainCanvas;
    public Button debug_ran_circle;

    World world;



    public void initialize() {
        world = new World(mainCanvas);
        debug_ran_circle.setOnMouseClicked(event -> {
            System.out.println("Clicked!");
            world.add(
                    new Circle2d(
                            new Vector(Math.random() * mainCanvas.getWidth(),Math.random() * mainCanvas.getHeight()),
                            Color.BLACK,
                            Math.random() * 50 + 50

                    )
            );
        });

        world.add(
                new Line2d(
                        new Vector(50,50),
                        new Vector(30,100),
                        Color.ALICEBLUE
                )
        );
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                world.render();
            }
        }.start();
    }
}