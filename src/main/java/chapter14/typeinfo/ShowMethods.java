package chapter14.typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将ShoMethods改为非public， 程序仍然可以运行
 * 不过编译器不再为ShowMethods合成默认的构造器
 */
public class ShowMethods {
    private static Pattern p = Pattern.compile("\\w+\\.|final |native ");
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c = Class.forName("[Ljava.lang.String;");
//        Class<?> c = Class.forName("chapter14.typeinfo.ShowMethods");
//        Class<?> c = chapter14.typeinfo.pets.TypeCounter.class;
//        Class<?> c = java.lang.String.class;
        // getMethods 方法获取类的所有方法，包括从父类继承的方法
        // getDeclaredMethods 方法获取所有该类自己声明的方法，不包括从父类继承的方法。
        Method[] methods = c.getMethods();
        Constructor[] ctors = c.getConstructors();
        for (int i = 0; i < ctors.length; i++) {
            Matcher m = p.matcher(ctors[i].toString());
            System.out.println(m.replaceAll(""));
        }
        for (int i = 0; i < methods.length; i++) {
            Matcher m = p.matcher(methods[i].toString());
            System.out.println(m.replaceAll(""));
        }
    }
}
