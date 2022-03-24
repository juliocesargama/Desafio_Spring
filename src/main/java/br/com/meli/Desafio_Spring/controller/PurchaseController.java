package br.com.meli.Desafio_Spring.controller;

import br.com.meli.Desafio_Spring.dto.PurchaseArticleDTO;
import br.com.meli.Desafio_Spring.dto.RequestPurchaseDTO;
import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    private final ModelMapper modelMapper;

    @PostMapping("/api/v1/purchase-request")
    public ResponseEntity<Purchase> purchaseArticles(@RequestBody RequestPurchaseDTO purchaseArticleDTOS) {

        List<PurchaseArticleDTO> PurchaseArticleDTO = purchaseArticleDTOS.getArticlesPurchaseRequest().stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseArticleDTO.class))
                .collect(Collectors.toList());

        System.out.println("endpoint");
        Purchase purchase = purchaseService.save(PurchaseArticleDTO);

        return new ResponseEntity<Purchase>(purchase, HttpStatus.CREATED);
    }
}
