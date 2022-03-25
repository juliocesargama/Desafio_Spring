package br.com.meli.Desafio_Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.meli.Desafio_Spring.entity.Article;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleOutputDTO {
    
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Boolean freeShipping;
    private String prestige;

    public ArticleOutputDTO convert(Article article){
        this.name = article.getName();
        this.category = article.getCategory();
        this.brand = article.getBrand();
        this.price = article.getPrice();
        this.freeShipping = article.getFreeShipping();
        this.prestige = article.getPrestige();
        return this;
    }

    public List<ArticleOutputDTO> convert(List<Article> articleList) {
        return articleList.stream()
                .map(a -> new ArticleOutputDTO(a.getName(), a.getCategory(), a.getBrand(), a.getPrice(), a.getFreeShipping(), a.getPrestige()))
                .collect(Collectors.toList());
    }
}

