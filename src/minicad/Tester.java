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
        canvas.addShape(generator.generate("CIRCLE 200 200 50 #0000FF 255 #FF0000 100"));
        //canvas.addShape(generator.generate("SQUARE 50 50 400 #C0EFDD 100 #F42280 100"));

        canvas.drawAll();

        File outputFile = new File("/home/george/poo.png");
        try {
            ImageIO.write(canvas.getSurface(), "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
