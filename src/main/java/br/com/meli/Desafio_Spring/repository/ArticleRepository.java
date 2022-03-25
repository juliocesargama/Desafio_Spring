package br.com.meli.Desafio_Spring.repository;

import br.com.meli.Desafio_Spring.entity.Article;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {

    private List<Article> articles;

    private final String FILE_PATH="src/main/resources/articles.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public ArticleRepository() {
        this.articles = new ArrayList<>();
        getAllArticles();
    }

    public void getAllArticles(){
        try {
            articles.addAll(mapper.readValue(new File(FILE_PATH), new TypeReference<List<Article>>(){}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getAll() {
        return articles;
    }

    public Article save(Article article) {
        Article articleFromRepo = getByNameCategoryBrand(article);
        if (articleFromRepo == null) {
            article.setProductId(articles.size() + 1);
            articles.add(article);
        } else {
            articleFromRepo.setQuantity(articleFromRepo.getQuantity() + article.getQuantity());
        }
        return article;
    }

    public Article getById(Article article) {
        return articles.stream().filter(a -> a.getProductId() == article.getProductId())
                .findFirst().orElse(null);
    }

    public Article getByNameCategoryBrand(Article article) {
        return articles.stream()
                .filter(a -> a.getName().equalsIgnoreCase(article.getName()))
                .filter(a -> a.getCategory().equalsIgnoreCase(article.getCategory()))
                .filter(a -> a.getBrand().equalsIgnoreCase(article.getBrand()))
                .findFirst()
                .orElse(null);
    }

    public Article update(Article article, int i) {
        articles.set(i, article);
        return article;
    }
}
