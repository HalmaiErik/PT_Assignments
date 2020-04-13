package presentation;

public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException() {
        super();
    }

    public IllegalCommandException(String msg) {
        super(msg);
    }
}
