package physics2D.flatPhysics2d;

public class FlatBox extends FlatBody{
    private double width, height;
    private  Vector[] vertices;
    private  Vector[] transformedVertices;
    private int[] triangles;
    private boolean transformUpdateRequired;

    public Vector[] getVertices() {
        return vertices;
    }

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
        vertices = createBoxVertices();
        this.transformedVertices = new Vector[4];
        transformedVertices[0] = vertices[0];
        transformedVertices[1] = vertices[1];
        transformedVertices[2] = vertices[2];
        transformedVertices[3] = vertices[3];

        transformUpdateRequired = true;
        triangles = createBoxTriangles();
    }

    private Vector[] createBoxVertices(){
        double left = -width / 2f;
        double right = left + width;
        double bottom = -height / 2f;
        double top = bottom + height;

        Vector[] vertices = new Vector[4];
        vertices[0] = new Vector(left, top);
        vertices[1] = new Vector(right, top);
        vertices[2] = new Vector(right, bottom);
        vertices[3] = new Vector(left, bottom);

        return vertices;
    }

    private int[] createBoxTriangles(){
        int[] triangles = new int[6];
        triangles[0] = 0;
        triangles[1] = 1;
        triangles[2] = 2;
        triangles[3] = 0;
        triangles[4] = 2;
        triangles[5] = 3;
        return triangles;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void rotate(double amount){
        rotation += amount;
        transformUpdateRequired = true;
    }

    public Vector[] getTransformedVertices() {
        if(transformUpdateRequired){
            FlatTransform transform = new FlatTransform(position,rotation);
            for(int i = 0;i < vertices.length;i++){
                Vector v = vertices[i];
                transformedVertices[i] = Vector.transform(v, transform);
            }
        }

        transformUpdateRequired = false;
        return transformedVertices;
    }

    @Override
    public void move(Vector delta) {
        super.move(delta);
        transformUpdateRequired = true;
    }

    @Override
    public void moveTo(Vector newPosition) {
        super.moveTo(newPosition);
        transformUpdateRequired = true;
    }
}

