package chapter13.string;

import java.util.Arrays;

public class Splitting {
    private final static String thinking= "The available() method is used to produce the appropriate array size,"
                            + " and this paticular,version of the, overloaded read(), methodd fills the, array.";
    public static void split(String regex) {
        System.out.println(Arrays.toString(thinking.split(regex)));
    }

    public static boolean isSentence(String text) {
        /*
         *  .* 匹配任何字符
         */
        return text.matches("^[A-Z]+.*\\.$");
    }

    public static String replaceFirst(String regex, String replacement) {
        return thinking.replaceFirst(regex, replacement);
    }

    public static String replaceAll(String regex, String replacement) {
        return thinking.replaceAll(regex, replacement);
    }

    public static void main(String[] args) {
        // split
        Splitting.split("\\W+");
        Splitting.split(" ");
        Splitting.split(",\\W?");
        /*
         * | 的行为和其他操作符+?*很不一样， the|array匹配the 或者array,
         * 而the+匹配theee...这样的序列
         */
        Splitting.split("the|array");
        Splitting.split("the|\\W+");

        // match
        System.out.println(isSentence("My name is cf."));
        System.out.println(isSentence("app is not sentence."));
        System.out.println(isSentence("My sentence is not sentence"));
        System.out.println(isSentence("."));
        System.out.println(isSentence("M"));
        System.out.println(isSentence("M."));
        System.out.println(isSentence("My na.me"));
        System.out.println(isSentence("My name."));

        //replace
        System.out.println(replaceFirst("a|o|e|i|u", "_"));
        System.out.println(replaceAll("a|o|e|i|u", "_"));
    }
}
