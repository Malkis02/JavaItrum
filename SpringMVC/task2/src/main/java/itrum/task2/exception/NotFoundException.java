package itrum.task2.exception;

public class NotFoundException extends RuntimeException{
    private final String message;

    public NotFoundException(String message) {
        this.message = message;
    }
}
