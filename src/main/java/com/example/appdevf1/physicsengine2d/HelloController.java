package com.example.appdevf1.physicsengine2d;

import flat_graphics.World;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import physics2D.flatPhysics2d.FlatRandom;
import physics2D.flatPhysics2d.Vector;

public class HelloController {
    public Canvas mainCanvas;
    public Button debug_ran_circle;
    public Button debug_ran_box;

    World world;

    Vector topLeft;
    Vector bottomRight;



    public void initialize() {
        topLeft = new  Vector(0,0);
        bottomRight = new Vector(mainCanvas.getWidth(),mainCanvas.getHeight());
        world = new World(mainCanvas);
        debug_ran_circle.setOnMouseClicked(event -> {
            world.add(
                    FlatRandom.randomCircle(
                            topLeft.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)),
                            bottomRight.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2))
            ));
        });

        debug_ran_box.setOnMouseClicked(event -> {
            world.add(FlatRandom.randomBox(
                    topLeft.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)),
                    bottomRight.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2))));
        });



        new AnimationTimer() {
            @Override
            public void handle(long now) {
                world.render();
            }
        }.start();
    }
}