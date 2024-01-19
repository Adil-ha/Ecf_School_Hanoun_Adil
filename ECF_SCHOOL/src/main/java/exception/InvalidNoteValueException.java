package exception;

public class InvalidNoteValueException extends RuntimeException{
    public InvalidNoteValueException(String message) {
        super(message);
    }
}
