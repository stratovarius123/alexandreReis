package br.com.alexandre.concrete.exception;

public class LoginInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginInvalidException() {
        super();
    }

	public LoginInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public LoginInvalidException(String message) {
        super(message);
    }
    
	public LoginInvalidException(Throwable cause) {
        super(cause);
    }
}
