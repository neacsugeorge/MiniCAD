package minicad;

import minicad.shapes.*;

public interface Drawing {
    void draw(Line shape);
    void draw(Square shape);
    void draw(Rectangle shape);
    void draw(Circle shape);
    void draw(Triangle shape);
    void draw(Diamond shape);
    void draw(Polygon shape);
}
