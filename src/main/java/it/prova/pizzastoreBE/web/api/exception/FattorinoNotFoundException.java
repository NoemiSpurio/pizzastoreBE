package it.prova.pizzastoreBE.web.api.exception;

public class FattorinoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FattorinoNotFoundException(String message) {
		super(message);
	}

}
