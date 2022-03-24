package br.com.meli.Desafio_Spring.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleInputDTO {
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;
}

