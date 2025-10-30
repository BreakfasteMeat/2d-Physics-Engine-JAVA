package com.example.appdevf1.physicsengine2d;

import flat_graphics.World;
import flat_graphics.graphics2d.shapes.Circle2d;
import flat_graphics.graphics2d.shapes.Shape2d;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import physics2D.flatPhysics2d.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HelloController {
    public Canvas mainCanvas;
    public Button debug_ran_circle;
    public Button debug_ran_box;

    FlatWorld world;

    FlatCircle main_circle;

    Vector topLeft;
    Vector bottomRight;

    private boolean up, down, left, right;





    public void initialize() {

        topLeft = new  Vector(0,0);
        bottomRight = new Vector(mainCanvas.getWidth(),mainCanvas.getHeight());
        world = new FlatWorld(mainCanvas);
        main_circle = FlatRandom.randomCircle(
                topLeft.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)),
                bottomRight.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2))
        );
        world.add(main_circle);

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
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                if(lastTime > 0) {
                    double deltaTime = (now - lastTime) / 1_000_000_000.0;
                    update(deltaTime);
                    world.render();
                }
                lastTime = now;
            }
        }.start();
    }

    public void update(double deltaTime) {

        double speed = 200;
        double dx = 0;
        double dy = 0;

        if(up) dy++;
        if(down) dy--;
        if(left) dx--;
        if(right) dx++;
        if(dx != 0 || dy != 0) {
            Vector dir = FlatMath.normalize(new Vector(dx, dy));
            Vector velocity = dir.scaleMultiply(speed).scaleMultiply(deltaTime);
            main_circle.move(velocity);

        }


        List<Map.Entry<FlatBody, Shape2d>> entries = new ArrayList<>(world.getObjectMap().entrySet());

        for (int i = 0; i < entries.size(); i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                Map.Entry<FlatBody, Shape2d> entry1 = entries.get(i);
                Map.Entry<FlatBody, Shape2d> entry2 = entries.get(j);
                //ASSUME ALL ARE CIRCLES
                assert entry1.getKey() instanceof FlatCircle;
                assert entry2.getKey() instanceof FlatCircle;

                if(
                        Collisions.CirclesColliding(
                                (FlatCircle) entry1.getKey(),
                                (FlatCircle) entry2.getKey()
                        )
                ){
                    entry1.getKey().move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(2).scaleMultiply(-1));
                    entry2.getKey().move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(2));
                }
            }
        }

    }
    public void handleKeyPress(KeyEvent event){
        switch (event.getCode()){
            case W -> up = true;
            case S -> down = true;
            case A -> left = true;
            case D -> right = true;
        }
    }
    public void handleKeyRelease(KeyEvent event){
        switch (event.getCode()){
            case W -> up = false;
            case S -> down = false;
            case A -> left = false;
            case D -> right = false;
        }
    }



}