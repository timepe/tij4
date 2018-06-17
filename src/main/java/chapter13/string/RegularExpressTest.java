package chapter13.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressTest {
    private static final String content = "Java now has regular expressions";

    static public final String POEM =
            "Twas brlling, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves, \n" +
                    "And the mome raths outgrabe.\n\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch.\n" +
                    "Beware the Jubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";

    public static String dateFormat(String dashDate) {
        /**
         *  默认的Pattern为当行模式, 只匹配最后一行；
         *  如存在多行需要匹配，需要使用Pattern.MULTILINE模式或者在正则表达式中加上(?m)
         */
        Pattern p = Pattern.compile("(?m)(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher m = p.matcher(dashDate);

        if (!m.matches()) {
            System.out.println("no illegal date format.");
            return null;
        }

        return m.group(1) + m.group(2) + m.group(3);
    }

    public static boolean matches(String regex) {
        //1. 建立正则表达式
        Pattern p = Pattern.compile(regex);
        //2. 使用正则表达式匹配文本
        Matcher m = p.matcher(content);
        //3. 查找匹配
        if (m.find()) return true;
        return false;
    }

    /*
     * 将一串数字转化为另一串数字  2017-08-14 -> bjag-jh-ad
     * 1 -> a
     * 2 -> b
     * 3 -> c
     * 4 -> d
     * 5 -> e
     * 6 -> f
     * 7 -> g
     * 8 -> h
     * 9 -> i
     * 0 -> j
     */
    public static String appendReplace(String text) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        StringBuffer sbuf = new StringBuffer();
        /*
        *  appendReplacement适用于正则表达式定位，然后对匹配到的字符进行调整
        *  的情况。而这种调整相对而言因匹配到的字符不同而有响应的改变。这是
        *  replaceAll/replace所无法做到的
        */
        while (m.find()) {
            m.appendReplacement(sbuf, translate(m.group()));
        }
        m.appendTail(sbuf);

        return sbuf.toString();
    }

    private static String translate(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '1':  sb.append("a"); break;
                case '2': sb.append("b"); break;
                case '3': sb.append("c"); break;
                case '4': sb.append("d"); break;
                case '5': sb.append("e"); break;
                case '6': sb.append("f"); break;
                case '7': sb.append("g"); break;
                case '8': sb.append("h"); break;
                case '9': sb.append("i"); break;
                case '0': sb.append("j"); break;
                default: sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void groupMatches() {
        Matcher m = Pattern.compile("(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
        while(m.find()) {
            for (int j = 0; j <= m.groupCount(); j++) {
                /*
                * 第一行结果为 [the slithy toves]	[the]	[slithy toves]	[slithy]	[toves]
                * TODO： group行为比较怪异, group(0) 显示 [the slithy toves], group(1) 显示[the], 但问题是为什么group(2)显示 [slithy toves]
                * FIXED: 所以问题的关键在于有几个括号, (?m)为模式匹配标志，不计算在内。
                * (\S+)\s+((\S+)\s+(\S+)) 标记4个括号， 其结果为$0, $1, $2$3, $2, $3
                */
                System.out.print("[" + m.group(j) + "]\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("^Java: " + matches("^Java"));
        System.out.println("\\Breg.*: " + matches("\\Breg.*"));
        System.out.println("n.w\\s+h(a|i)s: " + matches("n.w\\s+h(a|i)s"));
        System.out.println("s?: " + matches("s?"));
        System.out.println("s+: " + matches("s+"));
        System.out.println("s*: " + matches("s*"));
        System.out.println("s{4}: " + matches("s{4}"));
        System.out.println("s{1}.: " + matches("s{1}."));
        System.out.println("s{0,3}: " + matches("s{0,3}"));

        groupMatches();

        System.out.println(dateFormat("2018-06-15"));
        System.out.println(dateFormat("2018-06-15."));
        System.out.println(dateFormat("2018-06-.15"));
        System.out.println(dateFormat("2018-0615"));
        System.out.println(dateFormat(".2018-0615"));

        System.out.println(appendReplace("2017-08-14"));
    }
}
