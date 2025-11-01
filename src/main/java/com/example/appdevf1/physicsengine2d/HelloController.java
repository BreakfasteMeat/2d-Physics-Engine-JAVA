package com.example.appdevf1.physicsengine2d;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import physics2D.flatPhysics2d.*;

import java.util.List;

public class HelloController {
    public Canvas mainCanvas;
    public Button debug_ran_circle;
    public Button debug_ran_box;

    FlatWorld world;

    FlatBody debug_moveable_polygon;

    Vector topLeft;
    Vector bottomRight;

    private boolean up, down, left, right;





    public void initialize() {

        topLeft = new  Vector(0,0);
        bottomRight = new Vector(mainCanvas.getWidth(),mainCanvas.getHeight());
        world = new FlatWorld(mainCanvas);
        debug_moveable_polygon = FlatRandom.randomCircle(
                topLeft.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)),
                bottomRight.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2))
        );
//        debug_moveable_polygon = FlatRandom.randomBox(
//                topLeft.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)),
//                bottomRight.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)));
        world.add(debug_moveable_polygon);

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
            debug_moveable_polygon.move(velocity);

        }






        List<FlatBody> bodies = world.getBodies();
    }

    private void resolveCircleToBoxCollision(FlatCircle circle, FlatBox box) {
        if(
                Collisions.PolygonCircleColliding(
                        circle.getPosition(),circle.getRadius(),box.getTransformedVertices()
                )
        ) {
            circle.move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(1).scaleMultiply(-1));
            box.move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(2));
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