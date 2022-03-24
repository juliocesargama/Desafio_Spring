package br.com.meli.Desafio_Spring.exception;

public class MissingArticleQuantityException extends RuntimeException {

    public MissingArticleQuantityException(String message) {
        super(message);
    }
}
