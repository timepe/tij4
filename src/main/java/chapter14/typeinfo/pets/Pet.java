package chapter14.typeinfo.pets;

public class Pet extends Individual {
    public Pet(String name) {super(name);}
    public Pet() { super(); }
    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<Pet> {
        public Pet create() {
            return new Pet();
        }
    }
}
