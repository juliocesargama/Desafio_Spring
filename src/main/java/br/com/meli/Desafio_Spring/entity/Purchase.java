package br.com.meli.Desafio_Spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class Purchase {
    private Long id;
    private List<Article> articles;
    private BigDecimal total;

    public Purchase(List<Article> articles) {
        this.articles = articles;
        total = setTotal();
    }

    public BigDecimal setTotal() {
        return BigDecimal.valueOf(articles.stream().mapToDouble(article -> article.getPrice().doubleValue() * article.getQuantity()
                .doubleValue()).sum());
    }
}
