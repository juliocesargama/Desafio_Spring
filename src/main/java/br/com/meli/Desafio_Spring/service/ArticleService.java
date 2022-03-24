package br.com.meli.Desafio_Spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meli.Desafio_Spring.repository.ArticleRepository;
import br.com.meli.Desafio_Spring.entity.Article;


@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public List<Article> findAll() {
    return articleRepository.getAll();
  }
}
