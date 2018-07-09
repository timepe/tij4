package chapter12.exception;
class FirstException extends Exception {
    @Override
    public String toString() {
        return "FirstException";
    }
}
class SecondException extends FirstException {
    @Override
    public String toString() {
        return "SecondException";
    }
}
class ThirdException extends SecondException {
    @Override
    public String toString() {
        return "ThirdException";
    }
}

class ThrowingFirstException {
    public void f() throws FirstException {
        throw new FirstException();
    }
}

class ThrowingSecondException extends ThrowingFirstException {
    // throws Exception必须为FirstException或其子类
    public void f() throws SecondException {
        throw new SecondException();
    }
}

class ThrowingThirdException extends ThrowingSecondException {
    // throws Exception必须为SecondException或其子类
    public void f() throws SecondException {
        throw new ThirdException();
    }
}

public class ThreeExceptions {
    public static void main(String[] args){
        try {
            ThrowingFirstException tte = new ThrowingThirdException();
            tte.f();
        } catch (FirstException e) {  //可以看成 e = new ThirdException()
            System.out.println(e);
        }
    }
}
