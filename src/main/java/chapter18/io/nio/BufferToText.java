package chapter18.io.nio;

import javafx.scene.layout.BackgroundSize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        /*1. 创建channel, 并向其中写入数据 */
        FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        /* 2. 创建读通道，读取数据 */
        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();

        /* 3. 直接使用asCharBuffer是不行的， 数据乱码*/
        System.out.println(buff.asCharBuffer());
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        /* 使用与文件相同的编码解码字节， 使用nio中的Charset类 */
        System.out.println("Decoded using " + encoding
                + Charset.forName(encoding).decode(buff));
        /* 1. 创建写通道， 并写入UTF字节 */
        fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        /* 2. 读取字节到ByteBuffer， 并使用asCharBuffer成功读取 */
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
        /* 1. 创建一个输出通道，并使用buff.asCharBuffer().put向缓冲区中写入字符串 */
        fc = new FileOutputStream("data2.txt").getChannel();
        buff = ByteBuffer.allocate(24);
        buff.asCharBuffer().put("Some text");
        fc.write(buff);  // 此处会填充所有的24个字节， 不够的字节为随机数据。
        fc.close();
        /* 2. 创建一个输入通道， 并使用asCharBuffer读取数据。发现
         *  "Some text" 正常打印，但是分配的24个字节全部都打印出来了。
        */
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
        buff.rewind();
        printCharBuffer(buff);
    }

    public static void printCharBuffer(ByteBuffer bb) {
        CharBuffer charBuffer = bb.asCharBuffer();
        while(charBuffer.hasRemaining()){
            System.out.print(charBuffer.get());
        }
        System.out.println();
    }
}
