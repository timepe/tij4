package chapter14.typeinfo.pets;

public class Dog extends Pet {
    public Dog(String name) { super(name);}
    public Dog() {super();}

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Dog> {
        public Dog create() {
            return new Dog();
        }
    }

}
