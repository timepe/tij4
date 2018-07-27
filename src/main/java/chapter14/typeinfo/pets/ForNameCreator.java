package chapter14.typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types =
            new ArrayList<Class<? extends Pet>>();

    private static String[] typeNames = {
            "chapter14.typeinfo.pets.Mutt",
            "chapter14.typeinfo.pets.Pug",
            "chapter14.typeinfo.pets.EgyptianMau",
            "chapter14.typeinfo.pets.Manx",
            "chapter14.typeinfo.pets.Cymric",
            "chapter14.typeinfo.pets.Rat",
            "chapter14.typeinfo.pets.Mouse",
            "chapter14.typeinfo.pets.Hamster"
    };

    private static void loader() {
        try {
            for (String name : typeNames) {
                types.add((Class<? extends Pet>) Class.forName(name));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static {loader();}
    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}
