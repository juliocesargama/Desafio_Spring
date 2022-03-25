package br.com.meli.Desafio_Spring.exception.handler;

import br.com.meli.Desafio_Spring.dto.ExceptionArticleDTO;
import br.com.meli.Desafio_Spring.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionNotFoundHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundArticleException(EntityNotFoundException exception, HttpServletRequest request) {
        ExceptionArticleDTO exceptionArticleDTO = ExceptionArticleDTO.builder()
                .timestamp(LocalDateTime.now())
                .title("Resource not found")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(exceptionArticleDTO, HttpStatus.NOT_FOUND);
    }

}
