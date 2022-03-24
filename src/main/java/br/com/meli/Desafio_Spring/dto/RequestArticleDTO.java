package br.com.meli.Desafio_Spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestArticleDTO {
    private List<ArticleInputDTO> articles;
}

