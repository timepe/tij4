package chapter14.typeinfo.pets;


public class Cymric extends Manx {
    public Cymric(String name) {
        super(name);
    }

    public Cymric() {
        super();
    }

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Cymric> {
        public Cymric create() {
            return new Cymric();
        }
    }
}
