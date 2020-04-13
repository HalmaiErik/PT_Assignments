package util;

public class WrongInputException extends RuntimeException {
    public WrongInputException() {
        super();
    }

    public WrongInputException(String msg) {
        super(msg);
    }
}
