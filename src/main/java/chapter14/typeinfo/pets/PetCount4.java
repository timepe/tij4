package chapter14.typeinfo.pets;

import java.util.List;

/**
 * Created by timepe on 18/7/28.
 */
public class PetCount4 {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        //List<Pet> pets = new LiteralPetCreator().arrayList(20);
        List<Pet> pets = new ForNameCreator().arrayList(20);
        for(Pet pet : pets) {
            counter.count(pet);
        }
        System.out.println(counter);
    }
}
