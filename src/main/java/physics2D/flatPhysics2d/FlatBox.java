package physics2D.flatPhysics2d;

public class FlatBox extends FlatBody{
    private double width, height;

    FlatBox(Vector position, double width, double height, double density, boolean isStatic, double restitution) {
        super(
                position,
                Vector.ZERO_VECTOR ,
                0,
                0,
                density,
                density * width * height,
                restitution,
                width * height,
                isStatic
        );
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

