package br.com.meli.Desafio_Spring.service;

import br.com.meli.Desafio_Spring.entity.Article;
import br.com.meli.Desafio_Spring.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Article> searchArticlesByFilters(MultiValueMap<String, String> params) {
        List<Article> list = articleRepository.getAll();

        if(params.get("category") != null){
            list = list.stream()
                    .filter(a -> params.get("category").get(0).equalsIgnoreCase(a.getCategory()))
                    .collect(Collectors.toList());
        }
        if(params.get("brand") != null){
            list = list.stream()
                    .filter(a -> params.get("brand").get(0).equalsIgnoreCase(a.getBrand()))
                    .collect(Collectors.toList());
        }
        if(params.get("freeShipping") != null){
            list = list.stream()
                    .filter(a -> params.get("freeShipping").get(0).equalsIgnoreCase(a.getFreeShipping().toString()))
                    .collect(Collectors.toList());
        }
        if(params.get("prestige") != null){
            list = list.stream()
                    .filter(a -> params.get("prestige").get(0).equalsIgnoreCase(a.getPrestige()))
                    .collect(Collectors.toList());
        }

        if (params.get("order").get(0).equals("0")) {
            return filterByAlphabet(list);
        } else if (params.get("order").get(0).equals("1")) {
            return filterByAlphabetReverse(list);
        } else if (params.get("order").get(0).equals("2")) {
            return filterByHigherPrice(list);
        } else if (params.get("order").get(0).equals("3")) {
            return filterByLowerPrice(list);
        } else {
            throw new RuntimeException("Order nao existe");
        }
    }

    public List<Article> filterByHigherPrice(List<Article> list) {
        return list.stream()
                .sorted((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()))
                .collect(Collectors.toList());
    }

    public List<Article> filterByLowerPrice(List<Article> list){
        return list.stream()
                .sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()))
                .collect(Collectors.toList());
    }

    public List<Article> filterByAlphabet(List<Article> list){
        return list.stream()
                .sorted(Comparator.comparing(Article::getName))
                .collect(Collectors.toList());
    }

    public List<Article> filterByAlphabetReverse(List<Article> list){
        return list;
    }

}
