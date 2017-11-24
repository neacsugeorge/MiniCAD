package minicad;

import minicad.shapes.Circle;
import minicad.shapes.Diamond;
import minicad.shapes.Line;
import minicad.shapes.Triangle;
import minicad.shapes.Square;
import minicad.shapes.Polygon;
import minicad.shapes.Rectangle;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public final class Canvas implements Drawing {
    private int width = 0, height = 0;
    private BufferedImage surface = null;
    private BufferedImage fillSurface = null;
    private ArrayList<Shape> shapes = null;

    public Canvas(final int width, final int height, final String color, final int alpha) {
        initCanvas(width, height, color, alpha);
    }

    private static final int HEIGHT = 1;
    private static final int WIDTH = 2;
    private static final int COLOR = 3;
    private static final int ALPHA = 4;
    public Canvas(final String canvasDetails) {
        String[] parts = canvasDetails.split(" ");
        initCanvas(
                Integer.parseInt(parts[WIDTH]),
                Integer.parseInt(parts[HEIGHT]),
                parts[COLOR],
                Integer.parseInt(parts[ALPHA])
        );
    }

    private void initCanvas(final int newWidth, final int newHeight,
                            final String color, final int alpha) {
        width = newWidth;
        height = newHeight;

        surface = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillSurface = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        shapes = new ArrayList<>();

        floodFill(new Point(Math.round(height / 2), Math.round(width / 2)),
                Utils.getColor(color, alpha));
    }

    public BufferedImage getSurface() {
        return surface;
    }

    private boolean isOutOfBounds(final Point pos) {
        if (pos.x < 0
            || pos.x >= width
            || pos.y < 0
            || pos.y >= height) {
            return true;
        }

        return false;
    }

    private void floodFill(final Point start, final int color) {
        Queue<Point> fillQueue = new LinkedList<>();
        boolean[][] painted = new boolean[height][width];

        int target = fillSurface.getRGB(start.x, start.y);
        fillQueue.add(start);

        while (!fillQueue.isEmpty()) {
            Point pos = fillQueue.remove();

            if (floodFillPaint(pos, target, color, painted)) {
                fillQueue.add(new Point(pos.x, pos.y - 1));
                fillQueue.add(new Point(pos.x, pos.y + 1));
                fillQueue.add(new Point(pos.x - 1, pos.y));
                fillQueue.add(new Point(pos.x + 1, pos.y));
            }
        }
    }

    private boolean floodFillPaint(final Point pos, final int target,
                                  final int color, final boolean[][] painted) {
        if (isOutOfBounds(pos)
            || painted[pos.y][pos.x]
            || target == color
            || fillSurface.getRGB(pos.x, pos.y) != target) {
            return false;
        }


        fillSurface.setRGB(pos.x, pos.y, color);
        surface.setRGB(pos.x, pos.y, color);
        painted[pos.y][pos.x] = true;
        return true;
    }

    public void addShape(final Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void draw(final Line shape) {
        Point start = shape.getStart();
        Point stop = shape.getStop();

        Point current = new Point(start);

        int deltaX = Math.abs(stop.x - start.x);
        int deltaY = Math.abs(stop.y - start.y);
        int sign1 = Integer.signum(stop.x - start.x);
        int sign2 = Integer.signum(stop.y - start.y);

        boolean interchanged = false;
        if (deltaY > deltaX) {
            deltaX += deltaY;
            deltaY = deltaX - deltaY;
            deltaX = deltaX - deltaY;
            interchanged = true;
        }

        int error = 2 * deltaY - deltaX;
        for (int i = 0; i <= deltaX; i++) {
            if (!isOutOfBounds(current)) {
                surface.setRGB(current.x, current.y, shape.getColor());
                fillSurface.setRGB(current.x, current.y, shape.getColor());
            }

            while (error > 0) {
                if (interchanged) {
                    current.setLocation(current.x + sign1, current.y);
                } else {
                    current.setLocation(current.x, current.y + sign2);
                }

                error -= 2 * deltaX;
            }

            if (interchanged) {
                current.setLocation(current.x, current.y + sign2);
            } else {
                current.setLocation(current.x + sign1, current.y);
            }

            error += 2 * deltaY;
        }
    }

    @Override
    public void draw(final Square shape) {
        Point start = new Point(shape.getStart());

        ShapeGenerator.getInstance()
                .generate("RECTANGLE"
                    + " " + start.x + " " + start.y
                    + " " + shape.getSize() + " " + shape.getSize()
                    + " " + Utils.getHexAndAlpha(shape.getBorderColor())
                    + " " + Utils.getHexAndAlpha(shape.getFillColor())).accept(this);
    }

    @Override
    public void draw(final Rectangle shape) {
        fillSurface = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Point cornerNW = new Point(shape.getStart());
        Point cornerNE = new Point(cornerNW.x + shape.getWidth() - 1, cornerNW.y);
        Point cornerSW = new Point(cornerNW.x, cornerNW.y + shape.getHeight() - 1);
        Point cornerSE = new Point(cornerNE.x, cornerSW.y);

        ShapeGenerator.getInstance()
                .generate("LINE"
                        + " " + cornerNW.x + " " + cornerNW.y
                        + " " + cornerNE.x + " " + cornerNE.y
                        + " " + Utils.getHexAndAlpha(shape.getBorderColor())).accept(this);
        ShapeGenerator.getInstance()
                .generate("LINE"
                        + " " + cornerSW.x + " " + cornerSW.y
                        + " " + cornerSE.x + " " + cornerSE.y
                        + " " + Utils.getHexAndAlpha(shape.getBorderColor())).accept(this);
        ShapeGenerator.getInstance()
                .generate("LINE"
                        + " " + cornerNW.x + " " + cornerNW.y
                        + " " + cornerSW.x + " " + cornerSW.y
                        + " " + Utils.getHexAndAlpha(shape.getBorderColor())).accept(this);
        ShapeGenerator.getInstance()
                .generate("LINE"
                        + " " + cornerNE.x + " " + cornerNE.y
                        + " " + cornerSE.x + " " + cornerSE.y
                        + " " + Utils.getHexAndAlpha(shape.getBorderColor())).accept(this);

        floodFill(shape.getCenter(width, height), shape.getFillColor());
    }

    @Override
    public void draw(final Circle shape) {
        fillSurface = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Point current = new Point(shape.getStart());

        int radius = shape.getRadius();

        int x = radius;
        int y = 0;

        Queue<Point> points = new LinkedList<>();
        points.add(new Point(current.x + x, current.y + y));

        points.add(new Point(current.x, current.y - x));
        points.add(new Point(current.x - x, current.y));

        if (radius > 0) {
            points.add(new Point(current.x + x, current.y - y));
            points.add(new Point(current.y + y, current.x + x));
            points.add(new Point(current.y - y, current.x + x));
        }

        int p = 1 - radius;

        while (x > y) {
            y++;

            if (p <= 0) {
                p = p + 2 * y + 1;
            } else {
                x--;
                p = p + 2 * y - 2 * x + 1;
            }

            if (x < y) {
                break;
            }

            points.add(new Point(current.x + x, current.y + y));
            points.add(new Point(current.x - x, current.y + y));
            points.add(new Point(current.x + x, current.y - y));
            points.add(new Point(current.x - x, current.y - y));

            if (x != y) {
                points.add(new Point(current.y + y, current.x + x));
                points.add(new Point(current.y - y, current.x + x));
                points.add(new Point(current.y + y, current.x - x));
                points.add(new Point(current.y - y, current.x - x));
            }
        }

        while (!points.isEmpty()) {
            Point toPaint = points.remove();
            if (isOutOfBounds(toPaint)) {
                continue;
            }

            surface.setRGB(toPaint.x, toPaint.y, shape.getBorderColor());
            fillSurface.setRGB(toPaint.x, toPaint.y, shape.getBorderColor());
        }

        floodFill(current, shape.getFillColor());
    }

    @Override
    public void draw(final Triangle shape) {

    }

    @Override
    public void draw(final Diamond shape) {

    }

    @Override
    public void draw(final Polygon shape) {

    }

    public void drawAll() {
        for (Shape shape : shapes) {
            shape.accept(this);
        }
    }
}
