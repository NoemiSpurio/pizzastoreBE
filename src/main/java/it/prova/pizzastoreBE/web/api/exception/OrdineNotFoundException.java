package it.prova.pizzastoreBE.web.api.exception;

public class OrdineNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OrdineNotFoundException(String message) {
		super(message);
	}

}
