package chapter14.typeinfo;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
    void draw() {
        System.out.println(this + ".draw()");
    }
    abstract public String toString();
}

class Circle extends Shape {
    @Override
    public String toString() {
        return "Circle";
    }
}

class Triangle extends Shape {
    @Override
    public String toString() {
        return "Triangle";
    }
}

class Rhomboid extends Shape {
    @Override
    public String toString() {
        return "Rhomboid";
    }
}

class Square extends Shape {
    @Override
    public String toString() {
        return "Square";
    }
}


public class Shapes {
    public static void rotate(Shape shape) {
        if (shape instanceof Circle)
            ((Circle)shape).draw();
    }
    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
        for (Shape shape : shapeList) {
            //shape.draw();
            rotate(shape);
        }
        /*
        Shape s = new Rhomboid();
        Rhomboid r = (Rhomboid)s;
        s.draw();
        Circle c = (Circle)s;
        c.draw();
        */
    }
}
