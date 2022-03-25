package br.com.meli.Desafio_Spring.repository;

import br.com.meli.Desafio_Spring.entity.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCartRepository {

    private List<ShoppingCart> shoppingCarts= new ArrayList<>();

    public ShoppingCart save(ShoppingCart shoppingCart) {
        shoppingCart.setId(shoppingCarts.size()+ 1);
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }
}
