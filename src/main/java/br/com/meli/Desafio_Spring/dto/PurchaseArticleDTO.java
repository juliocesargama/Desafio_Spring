package br.com.meli.Desafio_Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseArticleDTO {
    private long productId;
    private String name;
    private String brand;
    private Integer quantity;
}
