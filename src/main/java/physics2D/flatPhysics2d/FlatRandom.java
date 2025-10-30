package physics2D.flatPhysics2d;

import java.util.Random;

public class FlatRandom {
    private final static Random random = new Random();


    public static FlatCircle randomCircle(Vector min, Vector max) {
        double randomX = min.x + (max.x - min.x) * random.nextDouble();
        double randomY = min.y + (max.y - min.y) * random.nextDouble();
        return new FlatCircle(
                new Vector(randomX,randomY),
                random.nextDouble()*20 + 30,
                1,
                true,
                0.4
        );
    }

    public static FlatBox randomBox(Vector min, Vector max) {
        double randomX = min.x + (max.x - min.x) * random.nextDouble();
        double randomY = min.y + (max.y - min.y) * random.nextDouble();
        return new FlatBox(
                new Vector(randomX,randomY),
                random.nextDouble()*30 + 50,
                random.nextDouble()*30 + 50,
                1,
                true,
                0.4
        );
    }
}
