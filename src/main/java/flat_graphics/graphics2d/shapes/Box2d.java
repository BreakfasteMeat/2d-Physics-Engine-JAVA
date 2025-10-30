package flat_graphics.graphics2d.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import physics2D.flatPhysics2d.Vector;

public class Box2d extends Shape2d {
    double width, height;
    public Box2d(Vector position, Color color, double width, double height) {
        super(position, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(ShapeDrawer drawer, GraphicsContext gc) {
        drawer.visit(this,gc);
    }
}
