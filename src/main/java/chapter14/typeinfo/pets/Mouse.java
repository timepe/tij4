package chapter14.typeinfo.pets;

public class Mouse extends Rodent {
    public Mouse(String name) {
        super(name);
    }

    public Mouse() {
        super();
    }

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Mouse> {
        public Mouse create() {
            return new Mouse();
        }
    }
}
