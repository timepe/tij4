package chapter18.io.stream;

import java.io.*;

public class BasicFileOutput {
    static String file = "BasicFileOutput.out";
    static String inFile = "src/main/java/chapter18/io/stream/BasicFileOutput.java";

    public static void main(String[] args) throws IOException {
        LineNumberReader in = new LineNumberReader(new FileReader(inFile));
        PrintWriter out = new PrintWriter(file);
        String s;
        while((s = in.readLine()) != null) {
            out.println(in.getLineNumber() + ": " + s);
        }
        out.close();

        long start, end;
        int i;
        BufferedReader inBuff = new BufferedReader(new FileReader(inFile));
        FileWriter writer;
        BufferedWriter buffWriter;
        start = System.currentTimeMillis();
        for(i = 0; i < 500; i++) {
            inBuff.mark(2048*8 + 1);
            writer  = new FileWriter(file);
            while((s = inBuff.readLine()) != null) {
//                writer.println(inBuff.getLineNumber() + ": " + s);
                writer.write(s + "\n");
            }
            writer.close();
            inBuff.reset();
        }
        end = System.currentTimeMillis();
        System.out.println("without buff: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        for(i = 0; i < 500; i++) {
            buffWriter = new BufferedWriter(new FileWriter(file));
            inBuff.mark(2048*8 + 1);
            while((s = inBuff.readLine()) != null) {
//                writer.println(inBuff.getLineNumber() + ": " + s);
                buffWriter.write(s + "\n");
            }
            buffWriter.close();
            inBuff.reset();
        }
        end = System.currentTimeMillis();
        System.out.println("with buff: " + (end - start) + " ms");


    }
}
