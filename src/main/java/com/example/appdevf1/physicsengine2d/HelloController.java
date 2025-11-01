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
//        debug_moveable_polygon = FlatRandom.randomCircle(
//                topLeft.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)),
//                bottomRight.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2))
//        );
        debug_moveable_polygon = FlatRandom.randomBox(
                topLeft.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)),
                bottomRight.subtract(new Vector(mainCanvas.getWidth()/2,mainCanvas.getHeight()/2)));
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
        for(FlatBody body : bodies) {
            ((FlatBox)body).rotate(0.01);
        }

        for(int i = 0;i < bodies.size();i++) {
            for(int j = i + 1;j < bodies.size();j++) {
                FlatBody body1 = bodies.get(i);
                FlatBody body2 = bodies.get(j);

                if(body1 instanceof FlatCircle && body2 instanceof FlatCircle) {
                    resolveCircleToCircleCollision((FlatCircle)body1, (FlatCircle)body2);
                }
                if(body1 instanceof FlatBox && body2 instanceof FlatBox) {
                    resolveBoxToBoxCollision((FlatBox)body1, (FlatBox)body2);
                }
            }
        }

    }

    private void resolveBoxToBoxCollision(FlatBox box1, FlatBox box2){
        if(
            Collisions.PolygonColliding(box1.getTransformedVertices(), box2.getTransformedVertices())
        ){
            box1.move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(1).scaleMultiply(-1));
            box2.move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(2));
        }
    }

    private void resolveCircleToCircleCollision(FlatCircle circle1, FlatCircle circle2) {
        if(
                Collisions.CirclesColliding(
                        circle1, circle2
                )
        ){
            circle1.move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(1).scaleMultiply(-1));
            circle2.move(Collisions.normal.scaleMultiply(Collisions.depth).scaleDivide(2));
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