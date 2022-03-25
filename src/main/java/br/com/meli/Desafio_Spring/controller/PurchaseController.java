package br.com.meli.Desafio_Spring.controller;

import br.com.meli.Desafio_Spring.dto.CartDTO;
import br.com.meli.Desafio_Spring.dto.PurchaseArticleDTO;
import br.com.meli.Desafio_Spring.dto.PurchaseOutputDTO;
import br.com.meli.Desafio_Spring.dto.RequestPurchaseDTO;
import br.com.meli.Desafio_Spring.entity.Client;
import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.repository.ClientRepository;
import br.com.meli.Desafio_Spring.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @PostMapping("/api/v1/purchase-request")
    public ResponseEntity<PurchaseOutputDTO> purchaseArticles(@RequestBody RequestPurchaseDTO purchaseArticleDTOS,
                                                              UriComponentsBuilder uriBuilder,
                                                              @RequestParam long id) {
      
        List<PurchaseArticleDTO> PurchaseArticleDTO = purchaseArticleDTOS.getArticlesPurchaseRequest().stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseArticleDTO.class))
                .collect(Collectors.toList());

        Purchase purchase = purchaseService.save(PurchaseArticleDTO);
        clientRepository.addPurchaseIdToList(purchase, id);

        clientRepository.addPurchaseIdToList(purchase, id);

        Client client = clientRepository.findById(id);

        String name = client.getName();


        PurchaseOutputDTO dto = new PurchaseOutputDTO();
        dto.convert(purchase, name);
        URI uri = uriBuilder
                .path("/api/v1/purchase-request")
                .buildAndExpand(purchase.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/api/v1/cart")

    public ResponseEntity<CartDTO> returnHistoryOfPurchases(@RequestParam String idclient) {

        CartDTO dto = new CartDTO();
        CartDTO result = dto.convert(Long.valueOf(idclient), clientRepository, purchaseService);

        return ResponseEntity.ok(result);
    }
}
