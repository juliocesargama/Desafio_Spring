package br.com.meli.Desafio_Spring.service;

import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> save(List<Article> articles) {
        List<Article> articleCreated  = new ArrayList<Article>();

        articles.forEach(article -> {
            articleCreated.add(articleRepository.save(article));
        });

        return articleCreated;
    }
}
