package physics2D.flatPhysics2d;

public class FlatMath {

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
    public static double length(Vector v1) {
        return Math.sqrt(v1.x * v1.x + v1.y * v1.y);
    }
    public static double distance(Vector v1, Vector v2) {
        Vector temp = v1.subtract(v2);
        return Math.sqrt(temp.x * temp.x + temp.y * temp.y);
    }
    public static Vector normalize(Vector v1) {
        double length = length(v1);
        double newX = v1.x / length;
        double newY = v1.y / length;
        return new Vector(newX,newY);
    }
    public static double dot(Vector v1, Vector v2) {
        return (v1.getX() * v2.getX()) + (v1.getY() * v2.getY());
    }
    public static double cross(Vector v1, Vector v2) {
        return v1.x * v1.y - v2.x * v2.y;
    }
}