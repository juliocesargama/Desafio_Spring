package br.com.meli.Desafio_Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.service.ArticleService;

@RestController
@RequestMapping("/")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping("api/v1/article")
  public List<Article> getAllArticles() {
    return articleService.findAll();
  }

}
