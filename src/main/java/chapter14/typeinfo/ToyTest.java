package chapter14.typeinfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}
interface Cats {}

class Toy {
    /* Class.newInstance 通过类的默认构造函数来实现对象的创建 */
    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy
        implements HasBatteries, Waterproof, Shoots, Cats{
    FancyToy() {super(1);}
}

class PrintInherit {
    public static void print(Class c) {
        ArrayList<String> names = new ArrayList<String>();
        while(c != null) {
            names.add(c.getSimpleName());
            c = c.getSuperclass();
        }

        //System.out.println(String.join("->", names));
    }

    public static void printDeclaredFields(Class c) {
        while(c != null) {
            System.out.println(c.getSimpleName() + ": ");
            for(Field field : c.getDeclaredFields()) {
                System.out.println(field);
            }
            System.out.println("-----------------");
            c = c.getSuperclass();
        }
    }
}

public class ToyTest {
    static void printInfo(Class cc) {
        System.out.println("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name: " + cc.getSimpleName());
        System.out.println("Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("chapter14.typeinfo.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }

        printInfo(c);
        for (Class face : c.getInterfaces()) {
            printInfo(face);
        }

        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());

        PrintInherit.print(c);
        PrintInherit.print(chapter14.typeinfo.Circle.class);
        PrintInherit.print(new chapter14.typeinfo.Circle().getClass());

        //PrintInherit.printDeclaredFields(c);
        PrintInherit.printDeclaredFields(String.class);
    }
}
