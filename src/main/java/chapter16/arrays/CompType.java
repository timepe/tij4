package chapter16.arrays;

import chapter15.generics.Generator;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.Random;

public class CompType implements Comparable<CompType> {
    int i;
    int j;
    private static int count = 1;

    public CompType(int n1, int n2) {
        i = n1;
        j = n2;
    }

    public String toString() {
        String result = "[i = " + i + ", j = " + j + "]";
        if (count++ % 3 == 0) {
            result += "\n";
        }
        return result;
    }

    public int compareTo(CompType o) {
        return (i < o.i ? -1 : (i == o.i ? 0 : 1));
    }

    private static Random r = new Random(47);
    public static Generator<CompType> generator() {
        return new Generator<CompType>() {
            public CompType next() {
                return new CompType(r.nextInt(100), r.nextInt(100));
            }
        };
    }

    public static void main(String[] args) {
//        CompType[] a = {new CompType(3, 2), new CompType(4,9), new CompType(18, 29),
//            new CompType(12, 15), new CompType(9, 5), new CompType(10, 8)};
        CompType [] a = new CompType[12];
        for (int i = 0; i < 12; i++) {
            a[i] = CompType.generator().next();
        }
        System.out.println("before sorting: ");
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println("after sorting: ");
        System.out.println(Arrays.toString(a));
    }
}
