package br.com.ftm.jokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JogadorException extends Exception {

	private static final long serialVersionUID = 1L;

	public JogadorException(String mensagem) {
		super(mensagem);
	}
}
