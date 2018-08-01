package chapter14.typeinfo.proxy;

interface Interface {
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {
    public void doSomething() {
        System.out.println("do something");
    }

    public void somethingElse(String arg) {
        System.out.println("something else " + arg);
    }
}

/**
 * 静态代理需要实现与被代理对象相同的接口；
 * 代理类需要指定被代理的接口，即需要为每一个接口实现一个代理类
 */
class SimpleProxy implements Interface {
    private Interface proxied;
    public static int count = 0;
    public SimpleProxy(Interface proxied){
        this.proxied = proxied;
    }
    public void doSomething() {
        count++;
        System.out.println("SimpleProxy do Something");
        proxied.doSomething();
    }

    public void somethingElse(String arg) {
        System.out.println("SimpleProxy do something else");
        proxied.somethingElse(arg);
    }
}

public class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
        consumer(new SimpleProxy(new RealObject()));
        System.out.println(SimpleProxy.count);
    }
}
