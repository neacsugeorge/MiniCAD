package minicad.shapes;

import minicad.Drawing;
import minicad.Shape;

public final class Square implements Shape {
    @Override
    public void accept(final Drawing drawing) {
        drawing.draw(this);
    }
}
