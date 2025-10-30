package physics2D.flatPhysics2d;

public class Collisions {
    public static Vector normal;
    public static double depth;
    public static boolean CirclesColliding(FlatCircle circle1, FlatCircle circle2) {
        double distance = FlatMath.distance(circle1.getPosition(), circle2.getPosition());
        double radii = circle1.getRadius() + circle2.getRadius();
        if(distance >= radii){
            return false;
        }
        normal = FlatMath.normalize(circle2.getPosition().subtract(circle1.getPosition()));
        depth = radii - distance;

        return true;
    }
    private Collisions(){}

    public static void main(String[] args) {
        FlatCircle c1 = new FlatCircle(new Vector(0, 0), 10,1,false,1);
        FlatCircle c2 = new FlatCircle(new Vector(100, 0), 10,1,false,1);

        boolean result = CirclesColliding(c1, c2);
        System.out.println("Distance: " + FlatMath.distance(c1.getPosition(), c2.getPosition()));
        System.out.println("Radii: " + (c1.getRadius() + c2.getRadius()));
        System.out.println("Colliding: " + result);

    }

}
