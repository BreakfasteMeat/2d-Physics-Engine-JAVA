package physics2D.flatPhysics2d;

public class FlatTransform {
    public double positionX, positionY;
    public double sin, cos;

    public static FlatTransform ZERO = new FlatTransform(0,0,0);

    public FlatTransform(double positionX, double positionY, double angle) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.sin = Math.sin(angle);
        this.cos = Math.cos(angle);
    }

    public FlatTransform(Vector position, double angle) {
        this.positionX = position.getX();
        this.positionY = position.getY();
        this.sin = Math.sin(angle);
        this.cos = Math.cos(angle);
    }
}
