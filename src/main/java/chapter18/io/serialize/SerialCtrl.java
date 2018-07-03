package chapter18.io.serialize;

import java.io.*;

public class SerialCtrl implements Serializable {
    private String a;
    private transient String b;

    public SerialCtrl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    @Override
    public String toString() {
        return a + "\n" + b;
    }
    /*
     *　如果屏蔽该方法，则ObjectInputStream读取异常。
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }
    /*
     *  如果屏蔽该方法，则会导致ObjectInputStream读取出来的对象b值为空。
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b = (String)stream.readObject();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtrl sc = new SerialCtrl("Test1", "Test2");
        System.out.println("Before:\n" + sc);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(buf);
        o.writeObject(sc);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        SerialCtrl sc2 = (SerialCtrl)in.readObject();
        System.out.println("After:\n" + sc2);
    }
}
