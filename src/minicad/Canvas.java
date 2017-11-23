package minicad;

import minicad.shapes.Circle;
import minicad.shapes.Diamond;
import minicad.shapes.Line;
import minicad.shapes.Triangle;
import minicad.shapes.Square;
import minicad.shapes.Polygon;
import minicad.shapes.Rectangle;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public final class Canvas implements Drawing {
    private int width = 0, height = 0;
    private BufferedImage surface = null;

    public Canvas(final int width, final int height, final String color, final int alpha) {
        initCanvas(width, height, color, alpha);
    }

    private static final int HEIGHT = 1;
    private static final int WIDTH = 2;
    private static final int COLOR = 3;
    private static final int ALPHA = 4;
    Canvas(final String canvasDetails) {
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

        floodFill(new Point(Math.round(height / 2), Math.round(width / 2)),
                Canvas.getColor(color, alpha));
    }

    public BufferedImage getSurface() {
        return surface;
    }

    private static int getColor(final String hex, final int alpha) {
        Color opaque = Color.decode(hex);

        return new Color(opaque.getRed(), opaque.getGreen(), opaque.getBlue(),
            alpha).getRGB();
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

        int target = surface.getRGB(start.x, start.y);
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
            || surface.getRGB(pos.x, pos.y) != target) {
            return false;
        }


        surface.setRGB(pos.x, pos.y, color);
        painted[pos.y][pos.x] = true;
        return true;
    }

    @Override
    public void draw(final Line shape) {

    }

    @Override
    public void draw(final Square shape) {

    }

    @Override
    public void draw(final Rectangle shape) {

    }

    @Override
    public void draw(final Circle shape) {

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
}
