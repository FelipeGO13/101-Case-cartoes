package br.com.itau.cartoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClienteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String atributo;

	public String getAtributo() {
		return atributo;
	}

	public ClienteException(String atributo, String message) {
		super(message);
		this.atributo = atributo;
	}

}
