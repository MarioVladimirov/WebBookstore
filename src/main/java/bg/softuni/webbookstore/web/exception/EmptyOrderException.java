package bg.softuni.webbookstore.web.exception;

public class EmptyOrderException extends RuntimeException {

    private final String message;

    public EmptyOrderException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
