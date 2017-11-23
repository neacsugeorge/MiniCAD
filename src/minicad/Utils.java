package minicad;

import java.awt.Color;

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

        return "#" + Integer.toHexString(rgba.getRed())
                + Integer.toHexString(rgba.getGreen())
                + Integer.toHexString(rgba.getBlue())
                + " " + rgba.getAlpha();
    }
}
