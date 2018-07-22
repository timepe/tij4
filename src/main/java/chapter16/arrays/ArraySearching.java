package chapter16.arrays;


import chapter15.generics.Generator;

import java.util.Arrays;

public class ArraySearching {
    public static void main(String[] args) {
        Generator<Integer> gen = new RandomGenerator.Integer();
        int [] a = new int[50];
        for (int i = 0; i < 50; i++) {
            a[i] = gen.next();
        }
        Arrays.sort(a);
        System.out.println("sorted Array: " + Arrays.toString(a));
        while(true) {
            int r = gen.next();
            int location = Arrays.binarySearch(a, r);
            if (location >= 0) {
                System.out.println("Location of " + r + " is " + location +
                ", a[" + location + "] = " + a[location]);
                break;
            }
        }
    }
}
