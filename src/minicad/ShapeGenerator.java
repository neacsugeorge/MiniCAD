package minicad;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class ShapeGenerator {
    private static ShapeGenerator instance = new ShapeGenerator();

    private ShapeGenerator() {
    }

    public static ShapeGenerator getInstance() {
        return instance;
    }

    private static final int SHAPE_TYPE = 0;
    public Shape generate(final String shapeDetails) {
        String[] details = shapeDetails.split(" ");
        String shapeType = details[SHAPE_TYPE].toLowerCase();
        shapeType = "minicad.shapes."
                + Character.toUpperCase(shapeType.charAt(0))
                + shapeType.substring(1);

        Shape shape = null;

        List detailsToPass = new LinkedList();
        Collections.addAll(detailsToPass, details);
        detailsToPass.remove(0);
        details = (String[]) detailsToPass.toArray(new String[detailsToPass.size()]);

        try {
            Constructor<?> constructor = Class.forName(shapeType).getConstructor(String[].class);
            shape = (Shape) constructor.newInstance((Object) details);
        } catch (NoSuchMethodException
                | ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println(shapeType + " " + shape);

        return shape;
    }
}
