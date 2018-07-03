package chapter18.io.nio;

import javafx.scene.layout.BackgroundSize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.out.println("arguments: sourcefile desfile");
            System.exit(1);
        }
        /* 1. 创建输入和输出流通道 */
        FileChannel in = new FileInputStream(args[0]).getChannel(),
                out = new FileOutputStream(args[1]).getChannel();
        /* 2. 分配一个固定大小的缓冲区 */
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        /* 3. 循环复制 */
        while (in.read(buffer) != -1) {
            buffer.flip();  // 读取数据之后，首先调用flip准备好缓冲区
            out.write(buffer); // 将缓冲区数据写入输出文件通道
            buffer.clear(); // 清空缓冲区
        }
    }
}
