package br.com.itau.cartoes.exception;

public class CartaoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String atributo;

	public String getAtributo() {
		return atributo;
	}

	public CartaoException(String atributo, String message) {
		super(message);
		this.atributo = atributo;
	}

}
