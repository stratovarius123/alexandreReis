package br.com.alexandre.concrete.service.exceptions;

import java.io.Serializable;
import java.util.Date;

public class AuthenticationError implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	
	public AuthenticationError(int status, String error, String message, String path) {
		super();
		this.timestamp =  new Date().getTime();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
