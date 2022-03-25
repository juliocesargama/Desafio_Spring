package br.com.meli.Desafio_Spring.repository;

import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseRepository {

    private List<Purchase> purchases = new ArrayList<>();

    public Purchase save(Purchase purchase) {
        purchase.setId((long) (purchases.size()+ 1));
        purchases.add(purchase);
        return purchase;
    }

    public List<Purchase> getAll(){
        return purchases;
    }

    public Purchase findById(Long id) {
        return purchases.stream()
                .filter(a -> a.getId() == id)
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Purchase not found " + id));
    }

}
