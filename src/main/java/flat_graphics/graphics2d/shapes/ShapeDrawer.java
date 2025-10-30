package flat_graphics.graphics2d.shapes;

import javafx.scene.canvas.GraphicsContext;

public class ShapeDrawer implements ShapeVisitor{
    @Override
    public void visit(Circle2d circle, GraphicsContext gc) {

        gc.fillOval(
                circle.getCircle().getCenterX(),
                circle.getCircle().getCenterY(),
                circle.getRadius(),
                circle.getRadius()
        );
    }

    @Override
    public void visit(Line2d line, GraphicsContext gc) {
        gc.setStroke(line.getColor());
        gc.setLineWidth(1);
        gc.strokeLine(
                line.getLine().getStartX(),
                line.getLine().getStartY(),
                line.getLine().getEndX(),
                line.getLine().getEndY()
        );
    }

    public void visit(Box2d box2d, GraphicsContext gc) {
        gc.setStroke(box2d.getColor());
        gc.setLineWidth(1);
        gc.fillRect(
                box2d.getPosition().getX(),
                box2d.getPosition().getY(),
                box2d.height,
                box2d.width
        );
    }
}
