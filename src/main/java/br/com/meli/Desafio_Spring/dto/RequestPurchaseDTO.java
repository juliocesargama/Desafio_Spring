package br.com.meli.Desafio_Spring.dto;

import lombok.Data;

import java.util.List;
@Data
public class RequestPurchaseDTO {
    private List<PurchaseArticleDTO> articlesPurchaseRequest;
}
