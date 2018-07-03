package chapter18.io.nio;

import java.nio.ByteBuffer;

public class GetData {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        /* 1. 创建一个ByteBuffer, 大小固定为BSIZE */
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0;
        /* 确定缓冲区大小 */
        while(i++ < bb.limit()) {
            if(bb.get() != 0)
                System.out.println("nonzero");
        }
        /* 缓冲区大小为1024， 比缓冲区大小大1*/
        System.out.println("i = " + i);
        bb.rewind();
        bb.asCharBuffer().put("Howdy!");

        char c;
        /* ByteBuffer具有get() 和 getChar() , CharBuffer只有get() */
        while((c = bb.getChar()) != 0) {
            System.out.print(c + " ");
        }
        System.out.println();
        bb.rewind();
        /* 创建ShortBuffer视图缓冲区 */
        bb.asShortBuffer().put((short) 471142);
        System.out.println(bb.getShort());
        bb.rewind();

        bb.asIntBuffer().put(99471142);
        System.out.println(bb.getInt());
        bb.rewind();

        bb.asLongBuffer().put(99471142);
        System.out.println(bb.getLong());
        bb.rewind();

        bb.asFloatBuffer().put(99471142);
        System.out.println(bb.getFloat());
        bb.rewind();

        bb.asDoubleBuffer().put(99471142);
        System.out.println(bb.getDouble());
        bb.rewind();
    }
}
