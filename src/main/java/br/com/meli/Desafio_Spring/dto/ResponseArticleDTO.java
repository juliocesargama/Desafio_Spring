package br.com.meli.Desafio_Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseArticleDTO {
    private List<ArticleDTO> articlesDTO;
}
