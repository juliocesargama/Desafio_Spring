package br.com.meli.Desafio_Spring.dto;

import br.com.meli.Desafio_Spring.entity.Article;

import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.exception.EntityNotFoundException;
import br.com.meli.Desafio_Spring.repository.ClientRepository;
import br.com.meli.Desafio_Spring.repository.PurchaseRepository;
import br.com.meli.Desafio_Spring.service.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOutputDTO {

    private String clientName;
    private List<Article> articles;
    private List<Purchase> purchases;
    private BigDecimal total;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PurchaseService purchaseService;

    public PurchaseOutputDTO convert(Purchase purchase, String name){
        this.clientName = name;
        this.articles = purchase.getArticles();
        this.total = purchase.getTotal();
        return this;
    }

    public PurchaseOutputDTO convert(Long idClient){
        this.clientName = clientRepository.findById(idClient).getName();
        this.purchases = purchaseService.returnPurchasesByIdClient(idClient);
        this.total = BigDecimal.valueOf(articles.stream()
                    .mapToDouble(article -> article.getPrice().doubleValue() * article.getQuantity().doubleValue())
                    .sum());

        return this;
    }

}
