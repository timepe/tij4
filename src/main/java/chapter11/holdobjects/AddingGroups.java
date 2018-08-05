package chapter11.holdobjects;

import java.util.*;

/**
 * Created by timepe on 18/8/5.
 */

class Snow {}
class Powder extends Snow {}
class Light extends Powder {}

public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        //Collection.addAll() 只支持添加另一个Collection作为参数
        collection.addAll(Arrays.asList(6, 7, 8, 9, 10));
        System.out.println(collection);
        Integer[] integers = new Integer[]{11, 12, 13, 14, 15};
        collection.addAll(Arrays.asList(integers));
        System.out.println(collection);
        //Collections.addAll() 支持可变参数
        Collections.addAll(collection, 16, 17, 18, 19, 20);
        System.out.println(collection);

        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        list.set(1, 99);
        System.out.println(list);
        /**
         *    无法使用Arrays.asList添加元素到List中
         *   List<Snow> snows = Arrays.asList(new Light(), new Powder());
         */
       List<Snow> snows = new ArrayList<Snow>();
        Collections.addAll(snows, new Light(), new Powder());
    }
}
