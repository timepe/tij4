package chapter18.io.stream;

import chapter13.string.BinaryFile;
import chapter18.io.file.Directory;
import chapter18.io.file.ProcessFiles;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.IOException;

public class ClassVerify {
    public static Boolean veriry (File f) throws IOException {
        byte[] bytes = BinaryFile.read(f);
        if (bytes.length < 4) return false;

        String start = String.format("%x%x%x%x", bytes[0], bytes[1], bytes[2], bytes[3]);
        if (start.toUpperCase().equals("CAFEBABE")) return true;

        return false;
    }

    public static void main(String[] args) throws IOException {
        new ProcessFiles(new ProcessFiles.Strategy() {
            public void process(File file){
                try {
                    if(veriry(file)) {
                        System.out.println(file);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, ".*").start(args);
    }
}
