package chapter16.arrays;

import chapter15.generics.Generator;

import java.util.Random;

public class RandomGenerator {
    public static Random r = new Random(47);
    public static class
    Boolean implements Generator<java.lang.Boolean> {
        public java.lang.Boolean next() {
            return r.nextBoolean();
        }
    }


    public static  class
    Byte implements Generator<java.lang.Byte> {
        public java.lang.Byte next() {
            return (byte) r.nextInt();
        }
    }

    public static class
    Character implements Generator<java.lang.Character> {
        public java.lang.Character next() {
            char[] chars = CountingGenerator.chars;
            return chars[r.nextInt(chars.length)];
        }
    }

    public static class
    String implements Generator<java.lang.String> {
//        Generator<java.lang.Character> cg = new Character();
//        public String() {}
//        public String(int length) {
//            this.length = length;
//        }
        Character rc = new Character();
        public java.lang.String next() {
            int len = r.nextInt(20);
            char[] buf = new char[len];
            for (int i = 0; i < len; i++) {
                buf[i] = rc.next();
            }
            return new java.lang.String(buf);
        }
    }


    public static class
    Short implements Generator<java.lang.Short> {
        public java.lang.Short next() {return (short)r.nextInt();}
    }

    public static class
    Integer implements Generator<java.lang.Integer> {
        public java.lang.Integer next() {return r.nextInt();}
    }

    public static class
    Long implements Generator<java.lang.Long> {
        public java.lang.Long next() {return r.nextLong();}
    }


    public static class
    Float implements Generator<java.lang.Float> {
        public java.lang.Float next() {
            return r.nextFloat();
        }
    }


    public static class
    Double implements Generator<java.lang.Double> {
        public java.lang.Double next() {
            return r.nextDouble();
        }
    }
}
