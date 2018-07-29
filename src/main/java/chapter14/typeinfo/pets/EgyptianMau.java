package chapter14.typeinfo.pets;

public class EgyptianMau extends Cat {
    public EgyptianMau(String name) { super(name);}
    public EgyptianMau() {super();}

    public static class PetFactory implements chapter14.typeinfo.pets.PetFactory<EgyptianMau> {
        public EgyptianMau create() {
            return new EgyptianMau();
        }
    }
}
