package chapter14.typeinfo.pets;

public class Rat extends Rodent{
    public Rat(String name) {
        super(name);
    }

    public Rat() {
        super();
    }

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Rat> {
        public Rat create() {
            return new Rat();
        }
    }

}
