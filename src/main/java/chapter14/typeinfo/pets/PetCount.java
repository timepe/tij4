package chapter14.typeinfo.pets;

import java.util.HashMap;
import java.util.Random;

public class PetCount {
    /*
    static class PetCounter extends HashMap<String, Integer> {
        public void count(String type) {
            Integer quantity = get(type);
            if(quantity == null)
                put(type, 1);
            else
                put(type, quantity + 1);
        }
    }
    */

    /**
     *  静态内部类，继承/组合HashMap
     *  统计一个类型出现的次数
     */
    static class PetCounter {
        private HashMap<String, Integer> counter = new HashMap<String, Integer>();
        public void count(String type) {
            Integer quantity = counter.get(type);
            if(quantity == null)
                counter.put(type, 1);
            else
                counter.put(type, quantity + 1);
        }

        @Override
        public String toString() {
            return counter.toString();
        }
    }

    public static void countPets(PetCreator creator) {
        PetCounter counter = new PetCounter();
        for (Pet pet : creator.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            /**
             * 因instanceof的作用对象无法是Class对象，它直接使用类名作为参数，所以只能采用if/switch单独做判断
             * 通过Class.isInstance的方法判定---- 动态的方式进行判定
             */
            for(Class<? extends Pet> c : creator.types()) {
                if(c.isInstance(pet))
                    counter.count(c.getSimpleName());
            }
            /*
            if(pet instanceof Pet)
                counter.count("Pet");
            if(pet instanceof Dog)
                counter.count("Dog");
            if(pet instanceof Mutt)
                counter.count("Mutt");
            if(pet instanceof Pug)
                counter.count("Pug");
            if(pet instanceof Cat)
                counter.count("Cat");
            if(pet instanceof Manx)
                counter.count("Manx");
            if(pet instanceof EgyptianMau)
                counter.count("EgyptianMau");
            if(pet instanceof Cymric)
                counter.count("Cymric");
            if(pet instanceof Rodent)
                counter.count("Rodent");
            if(pet instanceof Rat)
                counter.count("Rat");
            if(pet instanceof Mouse)
                counter.count("Mouse");
            if(pet instanceof Hamster)
                counter.count("Hamster");
                */
        }
        System.out.println();
        System.out.println(counter);
    }

    public static void main(String[] args) {
        countPets(new ForNameCreator());
    }
}
