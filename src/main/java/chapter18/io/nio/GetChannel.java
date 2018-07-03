package chapter18.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception{
        /* 1. 从FileOutputStream中获取Channel. */
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        /* 2. 向channel中写入数据 , 类型为ByteBuffer. */
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        /* 1. 从RandomAccessFile中获取channel. */
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size());
        /* 2. 向channel中写入数据 */
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        /* 1. 从FileOutputStream中获取channel. */
        fc = new FileInputStream("data.txt").getChannel();
        /* 2. 分配一个固定大小的ByteBuffer. */
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        /* 3. 将channel中的数据读入到buff中(如果没有这个步骤，是无法读取成功的) */
        fc.read(buff);
        /* 4. 准备开始读取数据 */
        buff.flip();
        /* 5. 循环读取数据 */
        while(buff.hasRemaining())
            System.out.println((char)buff.get());
    }
}
