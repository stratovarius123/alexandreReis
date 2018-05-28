package br.com.alexandre.concrete.resource.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alexandre.concrete.exception.EmailExistException;
import br.com.alexandre.concrete.exception.LoginInvalidException;
import br.com.alexandre.concrete.exception.UserNotFoundException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EmailExistException.class)
    public ResponseEntity<StandardError> handlerEmailExistenteException(EmailExistException e, HttpServletRequest request) {
		StandardError standardError =  new StandardError(HttpStatus.BAD_REQUEST.value(),"E-mail j� existente", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
	
	@ExceptionHandler(UserNotFoundException.class)
	 public ResponseEntity<StandardError> handlerUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
		StandardError standardError =  new StandardError(HttpStatus.NOT_FOUND.value(), "Usu�rio e/ou senha inv�lidos", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}
	
	@ExceptionHandler(LoginInvalidException.class)
	public ResponseEntity<StandardError> handlerLoginInvalidException(LoginInvalidException e, HttpServletRequest request) {
		StandardError standardError =  new StandardError(HttpStatus.UNAUTHORIZED.value(), "N�o Autorizado", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}
	
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<StandardError> handlerSignatureException(SignatureException e, HttpServletRequest request) {
		StandardError standardError =  new StandardError(HttpStatus.UNAUTHORIZED.value(), "N�o Autorizado", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}
	
}
