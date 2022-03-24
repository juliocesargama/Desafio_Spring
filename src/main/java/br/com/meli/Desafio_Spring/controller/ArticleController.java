package br.com.meli.Desafio_Spring.controller;

import br.com.meli.Desafio_Spring.dto.ArticleDTO;

import br.com.meli.Desafio_Spring.dto.RequestArticleDTO;
import br.com.meli.Desafio_Spring.dto.ResponseArticleDTO;
import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.service.ArticleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

public class ArticleController {
    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @PostMapping("/insert-articles-request")
    public ResponseEntity<?> createArticles(@RequestBody RequestArticleDTO requestArticleDTO) {
        List<Article> articles = requestArticleDTO.getArticles().stream()
                .map(article -> modelMapper.map(article, Article.class))
                .collect(Collectors.toList());

        List<Article> savedArticle = articleService.save(articles);
        List<ArticleDTO> articleDTO = savedArticle.stream().map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResponseArticleDTO(articleDTO), HttpStatus.CREATED);
    }

    @GetMapping("/articles/")
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDTO>> returnCategory(@RequestParam String category) {
        ArticleDTO dto = new ArticleDTO();
        List<ArticleDTO> result = dto.convert(articleService.findByCategory(category));
        return ResponseEntity.ok(result);
    }

}
