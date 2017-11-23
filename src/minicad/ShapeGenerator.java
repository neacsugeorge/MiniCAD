package minicad;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ShapeGenerator {
    private static ShapeGenerator instance = new ShapeGenerator();

    private ShapeGenerator() {
    }

    public static ShapeGenerator getInstance() {
        return instance;
    }

    public Shape generate(final String shapeDetails) {
        String shapeType = shapeDetails.substring(0, shapeDetails.indexOf(" ")).toLowerCase();
        shapeType = "minicad.shapes."
                + Character.toUpperCase(shapeType.charAt(0))
                + shapeType.substring(1);

        Shape shape = null;

        try {
            Constructor<?> constructor = Class.forName(shapeType).getConstructor(String.class);
            shape = (Shape) constructor.newInstance(
                    shapeDetails.substring(shapeDetails.indexOf(" ") + 1));
        } catch (NoSuchMethodException
                | ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println(shapeType + " " + shape);

        return null;
    }
}
