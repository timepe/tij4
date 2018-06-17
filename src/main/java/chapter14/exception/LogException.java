package chapter14.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

class MyException1 extends Exception {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

//    public MyException1() {
//        StringWriter trace = new StringWriter();
//        printStackTrace(new PrintWriter(trace));
//        logger.error(trace.toString());
//    }

    public void logException() {
        // 1. 建立StringWriter流
        StringWriter trace = new StringWriter();
        // 2. 将异常信息写入StringWriter流对象
        printStackTrace(new PrintWriter(trace));
        // 3. 将StringWriter流对象转换成字符串打印
        logger.error(trace.toString());
    }
};
class MyException2 extends Exception {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public void logException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.fatal(trace.toString());
    }
}

public class LogException {
    public static void main(String[] args) {
        try {
            throw new MyException1();
        } catch (MyException1 e) {
            e.logException();
        }

        try {
            throw new MyException2();
        } catch (MyException2 e) {
            e.logException();
            e.printStackTrace();
        }
    }
}
