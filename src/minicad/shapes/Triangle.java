package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;
import minicad.Utils;

import java.awt.Point;
import java.util.ArrayList;

public final class Triangle implements Shape {
    private ArrayList<Point> points;

    private int borderColor = 0;
    private int fillColor = 0;

    private static final int TWO = 2;
    private static final int NUM_POINTS = 3;
    public Triangle(final String[] details) {
        points = new ArrayList<>();

        int i;
        for (i = 0; i < NUM_POINTS; i++) {
            points.add(new Point(
                    Integer.parseInt(details[i * TWO]),
                    Integer.parseInt(details[i * TWO + 1])
            ));
        }

        borderColor = Utils.getColor(details[i * TWO],
                Integer.parseInt(details[i * TWO + 1]));

        i++;

        fillColor = Utils.getColor(details[i * TWO],
                Integer.parseInt(details[i * TWO + 1]));
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

    public String toString() {
        return "TRIANGLE " + points.get(0).x + " " + points.get(0).y
                + " " + points.get(1).x + " " + points.get(1).y
                + " " + points.get(TWO).x + " " + points.get(TWO).y
                + " " + Utils.getHexAndAlpha(borderColor)
                + " " + Utils.getHexAndAlpha(fillColor);
    }
}
