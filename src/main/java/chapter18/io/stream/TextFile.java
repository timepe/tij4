package chapter18.io.stream;

import java.io.*;

public class TextFile {
    public static String read(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            String line;
            try {
                while ((line = buffReader.readLine()) !=null){
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                buffReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static String read(String fileName) {
        return read(new File(fileName).getAbsoluteFile());
    }
}
