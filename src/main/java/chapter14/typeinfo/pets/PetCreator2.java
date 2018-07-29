package chapter14.typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 注册工厂模式： 每个类自建工厂，再由一个类（注册中心）进行统一的管理。
 * 每新建一个类，需要建立一个“自建工厂”，并将该工厂注册到注册中心。
 */
public class PetCreator2 {
    static List<Class<? extends PetFactory<? extends Pet>>> petFactories = new ArrayList<Class<? extends PetFactory<? extends Pet>>>();
    static {
        petFactories.add(Pet.PetFactory.class);
        petFactories.add(Cat.PetFactory.class);
        petFactories.add(Cymric.PetFactory.class);
        petFactories.add(EgyptianMau.PetFactory.class);
        petFactories.add(Hamster.PetFactory.class);
        petFactories.add(Manx.PetFactory.class);
        petFactories.add(Mouse.PetFactory.class);
        petFactories.add(Mutt.PetFactory.class);
        petFactories.add(Pug.PetFactory.class);
        petFactories.add(Rat.PetFactory.class);
        petFactories.add(Rodent.PetFactory.class);
    }
    private Random rand = new Random(47);
    public Pet randomPet() {
        int n = rand.nextInt(petFactories.size());
        try {
            return petFactories.get(n).newInstance().create();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomPet();
        }

        return result;
    }

    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> result = new ArrayList<Pet>();
        Collections.addAll(result, createArray(size));
        return result;
    }

}
