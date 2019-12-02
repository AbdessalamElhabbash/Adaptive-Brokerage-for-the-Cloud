package exceptions;

@SuppressWarnings("serial")
public class RequiredArgumentException extends Exception {

	public RequiredArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
