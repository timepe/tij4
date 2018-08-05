package chapter11.holdobjects;

import java.util.*;

/**
 * Created by timepe on 18/8/5.
 */
public class MoviesGenerator {
    public final static String[] favoriteMovies = {
            "Chicken Run",
            "The power of Thrown",
            "My heart will go on",
            "My God",
            "Just do it",
            "Nothing is impossible"
    };

    public static void fill(Collection<String> c, int size) {
        int i = 0;
        while (i < size) {
            Collections.addAll(c, favoriteMovies[i%favoriteMovies.length]);
            i++;
        }
    }

    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();
        MoviesGenerator.fill(c, 8);
        System.out.println(c);

        c = new LinkedList<String>();
        MoviesGenerator.fill(c, 8);
        System.out.println(c);

        /**
         * 按照最快查找速度来存放
         */
        c = new HashSet<String>();
        MoviesGenerator.fill(c, 8);
        System.out.println(c);
        /**
         * 按照元素的排序来存放
         */
        c = new TreeSet<String>();
        MoviesGenerator.fill(c, 8);
        System.out.println(c);

        /**
         * 按照元素的插入顺序来存放
         */
        c = new LinkedHashSet<String>();
        MoviesGenerator.fill(c, 8);
        System.out.println(c);
    }
}
