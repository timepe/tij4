package chapter14.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

class Exception1 extends Exception{
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public void logStackTrace() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.error(trace.toString());
    }
}
class Exception2 extends Exception{
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public void logStackTrace() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.error(trace.toString());
    }
}

public class TwoExceptions {
    public static void g() throws Exception1{
        throw new Exception1();
    }

    public static void f() throws Exception2{
        try {
            g();
        } catch (Exception1 e) {
           e.logStackTrace();
           //throw new Exception2();
            throw new RuntimeException(new Exception2());
        }
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (Exception2 e) {
            e.logStackTrace();
        }
    }
}

