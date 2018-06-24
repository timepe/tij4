package chapter18.io.stream;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextArray {
    private File inputFile;
    private ArrayList<String> textList = new ArrayList<String>();

    public TextArray(String inputFilePath) {
        inputFile = new File(inputFilePath);
    }

    public void loadFileToList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            try {
                String line;
                while ((line = reader.readLine()) !=null){
                    textList.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void grep(String regex) {
        Pattern p = Pattern.compile(regex);
        int lineNo = 1;
        for(String line : textList) {
            Matcher m = p.matcher(line);
            if (m.find()) {
                System.out.println(lineNo + ": " + line);
            }
            lineNo++;
        }
    }

    public void reversePrint() {
        for(int i=textList.size() - 1; i >= 0; i--) {
            System.out.println(textList.get(i).toUpperCase());
        }
    }

    public void writeWithNumber(String fileToWrite) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileToWrite)));
            int lineNo = 1;
            for(String line : textList) {
                String newLine =  lineNo + ": " + line;
                printWriter.write(newLine + "\n");
                lineNo++;
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TextArray textArray = new TextArray("src/main/java/chapter13/string/BinaryFile.java");
        textArray.loadFileToList();
        textArray.reversePrint();

        textArray.grep("File");

        textArray.writeWithNumber("write.txt");
    }

}
