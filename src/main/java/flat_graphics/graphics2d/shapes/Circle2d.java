package flat_graphics.graphics2d.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import physics2D.flatPhysics2d.Vector;

public class Circle2d extends Shape2d {
    Circle circle;
    double radius;

    public Circle2d(Vector position, Color color, double radius) {
        super(position, color);
        this.radius = radius;
        circle = new Circle(super.position.getX(), super.position.getY(), radius);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void accept(ShapeDrawer drawer, GraphicsContext gc) {
        drawer.visit(this, gc);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Circle getCircle() {
        return circle;
    }

    public double getRadius() {
        return radius;
    }
}
