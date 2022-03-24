package br.com.meli.Desafio_Spring.dto;

import br.com.meli.Desafio_Spring.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public List<ArticleDTO> convert(List<Article> articleList) {
        return articleList.stream()
                .map(a -> new ArticleDTO(a.getProductId(), a.getName(), a.getQuantity()))
                .collect(Collectors.toList());
    }

}
