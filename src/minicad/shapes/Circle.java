package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;
import minicad.Utils;

import java.awt.Point;

public final class Circle implements Shape {
    private Point start;
    private int radius;

    private int borderColor = 0;
    private int fillColor = 0;

    private static final int START_X = 0;
    private static final int START_Y = 1;
    private static final int RADIUS = 2;
    private static final int BORDER_COLOR = 3;
    private static final int BORDER_ALPHA = 4;
    private static final int FILL_COLOR = 5;
    private static final int FILL_ALPHA = 6;
    public Circle(final String[] details) {
        start = new Point(Integer.parseInt(details[START_X]),
                Integer.parseInt(details[START_Y]));
        radius = Integer.parseInt(details[RADIUS]);
        borderColor = Utils.getColor(details[BORDER_COLOR],
                Integer.parseInt(details[BORDER_ALPHA]));
        fillColor = Utils.getColor(details[FILL_COLOR],
                Integer.parseInt(details[FILL_ALPHA]));
    }

    public Point getStart() {
        return start;
    }

    public int getRadius() {
        return radius;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public int getFillColor() {
        return fillColor;
    }

    @Override
    public void accept(final Drawing drawing) {
        drawing.draw(this);
    }
}
