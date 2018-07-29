package chapter14.typeinfo.pets;

public class Mutt extends Dog {
    public Mutt(String name) { super(name);}
    public Mutt() {super();}

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Mutt> {
        public Mutt create() {
            return new Mutt();
        }
    }
}
