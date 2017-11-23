package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;
import minicad.Utils;

import java.awt.Point;

public final class Line implements Shape {
    private Point start;
    private Point stop;

    private int color = 0;


    private static final int START_X = 0;
    private static final int START_Y = 1;
    private static final int STOP_X = 2;
    private static final int STOP_Y = 3;
    private static final int COLOR = 4;
    private static final int ALPHA = 5;

    public Line(final String[] details) {
        start = new Point(Integer.parseInt(details[START_X]),
                Integer.parseInt(details[START_Y]));
        stop = new Point(Integer.parseInt(details[STOP_X]),
                Integer.parseInt(details[STOP_Y]));
        color = Utils.getColor(details[COLOR], Integer.parseInt(details[ALPHA]));
    }

    public Point getStart() {
        return start;
    }

    public Point getStop() {
        return stop;
    }

    public int getColor() {
        return color;
    }

    @Override
    public void accept(final Drawing drawing) {
        drawing.draw(this);
    }
}
