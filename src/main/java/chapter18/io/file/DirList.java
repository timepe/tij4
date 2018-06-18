package chapter18.io.file;

import chapter18.io.stream.TextFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

class dirFilter implements FilenameFilter {
    private String regex;
    private String toMatch;

    public dirFilter(String regex, String toMatch) {
        this.regex = regex;
        this.toMatch = toMatch;
    }

    public boolean accept(File dir, String name) {
        Pattern p = Pattern.compile(regex);
        //return p.matcher(name).matches();
        if (p.matcher(name).matches()) {
            File file = new File(dir + "/"+ name).getAbsoluteFile();
            if (TextFile.read(file).contains(toMatch))
                return true;
        }

        return false;

    }
}
public class DirList {
    // args[0] 为过滤表达式, 若不输入则显示目录的全部内容。
    public static void main(String[] args) {
        String defaultPath = "./src/main/java/chapter18/io/file";
        File path = new File(defaultPath);
        String list[];
        if(args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new dirFilter(args[0], args[1]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        long sumFileSize = 0;
        for (String dirItem : list) {
            System.out.println(dirItem);
            File dirFile = new File(defaultPath + "/" + dirItem);
            if (dirFile.isFile()) {
                sumFileSize += dirFile.length();
            }
        }

        System.out.println("All file size: " + sumFileSize + " bytes");
    }
}
