package minicad;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public final class Tester {
    private Tester() {
    }

    public static void main(final String[] args) {
        ShapeGenerator generator = ShapeGenerator.getInstance();

        //Canvas canvas = new Canvas("CANVAS 500 500 #3E9070 100");
        Canvas canvas = new Canvas("CANVAS 481 487 #3E9070 100");
        canvas.addShape(generator.generate("TRIANGLE 10 10 100 100 10 50 #0000FF 255 #FF0000 100"));
        canvas.addShape(generator.generate("DIAMOND 200 200 50 100 #00FF00 255 #FF0000 255"));

        canvas.drawAll();

        File outputFile = new File("/home/george/poo.png");
        try {
            ImageIO.write(canvas.getSurface(), "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
