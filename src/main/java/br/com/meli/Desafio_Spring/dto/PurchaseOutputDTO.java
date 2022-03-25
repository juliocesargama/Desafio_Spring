package br.com.meli.Desafio_Spring.dto;

import br.com.meli.Desafio_Spring.entity.Article;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseOutputDTO {

    private String clientName;
    private List<ArticleOutputDTO> articles;
    private BigDecimal total;

}
