package chapter18.io.stream;

import java.io.*;

public class StoringAndRecoveringData {
    public static void main(String[] args) {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")));
            try {
                out.writeDouble(3.1415926);
                out.writeUTF("派值");
                out.writeDouble(1.41413);
                out.writeUTF("根号2");

                System.out.println("---------------------------------");
                //out.write("By".getBytes());
                out.writeBytes("Bytes");
                out.writeInt(1);
                out.writeBoolean(true);
                out.writeShort(5);
                out.writeFloat((float)0.5);
                out.writeDouble(0.5);
                out.writeChar('c');
                out.writeChars("Ch");
                out.writeLong(20);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }

            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("data.txt")));
            try {
                System.out.println(in.readDouble());
                System.out.println(in.readUTF());
                System.out.println(in.readDouble());
                System.out.println(in.readUTF());

//                byte[] bytes = new byte[5];
//                in.read(bytes);
                System.out.println("-----------------------------");
                System.out.println(in.readByte());
                System.out.println(in.readByte());
                System.out.println(in.readByte());
                System.out.println(in.readByte());
                System.out.println(in.readByte());
                System.out.printf("%d-%d-%d-%d-%d\n", (int)'B', (int)'y', (int)'t', (int)'e', (int)'s');
                //System.out.println(in.readByte());

                System.out.println(in.readInt());
                System.out.println(in.readBoolean());
                System.out.println(in.readShort());
                System.out.println(in.readFloat());
                System.out.println(in.readDouble());
                System.out.println(in.readChar());
                System.out.println(in.readChar());
                System.out.println(in.readChar());
                System.out.println(in.readLong());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
