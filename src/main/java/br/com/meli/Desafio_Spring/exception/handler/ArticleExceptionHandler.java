package br.com.meli.Desafio_Spring.exception.handler;

import br.com.meli.Desafio_Spring.exception.ArtifactInvalidOrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleExceptionHandler {
    @ExceptionHandler(value = ArtifactInvalidOrderException.class)
    protected ResponseEntity<Object> handleMissingClient(ArtifactInvalidOrderException ex) {
        String body = ex.getMessage();
        return ResponseEntity.badRequest().body(body);
    }
}
