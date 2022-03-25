package br.com.meli.Desafio_Spring.dto;

import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.repository.ClientRepository;
import br.com.meli.Desafio_Spring.service.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDTO {

    private String clientName;
    private List<Purchase> purchases;
    private BigDecimal total;

    public CartDTO convert(Long idClient, ClientRepository clientRepository, PurchaseService purchaseService){
        this.clientName = clientRepository.findById(idClient).getName();
        this.purchases = purchaseService.returnPurchasesByIdClient(idClient);
//      this.total = BigDecimal.valueOf(articles.stream()
//                    .mapToDouble(article -> article.getPrice().doubleValue() * article.getQuantity().doubleValue())
//                    .sum());

        return this;
    }
}
