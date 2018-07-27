package chapter14.typeinfo.pets;

import chapter17.deepcontainers.MapData;

import java.util.LinkedHashMap;
import java.util.Map;

public class PetCount3 {
    static  class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        //初始化一个Map，并将所有的key映射为0
        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes, 0));
        }

        public void count(Pet pet) {
            for(Map.Entry<Class<? extends  Pet>, Integer> pair : entrySet()) {
                if(pair.getKey().isInstance(pet))
                    put(pair.getKey(), pair.getValue() + 1);
            }
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("{");
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                result.append(pair.getKey().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(", ");
            }
            // StringBuilder.delete删除区间[start, end)的内容。
            result.delete(result.length() - 2, result.length());
            result.append("}");
            return result.toString();
        }
    }

    public static void main(String[] args) {
        PetCounter petCount = new PetCounter();
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            petCount.count(pet);
        }
        System.out.println();
        System.out.println(petCount);
    }
}
