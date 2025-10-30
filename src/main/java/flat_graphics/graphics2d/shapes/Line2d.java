package flat_graphics.graphics2d.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import physics2D.flatPhysics2d.Vector;

public class Line2d extends Shape2d {
    Line line;
    Vector direction;

    public Line2d(Vector position, Vector direction, Color color) {
        super(position, color);
        this.direction = direction;
        line = new Line(super.position.getX(), super.position.getY(), direction.getX(), direction.getY());
    }
    @Override
    public void accept(ShapeDrawer drawer, GraphicsContext gc) {
        drawer.visit(this, gc);
    }
    public Line getLine() {
        return line;
    }

    public Vector getDirection() {
        return direction;
    }
}
