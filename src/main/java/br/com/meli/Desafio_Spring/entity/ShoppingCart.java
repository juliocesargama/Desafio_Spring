package br.com.meli.Desafio_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    private Integer id;
    private Client client;
    private List<Purchase> purchaseList;
    private BigDecimal total;
}
