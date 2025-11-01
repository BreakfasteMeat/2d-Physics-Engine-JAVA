package physics2D.flatPhysics2d;

import javafx.util.Pair;
import jdk.incubator.vector.VectorOperators;

public class Collisions {
    public static Vector normal;
    public static double depth;

    public static boolean PolygonCircleColliding(Vector circleCenter, double circleRadius, Vector[] vertices) {
        normal = Vector.ZERO_VECTOR;
        depth = Double.MAX_VALUE;

        for(int i = 0; i < vertices.length; i++){
            Vector va = vertices[i];
            Vector vb = vertices[(i + 1) % vertices.length];

            Vector edge = vb.subtract(va);
            Vector axis = new Vector(-edge.getY(),edge.getX());


            Pair<Double,Double> verticesAProjResult, verticesBProjResult;
            verticesAProjResult = projectVertices(vertices, axis);
            verticesBProjResult = projectCircle(circleCenter,circleRadius, axis);


            if(verticesAProjResult.getKey() >= verticesBProjResult.getValue() || verticesBProjResult.getKey() >= verticesAProjResult.getValue()){
                return false;
            }

            double axisDepth = Math.min(
                    verticesAProjResult.getValue() - verticesBProjResult.getKey() , verticesBProjResult.getValue() - verticesAProjResult.getKey()
            );
            if(axisDepth < depth){
                depth = axisDepth;
                normal = axis;
            }
        }
        int closestPointIndex = findClosestPointOnPolygon(circleCenter,vertices);
        Vector closestPoint = vertices[closestPointIndex];

        Vector axis = closestPoint.subtract(circleCenter);

        Pair<Double,Double> verticesAProjResult, verticesBProjResult;
        verticesAProjResult = projectVertices(vertices, axis);
        verticesBProjResult = projectCircle(circleCenter,circleRadius, axis);


        if(verticesAProjResult.getKey() >= verticesBProjResult.getValue() || verticesBProjResult.getKey() >= verticesAProjResult.getValue()){
            return false;
        }

        double axisDepth = Math.min(
                verticesAProjResult.getValue() - verticesBProjResult.getKey() , verticesBProjResult.getValue() - verticesAProjResult.getKey()
        );
        if(axisDepth < depth){
            depth = axisDepth;
            normal = axis;
        }

        depth /= FlatMath.length(normal);
        normal = FlatMath.normalize(normal);

        Vector polygonCenter = findArithmeticMean(vertices);

        Vector direction = polygonCenter.subtract(circleCenter);

        if(FlatMath.dot(direction, normal) < 0f){
            normal = normal.scaleMultiply(-1);
        }

        return true;
    }

    private static int findClosestPointOnPolygon(Vector circleCenter, Vector[] vertices) {
        int result = -1;
        double minDistance = Double.MAX_VALUE;

        for(int i = 0; i < vertices.length; i++){
            Vector va = vertices[i];
            double distance = FlatMath.distance(circleCenter, va);

            if(distance < minDistance){
                minDistance = distance;
                result = i;
            }
        }

        return result;
    }

    public static boolean PolygonColliding(Vector[] verticesA, Vector[] verticesB) {
        normal = Vector.ZERO_VECTOR;
        depth = Double.MAX_VALUE;

        for(int i = 0; i < verticesA.length; i++){
            Vector va = verticesA[i];
            Vector vb = verticesA[(i + 1) % verticesA.length];

            Vector edge = vb.subtract(va);
            Vector axis = new Vector(-edge.getY(),edge.getX());


            Pair<Double,Double> verticesAProjResult, verticesBProjResult;
            verticesAProjResult = projectVertices(verticesA, axis);
            verticesBProjResult = projectVertices(verticesB, axis);

            if(verticesAProjResult.getKey() >= verticesBProjResult.getValue() || verticesBProjResult.getKey() >= verticesAProjResult.getValue()){
                return false;
            }

            double axisDepth = Math.min(
                    verticesAProjResult.getValue() - verticesBProjResult.getKey() , verticesBProjResult.getValue() - verticesAProjResult.getKey()
            );
            if(axisDepth < depth){
                depth = axisDepth;
                normal = axis;
            }
        }

        for(int i = 0; i < verticesB.length; i++){
            Vector va = verticesB[i];
            Vector vb = verticesB[(i + 1) % verticesB.length];

            Vector edge = vb.subtract(va);
            Vector axis = new Vector(-edge.getY(),edge.getX());

            Pair<Double,Double> verticesAProjResult, verticesBProjResult;
            verticesAProjResult = projectVertices(verticesA, axis);
            verticesBProjResult = projectVertices(verticesB, axis);

            if(verticesAProjResult.getKey() >= verticesBProjResult.getValue() || verticesBProjResult.getKey() >= verticesAProjResult.getValue()){
                return false;
            }
            double axisDepth = Math.min(
                    verticesAProjResult.getValue() - verticesBProjResult.getKey() , verticesBProjResult.getValue() - verticesAProjResult.getKey()
            );
            if(axisDepth < depth){
                depth = axisDepth;
                normal = axis;
            }
        }

        depth /= FlatMath.length(normal);
        normal = FlatMath.normalize(normal);

        Vector centerA = findArithmeticMean(verticesA);
        Vector centerB = findArithmeticMean(verticesB);

        Vector direction = centerB.subtract(centerA);

        if(FlatMath.dot(direction, normal) < 0f){
            normal = normal.scaleMultiply(-1);
        }


        return true;
    }


    private static Vector findArithmeticMean(Vector[] verticesA) {
        double sumX = 0, sumY = 0;
        for(int i = 0; i < verticesA.length; i++){
            sumX += verticesA[i].getX();
            sumY += verticesA[i].getY();
        }
        return new Vector(sumX / verticesA.length, sumY / verticesA.length);
    }


    private static Pair<Double, Double> projectVertices(Vector[] verticesA, Vector axis) {
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;

        for (Vector v : verticesA) {
            double proj = FlatMath.dot(v, axis);
            if (proj < min) min = proj;
            if (proj > max) max = proj;
        }

        return new Pair<>(min, max);
    }

    private static Pair<Double, Double> projectCircle(Vector center, double radius, Vector axis){
        Vector direction = FlatMath.normalize(axis);
        Vector directionAndRadius = direction.scaleMultiply(radius);

        Vector p1 = center.add(directionAndRadius);
        Vector p2 = center.subtract(directionAndRadius);

        double min = FlatMath.dot(p1, axis);
        double max = FlatMath.dot(p2, axis);

        if(min > max){
            double t = min;
            min = max;
            max = t;
        }

        return new Pair<>(min, max);
    }

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
