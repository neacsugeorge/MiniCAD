package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;
import minicad.Utils;

import java.awt.Point;

public final class Diamond implements Shape {
    private Point start;
    private int verticalDiagonal;
    private int horizontalDiagonal;

    private int borderColor = 0;
    private int fillColor = 0;

    private static final int START_X = 0;
    private static final int START_Y = 1;
    private static final int HORIZONTAL_DIAGONAL = 2;
    private static final int VERTICAL_DIAGONAL = 3;
    private static final int BORDER_COLOR = 4;
    private static final int BORDER_ALPHA = 5;
    private static final int FILL_COLOR = 6;
    private static final int FILL_ALPHA = 7;
    public Diamond(final String[] details) {
        start = new Point(Integer.parseInt(details[START_X]),
                Integer.parseInt(details[START_Y]));
        horizontalDiagonal = Integer.parseInt(details[HORIZONTAL_DIAGONAL]);
        verticalDiagonal = Integer.parseInt(details[VERTICAL_DIAGONAL]);
        borderColor = Utils.getColor(details[BORDER_COLOR],
                Integer.parseInt(details[BORDER_ALPHA]));
        fillColor = Utils.getColor(details[FILL_COLOR],
                Integer.parseInt(details[FILL_ALPHA]));
    }

    public Point getStart() {
        return start;
    }

    public int getVerticalDiagonal() {
        return verticalDiagonal;
    }

    public int getHorizontalDiagonal() {
        return horizontalDiagonal;
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
