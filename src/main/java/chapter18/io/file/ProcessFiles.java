package chapter18.io.file;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessFiles {
    public interface Strategy {
        void process(File file);
    }
    private Strategy strategy;
//    private String ext;  //文件扩展名
    private String regex;
    private Pattern pattern;
    public ProcessFiles(Strategy strategy, String regex) {
        this.strategy = strategy;
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    public void start(String args[]) {
        try {
            if(args.length == 0)
                processDirectoryTree(new File("."));
            else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if(fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        /*
                        if(!arg.endsWith("." + ext)){
                            arg += "." + ext;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                        */

                        if(!pattern.matcher(arg).matches()) {
                            return;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * 对目录树中的每一个节点执行strategy中的内容.
     */
    public void processDirectoryTree(File root) throws IOException {
        for (File file :
                Directory.walk(root.getAbsolutePath(), ".*")) {
            strategy.process(file.getCanonicalFile());
        }
    }

    public static void main(String[] args) {
        new ProcessFiles(new ProcessFiles.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
        }, ".*").start(args);

        System.out.println("\n\n---------------------------------------------------\n\n");

        final String lastModifiedTime = "2018-06-15 21:01:00";

            new ProcessFiles(new Strategy() {
                public void process(File file) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date date = format.parse(lastModifiedTime);
                        if (file.lastModified() < date.getTime())
                            System.out.println(file + "\t last modified: " + format.format(new Date(file.lastModified())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }, ".*\\.java").start(args);
    }
}
