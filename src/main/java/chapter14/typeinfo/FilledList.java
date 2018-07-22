package chapter14.typeinfo;

import java.util.ArrayList;
import java.util.List;

class CountedInteger {
    private static long counter;
    //new CountedInteger会导致counter+1，这样后面new出来的对象比前一个+1；
    private final long id = counter++;

    @Override
    public String toString() {
        return Long.toString(id);
    }
}
public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements) throws IllegalAccessException, InstantiationException {
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < nElements; i++) {
            result.add(type.newInstance());
        }

        return result;

    }

    public static void main(String[] args) {
        FilledList<CountedInteger> fl =
                new FilledList<CountedInteger>(CountedInteger.class);
        try {
            System.out.println(fl.create(15));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

