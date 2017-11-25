package minicad;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public static void print(final BufferedImage surface, final String filename) {
        File outputFile = new File(filename);
        try {
            ImageIO.write(surface, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
