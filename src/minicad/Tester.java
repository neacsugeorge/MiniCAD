package minicad;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public final class Tester {
    private Tester() {
    }

    public static void main(final String[] args) {
        ShapeGenerator generator = ShapeGenerator.getInstance();
        generator.generate("SQUARE 147 652 497 #578278 100 #9F8A92 100");

        Canvas canvas = new Canvas("CANVAS 656 762 #FB293F 100");

        File outputFile = new File("/home/george/poo.png");
        try {
            ImageIO.write(canvas.getSurface(), "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
