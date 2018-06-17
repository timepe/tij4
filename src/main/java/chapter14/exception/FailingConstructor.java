package chapter14.exception;
class FailingConstructorException extends Exception {
    @Override
    public String getMessage() {
        return "Failing Constructor Exception.";
    }
}
public class FailingConstructor {
    public FailingConstructor() throws FailingConstructorException{
        throw new FailingConstructorException();
    }

    public void dispose () {
        System.out.println("Do some cleanning.");
    }

    public static void main(String[] args) {
        try {
            FailingConstructor fc = new FailingConstructor();
            try {

            } finally {
                fc.dispose();
            }
        } catch (FailingConstructorException e) {
            System.out.println(e.getMessage());
        }
    }
}
