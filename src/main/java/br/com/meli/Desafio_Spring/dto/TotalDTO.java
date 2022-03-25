package br.com.meli.Desafio_Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TotalDTO {
    BigDecimal totalPurchases;
}
