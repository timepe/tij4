package chapter11.holdobjects;

import chapter14.typeinfo.pets.*;

import java.util.*;

/**
 * Created by timepe on 18/8/5.
 */
public class ListFeatures {
    public static void main(String[] args) {
        Random rand = new Random(47);
        List<Pet> pets = Pets.arrayList(7);
        System.out.println("1: " + pets);
        Hamster h = new Hamster();
        pets.add(h);
        System.out.println("2: " + pets);

        System.out.println("3: " + pets.contains(h));

        pets.remove(h);
        Pet p = pets.get(2);
        System.out.println("4: " + p + " " + pets.indexOf(p));

        Pet cymric = new Cymric();
        System.out.println("5: " + pets.indexOf(cymric));
        System.out.println("6: " + pets.remove(cymric));

        System.out.println("7: " + pets.remove(p));
        System.out.println("8: " + pets);

        pets.add(3, new Mouse());
        System.out.println("9: " + pets);

        List<Pet> sub = pets.subList(1, 4);
        System.out.println("subList: " + sub);

        System.out.println("10: " + pets.containsAll(sub));

        System.out.println("11: " + pets.containsAll(sub));
        //Collections.sort(sub);
        //使用随机数打乱collections
        Collections.shuffle(sub, rand);
        System.out.println("shuffled subList: " + sub);

        System.out.println("12: " + pets.containsAll(sub));

        List<Pet> copy = new ArrayList<Pet>(pets);
        sub = Arrays.asList(pets.get(1), pets.get(4));
        System.out.println("sub: " + sub);
        copy.retainAll(sub);
        System.out.println("13: " + copy);

        copy = new ArrayList<Pet>(pets);
        copy.remove(2);

    }
}
