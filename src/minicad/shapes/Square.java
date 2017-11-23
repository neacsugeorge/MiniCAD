package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;

public final class Square implements Shape {
    public Square(final String details) {
        System.out.println(details);
    }

    @Override
    public void accept(final Drawing drawing) {
        drawing.draw(this);
    }
}
