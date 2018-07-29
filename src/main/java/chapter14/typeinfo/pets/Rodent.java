package chapter14.typeinfo.pets;

public class Rodent extends Pet {
    public Rodent(String name) {
        super(name);
    }

    public Rodent() {
        super();
    }

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Rodent> {
        public Rodent create() {
            return new Rodent();
        }
    }

}
