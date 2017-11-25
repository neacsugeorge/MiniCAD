package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;
import minicad.Utils;

import java.awt.Point;
import java.util.ArrayList;

public final class Polygon implements Shape {
    private ArrayList<Point> points;

    private int borderColor = 0;
    private int fillColor = 0;

    private static final int TWO = 2;
    public Polygon(final String[] details) {
        points = new ArrayList<>();

        int numPoints = Integer.parseInt(details[0]);

        int i;
        for (i = 0; i < numPoints; i++) {
            points.add(new Point(
                    Integer.parseInt(details[TWO * i + 1]),
                    Integer.parseInt(details[TWO * i + TWO])
            ));
        }

        borderColor = Utils.getColor(details[TWO * i + 1],
                Integer.parseInt(details[TWO * i + TWO]));

        i++;

        fillColor = Utils.getColor(details[TWO * i + 1],
                Integer.parseInt(details[TWO * i + TWO]));
    }

    public ArrayList<Point> getPoints() {
        return points;
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
