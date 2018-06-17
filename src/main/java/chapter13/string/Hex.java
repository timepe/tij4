package chapter13.string;

import java.io.IOException;

public class Hex {
    public static String format(byte[] data) {
        StringBuilder result = new StringBuilder();
        /*
        for (int n = 0; n < data.length; n++) {
            if (n % 16 == 0) {
                result.append(String.format("%05X\t", n));
            }
            result.append(String.format("%02X\t", data[n]));
            if (n % 16 == 15) {
                result.append("\n");
            }
        }
        */
        int n = 0;
        for (byte b : data) {
            if (n % 16 == 0) result.append(String.format("%05X\t", n));
            result.append(String.format("%02X\t", b));
            n++;
            if (n % 16 == 0) result.append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException{

        System.out.println(Hex.format(BinaryFile.read("target/classes/chapter13/string/Hex.class")));
    }
}
