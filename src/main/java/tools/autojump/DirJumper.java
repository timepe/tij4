package tools.autojump;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirJumper {
    //登记簿
    private Map<String, Integer> register;
    //文件读写
    private String storeFilePath;

    public DirJumper() {
        this("dirjumper.txt");
    }

    public DirJumper(String storeFilePath) {
        register = new HashMap<String, Integer>();
        this.storeFilePath = storeFilePath;
    }

    /**
     * 将文件中的内容载入登记表
     * @throws IOException
     */
    private void fill() throws IOException {
        if(register == null)
            throw new NullPointerException();
        if(storeFilePath == null)
            throw new NullPointerException();

        BufferedReader in = new BufferedReader(new FileReader(storeFilePath));
        Pattern p = Pattern.compile("([^\\s]+)\\s+([^\\s]+)$");
        String line;
        while((line = in.readLine()) != null) {
            Matcher m = p.matcher(line);
            if(m.matches()){
                String key = m.group(1);
                String value = m.group(2);
                register.put(key, Integer.parseInt(value));
            }
        }
        in.close();
    }

    /**
     * 将登记表中的数据写入文件
     * @throws IOException
     */
    private void flush() throws IOException {
        if(register == null)
            throw new NullPointerException();
        if(storeFilePath == null)
            throw new NullPointerException();

        PrintWriter out = new PrintWriter(storeFilePath);
        for (Map.Entry<String, Integer> e : register.entrySet()) {
            out.println(e.getKey() + "\t" + e.getValue());
        }
        out.close();
    }

    /**
    *  记录一次path的次数
    */
    public void count(String path) {
        if (path == null)
            throw new NullPointerException();
        Integer ig = register.get(path);
        if(ig == null) {
            ig = 0;
        }
        register.put(path, ++ig);
    }

    /**
     * 根据字符串获取最匹配的路径
     * @param s 匹配字符串
     * @return
     */
    public String mostVistedPath(String s) {
        Pattern p = Pattern.compile(".*" + s + ".*");
        Matcher m;
        int mostVistedNum = 0;
        String mostVistedItem = null;
        for (Map.Entry<String, Integer> rec : register.entrySet()) {
            m = p.matcher(rec.getKey());
            if(m.find() && rec.getValue() > mostVistedNum) {
                mostVistedNum = rec.getValue();
                mostVistedItem = rec.getKey();
            }
        }

        return mostVistedItem;
    }


    public static void main(String[] args) throws IOException{
        DirJumper dj = new DirJumper();
        dj.count("D:/Documents/Books");
        dj.count("D:/Documents/Dev");
        dj.count("D:/Documents/Dev/Java");
        dj.count("D:/Documents/Dev/Java");
        dj.count("D:/Documents/Dev/C");
        dj.count("D:/Documents/Books");
        dj.count("D:/Documents/Dev/C");
        dj.count("D:/Documents/Books");
        dj.count("D:/Documents/Books");
        dj.flush();
        System.out.println(dj.mostVistedPath("Documents"));
        System.out.println(dj.mostVistedPath("Dev"));
    }
}
