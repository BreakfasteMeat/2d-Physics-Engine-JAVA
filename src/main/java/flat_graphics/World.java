package flat_graphics;

import flat_graphics.graphics2d.shapes.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import physics2D.flatPhysics2d.FlatBody;
import physics2D.flatPhysics2d.FlatBox;
import physics2D.flatPhysics2d.FlatCircle;
import physics2D.flatPhysics2d.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    Map<FlatBody, Shape2d> objectMap;
    Camera camera;
    Canvas canvas;
    ShapeDrawer drawer;

    public World(Canvas canvas) {
        camera = new Camera(new Vector(0,0),5,canvas.getWidth() / canvas.getHeight());
        this.canvas = canvas;
        objectMap = new HashMap<>();
        drawer = new ShapeDrawer();
    }


    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        gc.save();

        gc.translate(canvasWidth/2,canvasHeight/2); // Setting 0,0 to the center

        gc.scale(1,-1); // Flipping Y axis (positive value goes up)

        for(Map.Entry<FlatBody, Shape2d> entry : objectMap.entrySet()) {
            entry.getValue().accept(drawer,gc);
        }

        gc.restore();
    }

    public void add(FlatBody body) {
        if(objectMap.containsKey(body)) {
           return;
        }
        Shape2d shape2d;
        if(body instanceof FlatBox box) {
            shape2d = new Box2d(
                    box.getPosition(),
                    Color.BLACK,
                    box.getWidth(),
                    box.getHeight()
            );
        } else if(body instanceof FlatCircle circle){
            shape2d = new Circle2d(
                    circle.getPosition(),
                    Color.BLACK,
                    circle.getRadius()
            );
        } else {
            throw new RuntimeException("Unsupported body type");
        }
        objectMap.put(body,shape2d);
    }

//    public void addFilledCircle(Vector center, double radius) {
//        Circle2d circle = new Circle2d(
//                center,
//                Color.BLACK,
//                radius
//        );
//        objectList.add(circle);
//    }
//    public void addFilledBox(Vector top_left_position, double width, double height) {
//        Box2d box = new Box2d(
//                top_left_position,
//                Color.BLACK,
//                width,
//                height
//        );
//        objectList.add(box);
//    }
//    public void addLine(Vector origin, Vector direction) {
//        this.add(
//                new Line2d(
//                        origin,
//                        direction,
//                        Color.BLACK
//                )
//        );
//    }
//    public void addLine(double startX, double startY, double endX, double endY) {
//        addLine(
//                new Vector(startX,startY),
//                new Vector(endX,endY)
//        );
//    }
}
