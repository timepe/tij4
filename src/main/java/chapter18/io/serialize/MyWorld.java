package chapter18.io.serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class House implements Serializable {}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() + "] " + preferredHouse + "\n";
    }
}

/**
 * 使用字节数组作为存储器来序列化一个对象，毕竟
 * ByteArrayInputStream/ByteArrayOutputStream  <===> FileInputStream/FileOutputStream
 * 没毛病.
 */
public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Moly the cat", house));
        System.out.println("animals: " + animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);

        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);

        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
        List animal1 = (List)in1.readObject();
        List animal2 = (List)in1.readObject();
        List animal3 = (List)in2.readObject();

        /*
        *  animal1中的对象和animal2中的对象具有相同的地址
        *  而animal3中对象地址和animal1，animal2中的地址
        *  不同。
        * */
        System.out.println("animal1: " + animal1);
        System.out.println("animal2: " + animal2);
        System.out.println("animal3: " + animal3);
    }
}
