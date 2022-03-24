package br.com.meli.Desafio_Spring.dto;

import lombok.Data;

@Data
public class ArticleDTO {

    private long productId;
    private String name;
    private Integer quantity;
}
