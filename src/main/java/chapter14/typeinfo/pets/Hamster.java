package chapter14.typeinfo.pets;

public class Hamster extends Rodent {
    public Hamster(String name) {
        super(name);
    }

    public Hamster() {
        super();
    }

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Hamster> {
        public Hamster create() {
            return new Hamster();
        }
    }

}
