package chapter18.io.stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class OSExecuteException extends RuntimeException {
    public OSExecuteException(String why)  { super(why);}
}

public class OSExecute {
    public static void command (String command) {
        boolean err = false;
        try {
            /* 1. 获取进程, 并启动 */
            Process process = new ProcessBuilder(command.split(" ")).start();
            /* 2. 获取子进程的输出流， 此处为标准输出流 */
            BufferedReader results = new BufferedReader(new
                    InputStreamReader(process.getInputStream(), "gbk"));
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }
            /* 3. 获取子进程的标准错误流 */
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream(), "gbk"));
            while((s = errors.readLine()) != null) {
                System.err.println(s);
                err = true;
            }
        } catch (Exception e) {
            if(!command.startsWith("CMD /C"))
                command("CMD /C " + command);
            else
                throw new OSExecuteException("Error executing " + command);
        }

        if(err)
            throw new OSExecuteException("Error executing " + command);
    }

    public static void main(String[] args) {
        OSExecute.command("dir");
    }
}
