package chapter17.deepcontainers;

import chapter15.generics.Generator;
import javafx.util.Pair;

import java.util.LinkedHashMap;

public class MapData<K, V> extends LinkedHashMap<K, V> {
    public MapData(Iterable<K> genK, V value) {
        for (K key : genK) {
            put(key, value);
        }
    }

    public static <K, V> MapData<K, V>
        map(Iterable<K> genK, V value) {
        return new MapData<K, V>(genK, value);
    }
}
