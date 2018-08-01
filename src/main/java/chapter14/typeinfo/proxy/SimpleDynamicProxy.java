package chapter14.typeinfo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理： 可以代理任何对象，但是需要被代理对象实现
 * 否一个接口Interface， 并且在使用的时候需要将该接口传递给
 * newProxyInstance.
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    public static int count = 0;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    /*
     * proxy 为代理对象，proxy.anymethod()方法都会映射到
     * Interface.anymethod()， 进而调用invoke(proxy, method, args)方法
     * 形成循环
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        count ++;
        System.out.println("proxy: " + proxy.getClass());
        System.out.println("method: " + method);
        System.out.println(Arrays.toString(args));
        if (count <= 5) {
            System.out.println(proxy.toString());
        }
        return method.invoke(proxied, args);
    }
}

public class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("ly");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        Interface iface = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{ Interface.class},
        new DynamicProxyHandler(new RealObject()));
        consumer(iface);
    }
}
