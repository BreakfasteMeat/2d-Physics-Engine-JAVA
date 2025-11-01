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

    List<Color> colors = new ArrayList<>();
    List<FlatBody> bodies = new ArrayList<>();
    Camera camera;
    Canvas canvas;
    ShapeDrawer drawer;

    private Color getRandomColor(){
        int index = (int) (Math.random() * colors.size());
        return colors.get(index);
    }

    public List<FlatBody> getBodies() {
        return bodies;
    }

    public FlatWorld(Canvas canvas) {
        colors.add(Color.web("#1976D2")); // Vibrant Blue
        colors.add(Color.web("#43A047")); // Emerald Green
        colors.add(Color.web("#E53935")); // Strong Red
        colors.add(Color.web("#FB8C00")); // Deep Orange
        colors.add(Color.web("#8E24AA")); // Rich Purple

        camera = new Camera(new Vector(0,0),5,canvas.getWidth() / canvas.getHeight());
        this.canvas = canvas;
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

//        for(Map.Entry<FlatBody, Shape2d> entry : objectMap.entrySet()) {
//            entry.getValue().setPosition(entry.getKey().getPosition());
//            entry.getValue().accept(drawer,gc);
//            System.out.println(((Circle2d)entry.getValue()).getRadius() + " , " + ((FlatCircle)entry.getKey()).getRadius());
//        }
        for(FlatBody body : bodies){
            if(body instanceof FlatCircle circle) drawCircle(circle, gc);
            else if (body instanceof FlatBox box) drawBox(box, gc);

        }

        gc.restore();
    }

    private void drawCircle(FlatCircle circle, GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.setFill(Color.GREEN);

        double r = circle.getRadius();
        double x = circle.getPosition().getX() - r;
        double y = circle.getPosition().getY() - r;

        gc.fillOval(x, y, r * 2, r * 2);
        gc.strokeOval(x, y, r * 2, r * 2);
    }
    private void drawBox(FlatBox box, GraphicsContext gc) {
        //TODO IMPLEMENT RENDERING OF BOX
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.setFill(Color.GREEN);

        double[] xPoints = new double[4];
        double[] yPoints = new double[4];

        xPoints[0] = box.getTransformedVertices()[0].getX();
        yPoints[0] = box.getTransformedVertices()[0].getY();

        xPoints[1] = box.getTransformedVertices()[1].getX();
        yPoints[1] = box.getTransformedVertices()[1].getY();

        xPoints[2] = box.getTransformedVertices()[2].getX();
        yPoints[2] = box.getTransformedVertices()[2].getY();

        xPoints[3] = box.getTransformedVertices()[3].getX();
        yPoints[3] = box.getTransformedVertices()[3].getY();



        gc.fillPolygon(xPoints, yPoints, 4);
        gc.strokePolygon(xPoints, yPoints, 4);

    }

    public void add(FlatBody body) {
        if(bodies.contains(body)) {
            throw new RuntimeException("Body is already in the world");
        }
        bodies.add(body);
    }


}
