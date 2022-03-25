package br.com.meli.Desafio_Spring.exception;

public class UfNotFoundException  extends RuntimeException{
    public UfNotFoundException(String message) {
        super(message);
    }
}
