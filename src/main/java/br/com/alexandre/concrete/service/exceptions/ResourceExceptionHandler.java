package br.com.alexandre.concrete.service.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alexandre.concrete.exception.EmailExistException;
import br.com.alexandre.concrete.exception.UserNotFoundException;
import br.com.alexandre.concrete.exception.LoginInvalidException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EmailExistException.class)
    public ResponseEntity<StandardError> handlerEmailExistenteException(EmailExistException e, HttpServletRequest request) {
		StandardError standardError =  new StandardError(HttpStatus.BAD_REQUEST.value(),"E-mail já existente", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
	
	@ExceptionHandler(UserNotFoundException.class)
	 public ResponseEntity<StandardError> handlerUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
		StandardError standardError =  new StandardError(HttpStatus.NOT_FOUND.value(), "Usuário e/ou senha inválidos", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}
	
	@ExceptionHandler(LoginInvalidException.class)
	public ResponseEntity<StandardError> handlerUserNotFoundException(LoginInvalidException e, HttpServletRequest request) {
		StandardError standardError =  new StandardError(HttpStatus.UNAUTHORIZED.value(), "Não Autorizado", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}
	
}
