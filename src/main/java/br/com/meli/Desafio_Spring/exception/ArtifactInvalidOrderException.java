package br.com.meli.Desafio_Spring.exception;

public class ArtifactInvalidOrderException extends RuntimeException {
    public ArtifactInvalidOrderException(String message){
        super(message);
    }
}
