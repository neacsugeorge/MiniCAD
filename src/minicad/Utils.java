package minicad;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Utils {
    private Utils() {

    }

    public static int getColor(final String hex, final int alpha) {
        Color opaque = Color.decode(hex);

        return new Color(opaque.getRed(), opaque.getGreen(), opaque.getBlue(),
                alpha).getRGB();
    }

    public static String getHexAndAlpha(final int color) {
        Color rgba = new Color(color, true);
        StringBuilder result = new StringBuilder("#");

        String red = Integer.toHexString(rgba.getRed()),
                green = Integer.toHexString(rgba.getGreen()),
                blue = Integer.toHexString(rgba.getBlue());

        if (red.length() < 2) {
            red = "0" + red;
        }
        if (green.length() < 2) {
            green = "0" + green;
        }
        if (blue.length() < 2) {
            blue = "0" + blue;
        }

        result.append(red);
        result.append(green);
        result.append(blue);
        result.append(" ");
        result.append(rgba.getAlpha());

        return result.toString();
    }

    private static final int SIX = 6;
    static Point getCentroid(final ArrayList<Point> points) {
        int numPoints = points.size();

        if (points.get(numPoints - 1).equals(points.get(0))) {
            numPoints--;
        } else {
            points.add(points.get(0));
        }

        int x = 0,
            y = 0;

        for (int i = 0; i < numPoints - 1; i++) {
            int factor = points.get(i).x * points.get(i + 1).y
                        - points.get(i + 1).x * points.get(i).y;

            x += (points.get(i).x + points.get(i + 1).x) * factor;
            y += (points.get(i).y + points.get(i + 1).y) * factor;
        }

        int factor = points.get(numPoints - 1).x * points.get(0).y
                - points.get(0).x * points.get(numPoints - 1).y;

        x += (points.get(numPoints - 1).x + points.get(0).x) * factor;
        y += (points.get(numPoints - 1).y + points.get(0).y) * factor;

        int area = Utils.getPolygonArea(points);

        x = x / SIX / area;
        y = y / SIX / area;

        return new Point(x, y);
    }

    private static int getPolygonArea(final ArrayList<Point> points) {
        int numPoints = points.size();

        if (points.get(numPoints - 1).equals(points.get(0))) {
            numPoints--;
        } else {
            points.add(points.get(0));
        }

        int area = 0;
        for (int i = 0; i < numPoints; i++) {
            int next = (i + 1) % numPoints;
            area += points.get(i).x * points.get(next).y - points.get(next).x * points.get(i).y;
        }

        return area / 2;
    }

    public static void print(final BufferedImage surface, final String filename) {
        File outputFile = new File(filename);
        try {
            ImageIO.write(surface, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
