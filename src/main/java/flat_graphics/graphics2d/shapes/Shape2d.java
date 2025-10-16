package flat_graphics.graphics2d.shapes;

import javafx.scene.paint.Color;
import physics2D.vector2d.Vector;

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
}
