package physics2D.flatPhysics2d;

public abstract class FlatBody {
    protected Vector position;
    protected Vector velocity;
    protected double rotation;
    protected double rotationalVelocity;
    protected double density;
    protected double mass;
    protected double restitution;
    protected double area;
    protected boolean isStatic;

    public FlatBody(Vector position, Vector velocity, double rotation, double rotationalVelocity, double density, double mass, double restitution, double area, boolean isStatic) {
        this.position = position;
        this.velocity = velocity;
        this.rotation = rotation;
        this.rotationalVelocity = rotationalVelocity;
        this.density = density;
        this.mass = mass;
        this.restitution = restitution;
        this.area = area;
        this.isStatic = isStatic;
    }

    public void step(double elapsedTime){
        position.add(velocity);
        rotation += rotationalVelocity;
    }

    public static FlatCircle CreateCircle(Vector position, double radius, double density, boolean isStatic, double restitution) {
        double area = radius * radius * Math.PI;

        if(area < FlatWorld.MIN_BODY_SIZE){
            throw new IllegalArgumentException("Area is too small. Minimum area is " + FlatWorld.MIN_BODY_SIZE);
        }
        if(area > FlatWorld.MAX_BODY_SIZE){
            throw new IllegalArgumentException("Area is too large. Maximum area is " + FlatWorld.MAX_BODY_SIZE);
        }
        if(density < FlatWorld.MIN_DENSITY){
            throw new IllegalArgumentException("Density is too small. Minimum density is " + FlatWorld.MIN_DENSITY);
        }
        if(density > FlatWorld.MAX_DENSITY){
            throw new IllegalArgumentException("Density is too large. Maximum density is " + FlatWorld.MAX_DENSITY);
        }

        restitution = FlatMath.clamp(restitution,0f,1f);

        return new FlatCircle(position,radius,density,isStatic,restitution);
    }
    public static FlatBox CreateBox(Vector position, double width, double height, double density, boolean isStatic, double restitution) {
        double area = width * height * Math.PI;

        if(area < FlatWorld.MIN_BODY_SIZE){
            throw new IllegalArgumentException("Area is too small. Minimum area is " + FlatWorld.MIN_BODY_SIZE);
        }
        if(area > FlatWorld.MAX_BODY_SIZE){
            throw new IllegalArgumentException("Area is too large. Maximum area is " + FlatWorld.MAX_BODY_SIZE);
        }
        if(density < FlatWorld.MIN_DENSITY){
            throw new IllegalArgumentException("Density is too small. Minimum density is " + FlatWorld.MIN_DENSITY);
        }
        if(density > FlatWorld.MAX_DENSITY){
            throw new IllegalArgumentException("Density is too large. Maximum density is " + FlatWorld.MAX_DENSITY);
        }

        restitution = FlatMath.clamp(restitution,0f,1f);

        return new FlatBox(position,width,height,density,isStatic,restitution);
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public double getRotation() {
        return rotation;
    }

    public double getRotationalVelocity() {
        return rotationalVelocity;
    }

    public double getDensity() {
        return density;
    }

    public double getMass() {
        return mass;
    }

    public double getRestitution() {
        return restitution;
    }

    public double getArea() {
        return area;
    }

    public boolean isStatic() {
        return isStatic;
    }


    public void move(Vector delta) {
        if(delta == null){
            throw new IllegalArgumentException("Delta is null");
        }
        position = position.add(delta);
    }

    public void moveTo(Vector newPosition) {
        if(newPosition == null){
            throw new IllegalArgumentException("New position is null");
        }
        position = newPosition;
    }

}
