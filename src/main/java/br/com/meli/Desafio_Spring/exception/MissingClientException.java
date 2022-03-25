package br.com.meli.Desafio_Spring.exception;

public class MissingClientException extends RuntimeException{
    public MissingClientException(String message) {
        super(message);
    }
}
