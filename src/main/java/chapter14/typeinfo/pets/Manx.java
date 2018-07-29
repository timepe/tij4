package chapter14.typeinfo.pets;

public class Manx extends Cat {
    public Manx(String name) {
        super(name);
    }

    public Manx() {
        super();
    }

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Manx> {
        public Manx create() {
            return new Manx();
        }
    }
}
