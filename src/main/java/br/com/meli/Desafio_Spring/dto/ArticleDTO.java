package br.com.meli.Desafio_Spring.dto;

import br.com.meli.Desafio_Spring.entity.Article;

import lombok.Data;

@Data
public class ArticleDTO {

    private long productId;
    private String name;
    private Integer quantity;

    public ArticleDTO convert(Article article){
        this.productId = article.getProductId();
        this.name = article.getName();
        this.quantity = article.getQuantity();
        return this;
    }
}
