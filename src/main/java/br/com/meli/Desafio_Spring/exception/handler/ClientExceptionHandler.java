package br.com.meli.Desafio_Spring.exception.handler;

import br.com.meli.Desafio_Spring.exception.MissingArticleQuantityException;
import br.com.meli.Desafio_Spring.exception.MissingClientException;
import br.com.meli.Desafio_Spring.exception.UfNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientExceptionHandler {
    @ExceptionHandler(value = MissingClientException.class)
    protected ResponseEntity<Object> handleMissingClient(MissingClientException ex) {
        String body = ex.getMessage();
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = UfNotFoundException.class)
    protected ResponseEntity<Object> handleUfNotFoundException (UfNotFoundException ex){
        String body = ex.getMessage();
        return ResponseEntity.badRequest().body(body);
    }
}
