package flat_graphics;

import flat_graphics.graphics2d.shapes.Circle2d;
import flat_graphics.graphics2d.shapes.Line2d;
import flat_graphics.graphics2d.shapes.Shape2d;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import physics2D.vector2d.Vector;

import java.util.ArrayList;
import java.util.List;

public class World {
    List<Shape2d> objectList;
    Camera camera;
    Canvas canvas;

    public World(Canvas canvas) {
        camera = new Camera(new Vector(0,0),5,canvas.getWidth() / canvas.getHeight());
        this.canvas = canvas;
        objectList = new ArrayList<>();
    }


    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for(Shape2d shape : objectList) {
            System.out.println(shape.toString());
            if(shape instanceof Circle2d){
                Circle2d circle = (Circle2d) shape;

                gc.fillOval(
                        circle.getCircle().getCenterX(),
                        circle.getCircle().getCenterY(),
                        circle.getRadius(),
                        circle.getRadius()
                );
            } else if (shape instanceof Shape2d){
                Line2d line = (Line2d) shape;
                gc.setStroke(line.getColor());
                gc.setLineWidth(3);
                gc.strokeLine(
                        line.getLine().getStartX(),
                        line.getLine().getStartY(),
                        line.getLine().getEndX(),
                        line.getLine().getStartY()
                );
            }
        }
    }

    public void add(Shape2d shape) {
        objectList.add(shape);
    }
}
