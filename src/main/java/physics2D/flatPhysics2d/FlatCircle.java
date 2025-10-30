package physics2D.flatPhysics2d;

public class FlatCircle extends FlatBody{
    private double radius;

    public double getRadius() {
        return radius;
    }

    FlatCircle(Vector position, double radius, double density, boolean isStatic, double restitution) {
        super(
                position,
                Vector.ZERO_VECTOR ,
                0,
                0,
                density,
                density * Math.PI * radius * radius,
                restitution,
                Math.PI * radius * radius,
                isStatic
        );
        this.radius = radius;
    }
}
