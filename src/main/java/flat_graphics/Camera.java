package flat_graphics;

import physics2D.flatPhysics2d.Vector;

public class Camera {
    private Vector position;
    private int zoom;
    private double aspectRatio;

    private final int MIN_ZOOM = 1;
    private final int MAX_ZOOM = 64;

    public Camera(Vector position, int zoom, double aspectRatio) {
        this.position = position;
        this.zoom = zoom;
        this.aspectRatio = aspectRatio;
    }

    public Vector getPosition() {
        return position;
    }

    public void move(double x, double y) {
        position.add(new Vector(x, y));
    }

    public int getZoom() {
        return zoom;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }
}
