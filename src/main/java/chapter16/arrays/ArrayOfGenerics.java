package chapter16.arrays;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfGenerics {
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        /*
          可以将la向下转型为ls, ls将提供编译器检查。
         */
        ls = (List<String>[])la;
        ls[0] = new ArrayList<String>();

        Object[] objects = ls;
        objects[1] = new ArrayList<Integer>();

    }
}
