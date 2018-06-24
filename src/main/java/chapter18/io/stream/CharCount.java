package chapter18.io.stream;

import java.util.HashMap;
import java.util.Map;

public class CharCount {
    private Map<Character, Integer> countMap = new HashMap<Character, Integer>();
    private String path;

    public CharCount(String path) {
        this.path = path;
    }

    public void count() {
        String content = TextFile.read(path);
        for(int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c)) continue;
            if (!countMap.containsKey(c)) {
                countMap.put(c, 0);
            }
            Integer value = countMap.get(c);
            value += 1;
            countMap.put(c, value);
        }

        System.out.println(countMap);
    }


    public static void main(String[] args) {
        CharCount cc = new CharCount("src/main/java/chapter18/io/stream/CharCount.java");
        cc.count();

    }
}
