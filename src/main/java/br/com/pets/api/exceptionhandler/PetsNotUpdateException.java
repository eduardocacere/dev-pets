package br.com.pets.api.exceptionhandler;

@SuppressWarnings("serial")
public class PetsNotUpdateException extends RuntimeException {
	
	public PetsNotUpdateException(String message) {
		super(message);
	}
	
	public PetsNotUpdateException() {
		super("update.not.found");
	}

}
