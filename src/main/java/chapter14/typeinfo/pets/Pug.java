package chapter14.typeinfo.pets;

public class Pug extends Dog {
    public Pug(String name) {super(name);}
    public Pug() { super();}

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Pug> {
        public Pug create() {
            return new Pug();
        }
    }
}
