package chapter18.io.nio;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        IntBuffer ib = bb.asIntBuffer();
        /* 可以在IntBuffer中put一个整型数组 */
        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016, 1000});
        System.out.println(ib.get(3));
        /* 修改视图中索引为3的值 */
        ib.put(3, 1811);
        ib.flip();
        /* 修改完再读取需要flip()*/
        while (ib.hasRemaining()) {
            int i = ib.get();
            System.out.println(i);
        }

        System.out.println("\n==============================");
//        while(bb.hasRemaining()) {
//            System.out.println(bb.get());
//        }

//        bb.rewind();
        IntBuffer ib2 = ((ByteBuffer) bb.rewind()).asIntBuffer();
        while (ib2.hasRemaining())
            System.out.println(ib2.get());

        System.out.println("\n==============================");
        bb = ByteBuffer.wrap(new byte[] {0, 0, 0, 0, 0, 0, 0, 'a'});
        DoubleBuffer db = bb.asDoubleBuffer();
        db.rewind();
        while(db.hasRemaining()) {
            System.out.println(db.get());
        }

    }
}
