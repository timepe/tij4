package chapter14.typeinfo.pets;

import java.util.ArrayList;

public class Pets2 {
    public static final PetCreator2 creator =
            new PetCreator2();
    public static Pet randomPet() {
        return creator.randomPet();
    }

    public static Pet[] createArray(int size) {
        return creator.createArray(size);
    }

    public static ArrayList<Pet> arrayList(int size) {
        return creator.arrayList(size);
    }

    public static void main(String[] args) {
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
        }
        System.out.println();
    }
}
