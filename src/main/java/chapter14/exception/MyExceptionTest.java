package chapter14.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

class MyException extends Exception {
    public MyException() {
    }
    public MyException(String message) {
        super(message);
    }
}

class RTException extends RuntimeException {
    public RTException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "RTException in toString()";
    }
}

public class MyExceptionTest {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void main(String[] args) {
        try {
            throw new MyException("it's my exception.");
        } catch (MyException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            // 比catch中的语句先执行
            System.out.println("finally, I've been executed.");
        }

        Object nullObj = null;
        try {
            nullObj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] arrs = new String[4];
        try {
            arrs[4] = "Array index out of bounds";
        } catch (ArrayIndexOutOfBoundsException e){
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
//            e.printStackTrace();
            logger.error(trace.toString());
        }

        int count = 0;

        while (true) {
            try {
                if (count < 5) {
                    throw new MyException("count < 5");
                } else {
                    break;
                }
            } catch (MyException e) {
                e.printStackTrace();
            } finally {
                count++;
            }
        }
        /* 多个异常覆盖 */
        try {
            nullObj.toString();
        } finally {
            System.out.println("null pointer exception will be executed.");
        }
       throw new RTException("RTException");


    }
}
