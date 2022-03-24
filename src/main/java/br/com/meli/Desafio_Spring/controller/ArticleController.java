package br.com.meli.Desafio_Spring.controller;

import br.com.meli.Desafio_Spring.dto.ArticleDTO;

import br.com.meli.Desafio_Spring.dto.RequestArticleDTO;
import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.service.ArticleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ArticleDTO>> createArticles(@RequestBody RequestArticleDTO requestArticleDTO) {
        List<Article> articles = requestArticleDTO.getArticles().stream()
                .map(article -> modelMapper.map(article, Article.class))
                .collect(Collectors.toList());

        List<Article> savedArticle =  articleService.save(articles);
        List<ArticleDTO> articleDTO = savedArticle.stream().map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());


        return new ResponseEntity<List<ArticleDTO>>(articleDTO, HttpStatus.CREATED);
    }
}
