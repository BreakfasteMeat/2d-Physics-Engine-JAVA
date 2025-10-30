package flat_graphics.graphics2d.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

interface ShapeVisitor {
    void visit(Circle2d c, GraphicsContext gc);
    void visit(Line2d c, GraphicsContext gc);
}
