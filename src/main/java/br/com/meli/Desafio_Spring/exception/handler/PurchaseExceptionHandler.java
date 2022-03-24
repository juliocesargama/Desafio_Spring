package br.com.meli.Desafio_Spring.exception.handler;

import br.com.meli.Desafio_Spring.exception.MissingArticleQuantityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PurchaseExceptionHandler {
    @ExceptionHandler(value = MissingArticleQuantityException.class)
    protected ResponseEntity<Object> handleMissingQuantity(MissingArticleQuantityException ex) {
        String body = ex.getMessage();
        return ResponseEntity.badRequest().body(body);
    }

}
