import minicad.Canvas;
import minicad.ShapeGenerator;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        String inputFilePath = args[0];
//        String inputFilePath = "/home/george/Work/POO/teme/tema2/checker/input/test36.in";
        File inputFile = new File(inputFilePath);
        Scanner scanner;

        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int lines = scanner.nextInt() - 1;
        scanner.nextLine();
        String canvasDetails = scanner.nextLine();

        Canvas canvas = new Canvas(canvasDetails);
        ShapeGenerator generator = ShapeGenerator.getInstance();

        while (lines-- > 0) {
            canvas.addShape(generator.generate(scanner.nextLine()));
        }

        canvas.drawAll();

        File outputFile = new File("drawing.png");
        try {
            ImageIO.write(canvas.getSurface(), "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
