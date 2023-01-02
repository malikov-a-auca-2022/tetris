import java.util.ArrayList;
import java.util.Random;

public class Shapes extends ArrayList<Shape> {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();

    @Override
    public boolean add(Shape shape) {
        shapes.add(shape);
        return true;
    }

    public Shape getRandomShape() {
        Random rand = new Random();
        int index = rand.nextInt(shapes.size());
        return shapes.get(index);
    }
}
