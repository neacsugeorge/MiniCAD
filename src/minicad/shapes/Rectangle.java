package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;
import minicad.Utils;

import java.awt.Point;

public final class Rectangle implements Shape {
    private Point start;
    private int width, height;

    private int borderColor = 0;
    private int fillColor = 0;

    private static final int START_X = 0;
    private static final int START_Y = 1;
    private static final int HEIGHT = 2;
    private static final int WIDTH = 3;
    private static final int BORDER_COLOR = 4;
    private static final int BORDER_ALPHA = 5;
    private static final int FILL_COLOR = 6;
    private static final int FILL_ALPHA = 7;

    public Rectangle(final String[] details) {
        start = new Point(Integer.parseInt(details[START_X]),
                Integer.parseInt(details[START_Y]));
        height = Integer.parseInt(details[HEIGHT]);
        width = Integer.parseInt(details[WIDTH]);
        borderColor = Utils.getColor(details[BORDER_COLOR],
                Integer.parseInt(details[BORDER_ALPHA]));
        fillColor = Utils.getColor(details[FILL_COLOR],
                Integer.parseInt(details[FILL_ALPHA]));
    }

    public Point getStart() {
        return start;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public int getFillColor() {
        return fillColor;
    }

    public Point getCenter(final int canvasWidth, final int canvasHeight) {
        Point position = new Point(start);

        int newX = position.x + (width - 2) / 2;
        int newY = position.y + (height - 2) / 2;

        if (newX > canvasWidth) {
            newX = canvasWidth;
        }
        if (newY > canvasHeight) {
            newY = canvasHeight;
        }

        int drawnRectangleWidth = newX - position.x;
        int drawnRectangleHeight = newY - position.y;

//        System.out.println(start);
        position.setLocation(
                position.x + (drawnRectangleWidth - 2) / 2,
                position.y + (drawnRectangleHeight - 2) / 2
        );
//        System.out.println(position);

        return position;
    }

    @Override
    public void accept(final Drawing drawing) {
        drawing.draw(this);
    }

    public String toString() {
        return "RECTANGLE" + " " + start.x + " " + start.y
                + " " + height + " " + width
                + " " + Utils.getHexAndAlpha(borderColor)
                + " " + Utils.getHexAndAlpha(fillColor);
    }
}
