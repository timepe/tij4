package chapter12.exception;

class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception";
    }
}

class HoHumException extends Exception {
    @Override
    public String toString() {
        return "A trivail exception";
    }
}
public class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {

            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    lm.dispose();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

    }
}
