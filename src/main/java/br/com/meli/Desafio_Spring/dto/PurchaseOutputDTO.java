package br.com.meli.Desafio_Spring.dto;

import br.com.meli.Desafio_Spring.entity.Article;

import br.com.meli.Desafio_Spring.entity.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOutputDTO {

    private String clientName;
    private List<Article> articles;
    private BigDecimal total;

    public PurchaseOutputDTO convert(Purchase purchase, String name){
        this.clientName = name;
        this.articles = purchase.getArticles();
        this.total = purchase.getTotal();
        return this;
    }

}
