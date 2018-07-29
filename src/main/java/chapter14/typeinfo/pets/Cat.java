package chapter14.typeinfo.pets;

public class Cat extends Pet {
    public Cat(String name) { super(name);}
    public Cat() { super();}

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Cat> {
        public Cat create() {
            return new Cat();
        }
    }
}
