package br.com.meli.Desafio_Spring.service;

import br.com.meli.Desafio_Spring.dto.PurchaseArticleDTO;
import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.repository.ArticleRepository;
import br.com.meli.Desafio_Spring.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ArticleRepository articleRepository;

    public Purchase save(List<PurchaseArticleDTO> articleList) {

        List<Article> articles = convert(articleList);

        Purchase purchase = new Purchase(articles);
        purchaseRepository.save(purchase);
        return purchase;
    }


    public List<Article> convert(List<PurchaseArticleDTO> purchaseArticleDTOList) {

        List<Article> articleList = purchaseArticleDTOList.stream().map(purchaseArticleDTO -> convert(purchaseArticleDTO))
                .collect(Collectors.toList());

        return articleList;
    }

    public Article convert(PurchaseArticleDTO purchaseArticleDTO) {
        Article article = articleRepository.getAll().stream().filter(a -> a.getProductId() == purchaseArticleDTO.getProductId())
                .findFirst().get();

        return article;
    }
}
