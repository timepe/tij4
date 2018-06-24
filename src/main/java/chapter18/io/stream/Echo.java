package chapter18.io.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Echo {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String line ;
        while((line = stdin.readLine()) != null && line.length() != 0) {
            System.out.println(line.toUpperCase());
        }
    }
}
