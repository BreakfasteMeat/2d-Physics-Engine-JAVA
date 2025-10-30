package flat_graphics.graphics2d.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShapeDrawer implements ShapeVisitor{
    @Override
    public void visit(Circle2d circle, GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.setFill(circle.getColor());

        double r = circle.getRadius();
        double x = circle.getPosition().getX() - r;
        double y = circle.getPosition().getY() - r;

        gc.fillOval(x, y, r * 2, r * 2);
        gc.strokeOval(x, y, r * 2, r * 2);
    }


    @Override
    public void visit(Line2d line, GraphicsContext gc) {
        gc.setStroke(line.getColor());

        gc.strokeLine(
                line.getLine().getStartX(),
                line.getLine().getStartY(),
                line.getLine().getEndX(),
                line.getLine().getEndY()
        );
    }

    public void visit(Box2d box2d, GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.setFill(box2d.getColor());
        gc.fillRect(
                box2d.getPosition().getX(),
                box2d.getPosition().getY(),
                box2d.height,
                box2d.width
        );
        gc.strokeRect(
                box2d.getPosition().getX(),
                box2d.getPosition().getY(),
                box2d.height,
                box2d.width
        );
    }
}
