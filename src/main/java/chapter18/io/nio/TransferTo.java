package chapter18.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }

        FileChannel out = new FileOutputStream(args[1]).getChannel(),
                in = new FileInputStream(args[0]).getChannel();
        System.out.println(in.size());
        in.transferTo(0, in.size(), out);
    }
}
