package chapter18.io.serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);
    public abstract int getColor();

    public Shape(int xVal, int yVal, int dimension) {
        xPos = xVal;
        yPos = yVal;
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return getClass() +
                "color[" + getColor() + "] xPos[" + xPos +
                "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }

    public static Shape randomFactory() {
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
        }
    }
}

class Circle extends Shape {
    private static int color = RED;

    public Circle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {return color;}
}


class  Square extends Shape {
    private static int color;

    public Square(int xVal, int yVal, int dimension) {
        super(xVal, yVal, dimension);
        color = RED;
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

class Line extends Shape {
    private static int color = RED;
    public static void serilizeStaticState(ObjectOutputStream os) throws IOException {
        os.writeInt(color);
    }
    public static void deserializeStaticState(ObjectInputStream os) throws IOException {
        color = os.readInt();
    }

    public Line(int xVal, int yVal, int dimension) {
        super(xVal, yVal, dimension);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}
public class StoreCADState {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class<? extends Shape>> shapeTypes =
                new ArrayList<Class<? extends Shape>>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);
        List<Shape> shapes = new ArrayList<Shape>();
        for (int i = 0; i < 10; i++) {
            shapes.add(Shape.randomFactory());
        }
        for (int i = 0; i < 10; i++) {
            shapes.get(i).setColor(Shape.GREEN);
        }

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CADState.out"));
        // 序列化Class类根本不会导致静态变量被序列化到内存中。
        out.writeObject(shapeTypes);
        Line.serilizeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);

        /*
         * 直接在本方法中反序列化shapes，由于内存中已经存在一个版本，
         * 则会将shapes2 指向shape所指向的地址。
         * 如在新的进程中反序列化shapes，则不会出现上述问题。
         */

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
        List<Class<? extends Shape>> shapeTypes2 =
                (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);

        List<Shape> shapes2 = (List<Shape>) in.readObject();
        System.out.println(shapes2);
    }
}
