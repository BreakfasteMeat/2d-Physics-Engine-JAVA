package physics2D.flatPhysics2d;

public class Vector{
    protected double x;
    protected double y;

    public static final Vector ZERO_VECTOR = new Vector(0, 0);


    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }
    public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }
    public Vector scaleMultiply(double scale) {
        return new Vector(x * scale, y * scale);
    }
    public Vector scaleDivide(double scale) {
        if (scale == 0){
            throw new ArithmeticException("Cannot divide physics2D.flatPhysics2d.Vector with 0.");
        }
        return new Vector(x / scale, y / scale);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector v = (Vector) obj;
            return x == v.x && y == v.y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


}
