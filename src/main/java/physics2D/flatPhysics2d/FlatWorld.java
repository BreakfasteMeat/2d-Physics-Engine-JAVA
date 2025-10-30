package physics2D.flatPhysics2d;

import flat_graphics.Camera;
import flat_graphics.graphics2d.shapes.Box2d;
import flat_graphics.graphics2d.shapes.Circle2d;
import flat_graphics.graphics2d.shapes.Shape2d;
import flat_graphics.graphics2d.shapes.ShapeDrawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatWorld {
    public static final double MIN_BODY_SIZE = 0.01f * 0.01f;
    public static final double MAX_BODY_SIZE = 64f * 64f;
    public static final double MIN_DENSITY = 0.2f;
    public static final double MAX_DENSITY = 21.4f;

    List<Color> colors = new ArrayList<Color>();
    Map<FlatBody, Shape2d> objectMap;
    Camera camera;
    Canvas canvas;
    ShapeDrawer drawer;

    private Color getRandomColor(){
        int index = (int) (Math.random() * colors.size());
        return colors.get(index);
    }

    public Map<FlatBody, Shape2d> getObjectMap() {
        return objectMap;
    }

    public FlatWorld(Canvas canvas) {
        colors.add(Color.web("#1976D2")); // Vibrant Blue
        colors.add(Color.web("#43A047")); // Emerald Green
        colors.add(Color.web("#E53935")); // Strong Red
        colors.add(Color.web("#FB8C00")); // Deep Orange
        colors.add(Color.web("#8E24AA")); // Rich Purple

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
        gc.clearRect(-canvasWidth/2, -canvasHeight/2, canvasWidth, canvasHeight);

        for(Map.Entry<FlatBody, Shape2d> entry : objectMap.entrySet()) {
            entry.getValue().setPosition(entry.getKey().getPosition());
            entry.getValue().accept(drawer,gc);
            System.out.println(((Circle2d)entry.getValue()).getRadius() + " , " + ((FlatCircle)entry.getKey()).getRadius());
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
                    getRandomColor(),
                    box.getWidth(),
                    box.getHeight()
            );
        } else if(body instanceof FlatCircle circle){
            shape2d = new Circle2d(
                    circle.getPosition(),
                    getRandomColor(),
                    circle.getRadius()
            );
        } else {
            throw new RuntimeException("Unsupported body type");
        }
        objectMap.put(body,shape2d);
    }


}
