import processing.core.*;

public class Queue {
    public Shape[] queue = new Shape[3];
    private Shapes shapes;
    private Menu menu;
    private PApplet papplet;

    Queue(Shapes shapes, Menu menu) {
        this.shapes = shapes;
        this.menu = menu;
        for(int i = 0; i < queue.length; i++) {
            queue[i] = shapes.getRandomShape();
        }
    }

    public Shape pop() {
        Shape shape = queue[0];
        for(int i = 0; i < queue.length - 1; i++) {
            queue[i] = queue[i + 1];
        }
        queue[queue.length - 1] = shapes.getRandomShape();
        return shape;
    }

    public void draw() { //not finished
        float width = (papplet.width - (papplet.width + menu.getPlayfieldWidth()) / 2f) / 5f;
        float x = (papplet.width + menu.getPlayfieldWidth() + width) / 2f;
        float cellSize = width / 5f;
        float height = cellSize * 4 * queue.length + cellSize * 2; // delete
        float y = (papplet.height - height) / 2f;

        papplet.stroke(255);
        papplet.fill(0, 0);

    }
}