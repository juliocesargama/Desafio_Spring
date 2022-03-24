package br.com.meli.Desafio_Spring.controller;

import br.com.meli.Desafio_Spring.dto.PurchaseArticleDTO;
import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/api/v1/purchase-request")
    public ResponseEntity<Purchase> purchaseArticles(@RequestBody List<PurchaseArticleDTO> purchaseArticleDTOS) {

        System.out.println("endpoint");
        Purchase purchase = purchaseService.save(purchaseArticleDTOS);

        return new ResponseEntity<Purchase>(purchase, HttpStatus.CREATED);
    }
}
