package br.com.meli.Desafio_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private long productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;

    public String getBrand() {
        return brand;
    }
    public String getPrestige() {
        return prestige;
    }
    public void setPrestige(String prestige) {
        this.prestige = prestige;
    }
    public Boolean getFreeShipping() {
        return freeShipping;
    }
    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
 

}
