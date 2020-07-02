package br.com.pets.api.exceptionhandler;

public class PetsException extends RuntimeException {

    public PetsException(String message) {
        super(message);
    }

    public PetsException() {
        super("recurso.operacao-nao-permitida");
    }

}
