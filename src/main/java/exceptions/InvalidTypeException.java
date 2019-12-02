package exceptions;

@SuppressWarnings("serial")
public class InvalidTypeException extends Exception {

	public InvalidTypeException(String errorMessage) {
        super(errorMessage);
    }
}
