package ufps.centrosrecreativos.api.util;

import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String msg;
	private final HttpStatus status;
	private final Exception e;

	public ValidationException(String msg, HttpStatus status) {
		this.msg = msg;
		this.status = status;
		this.e = null;
	}

	public ValidationException(String msg, HttpStatus status, Exception e) {
		this.msg = msg;
		this.status = status;
		this.e = e;
	}

	public String getMsg() {
		return msg;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Exception getE() {
		return e;
	}
}
