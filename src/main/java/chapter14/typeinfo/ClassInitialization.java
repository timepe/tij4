package chapter14.typeinfo;

import java.util.Random;

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);

    /**
     * 类加载的三个步骤：
     * 1. 加载，查找字节码，并从这些字节码中创建一个Class对象
     * 2. 链接，验证类中的字节码，为静态域分配存储空间，并解析这个类创建的对其他类的所有引用
     * 3. 初始化，如果有超类，对其进行初始化，执行静态初始化器和静态初始化块。惰性初始化
     */
    public static void main(String[] args) throws Exception{
        /* 使用.class语法来获得对类的引用不会引发初始化 */
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        //static final是编译期常量，不需要对类进行初始化就可以获取
        System.out.println(Initable.staticFinal);
        System.out.println(Initable.staticFinal2);
        System.out.println(Initable2.staticNonFinal);
        // Class.forName会立即对类进行静态初始化
        Class initable3 = Class.forName("chapter14.typeinfo.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
