package br.com.meli.Desafio_Spring.service;

import br.com.meli.Desafio_Spring.dto.PurchaseArticleDTO;
import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.exception.EntityNotFoundException;
import br.com.meli.Desafio_Spring.exception.MissingArticleQuantityException;
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

        List<Article> articlesFromPurchase = convert(articleList);

        // validate if there is enough articles to fulfill the purchase
        if (getArticlesWithLowerInventory(articlesFromPurchase).size() > 0) {
            throw new MissingArticleQuantityException("quantidade insulficiente");
        }

        Purchase purchase = new Purchase(articlesFromPurchase);
        purchaseRepository.save(purchase);
        return purchase;
    }

    public List<Article> getArticlesWithLowerInventory(List<Article> purchaseArticles) {
        return purchaseArticles.stream().filter(article -> article.getQuantity() > articleRepository.getById(article).getQuantity())
                .collect(Collectors.toList());
    }

    // those convert method might be moved to DTO class
    public List<Article> convert(List<PurchaseArticleDTO> purchaseArticleDTOList) {

        List<Article> articleList = purchaseArticleDTOList.stream().map(purchaseArticleDTO -> convert(purchaseArticleDTO))
                .collect(Collectors.toList());

        return articleList;
    }

    public Article convert(PurchaseArticleDTO purchaseArticleDTO) {
        Article article = new Article(
                articleRepository.getAll().stream().filter(a -> a.getProductId() == purchaseArticleDTO.getProductId())
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Id not found " + purchaseArticleDTO.getProductId())));

        article.setQuantity(purchaseArticleDTO.getQuantity());

        return article;
    }


}
