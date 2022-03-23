package br.com.meli.Desafio_Spring.repository;

import br.com.meli.Desafio_Spring.entity.Article;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ArticleRepository {

    private List<Article> articles = new ArrayList<>(
            Arrays.asList(
                    new Article(1, "Serra", "Ferramentas", "FORTGPRO", BigDecimal.valueOf(1800.00), 5, true, "***"),
                    new Article(2, "Furadeira", "Ferramentas", "Black & Decker", BigDecimal.valueOf(1800), 7, true, "****")
            )
    );

    private final String FILE_PATH="src/main/resources/articles.json";
    private final ObjectMapper mapper = new ObjectMapper();


    public void getAllArticles(){
        try {
            // TypeReference<List<Article>> listTypeReference = new TypeReference<>() {};
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Article.class);

            ObjectMapper objectMapper = new ObjectMapper();
            articles = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Article>>(){});
            //articles = mapper.readValue(Paths.get(FILE_PATH).toFile(), collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
