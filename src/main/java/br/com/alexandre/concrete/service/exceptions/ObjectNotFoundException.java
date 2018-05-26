package br.com.alexandre.concrete.service.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String mensagem){
		super(mensagem);
	}
	
	public ObjectNotFoundException() {
        super();
    }
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
   
    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }
}
