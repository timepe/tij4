package chapter14.typeinfo.pets;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by timepe on 18/7/28.
 */
public class TypeCounter extends HashMap<Class<?>, Integer> {
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    /**
     * 统计obj出现的次数
     * @param obj
     */
    public void count(Object obj) {
        Class<?> type = obj.getClass();
        countClass(type);
    }

    /**
     * 递归计数
     * 对一个type计数,那么必须对其超类计数;
     * @param type
     */
    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        if(superClass != baseType &&
                baseType.isAssignableFrom(superClass))
            countClass(superClass);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("{");
        for(Map.Entry<Class<?>, Integer> e : entrySet()) {
            b.append(e.getKey().getSimpleName());
            b.append("=");
            b.append(e.getValue());
            b.append(", ");
        }

        if (entrySet().size() > 0)
            b.delete(b.length() - 2, b.length());
        b.append("}");
        return  b.toString();
    }
}
