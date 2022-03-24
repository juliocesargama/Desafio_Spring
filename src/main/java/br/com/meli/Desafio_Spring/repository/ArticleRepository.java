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
        article.setProductId(articles.size() + 1);
        articles.add(article);
        return article;
    }

    public Article getById(Article article) {
        return articles.stream().filter(a -> a.getProductId() == article.getProductId())
                .findFirst().orElse(null);
    }
}
