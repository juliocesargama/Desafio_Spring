package br.com.meli.Desafio_Spring.service;

import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ArticleService {

  @Autowired
  private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.getAll();
    }

    public List<Article> save(List<Article> articles) {
        List<Article> articleCreated  = new ArrayList<>();

        articles.forEach(article -> {
            articleCreated.add(articleRepository.save(article));
        });

        return articleCreated;
    }

    public List<Article> findByCategory(String category) {
        if (!categoryExists(category)){
            throw new RuntimeException("Categoria não existe");
        }
        return articleRepository.getAll().stream()
                .filter(a -> a.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public boolean categoryExists(String category) {
        return articleRepository.getAll().stream()
                .anyMatch(a -> a.getCategory().equalsIgnoreCase(category));
    }

    public Stream<Article> searchArticlesByFilters(MultiValueMap<String, String> params) {
        
        return articleRepository.getAll().stream()
                .filter(a -> params.get("category").contains(a.getCategory()))
                .filter(a -> params.get("brand").contains(a.getBrand()))
                .filter(a -> params.get("freeShipping").contains(a.getFreeShipping().toString()))
                .filter(a -> params.get("prestige").contains(a.getPrestige()));
            } 

}
