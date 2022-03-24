package br.com.meli.Desafio_Spring.repository;

import br.com.meli.Desafio_Spring.entity.Purchase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseRepository {

    private List<Purchase> purchases = new ArrayList<>();

    public Purchase save(Purchase purchase) {
        purchase.setId(purchases.size()+ 1);
        purchases.add(purchase);
        return purchase;
    }

}
