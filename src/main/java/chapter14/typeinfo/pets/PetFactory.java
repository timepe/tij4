package chapter14.typeinfo.pets;

public interface PetFactory<T> {
    T create();
}
