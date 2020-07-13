package util;

public class WrongPolynomialException extends RuntimeException {
    public WrongPolynomialException() {
        super();
    }

    public WrongPolynomialException(String str) {
        super(str);
    }
}
