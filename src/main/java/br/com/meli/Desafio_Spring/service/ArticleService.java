package br.com.meli.Desafio_Spring.service;

import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
