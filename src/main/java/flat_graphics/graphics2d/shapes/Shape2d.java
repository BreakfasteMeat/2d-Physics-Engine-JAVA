package flat_graphics.graphics2d.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import physics2D.flatPhysics2d.Vector;

public abstract class Shape2d {
    Vector position;
    Color color;
    public Shape2d(Vector position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Vector getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public abstract void accept(ShapeDrawer drawer, GraphicsContext gc);

    public void setPosition(Vector position) {
        this.position = position;
    }
}
