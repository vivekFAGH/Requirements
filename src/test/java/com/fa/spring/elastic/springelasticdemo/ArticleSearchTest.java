package com.fa.spring.elastic.springelasticdemo;

import com.fa.spring.elastic.springelasticdemo.config.Config;
import com.fa.spring.elastic.springelasticdemo.entity.Article;
import com.fa.spring.elastic.springelasticdemo.entity.Author;
import com.fa.spring.elastic.springelasticdemo.repository.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = Config.class)
public class ArticleSearchTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    private final Author johnSmith = new Author("John Smith");
    private final Author johnDoe = new Author("John Doe");

    @BeforeEach
    public void before() {
//        final Author johnSmith = new Author("John Smith");
//        final Author johnDoe = new Author("John Doe");
        System.out.println("Starting before eash");

        Article article = new Article("Spring Data Elasticsearch");
        article.setAuthors(asList(johnSmith, johnDoe));
        articleRepository.save(article);

        article = new Article("Search engines");
        article.setAuthors(asList(johnDoe));
        articleRepository.save(article);

        article = new Article("Second Article About Elasticsearch");
        article.setAuthors(asList(johnSmith));
        articleRepository.save(article);

        article = new Article("Elasticsearch Tutorial");
        article.setAuthors(asList(johnDoe));
        articleRepository.save(article);
    }

    @AfterEach
    public void after() {
        System.out.println("Starting after eash");
        articleRepository.deleteAll();
    }

    @Test
    public void givenArticleService_whenSaveArticle_thenIdIsAssigned() {
        System.out.println("Starting test1");

        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsName(johnSmith.getName(), PageRequest.of(0, 10));
        System.out.println("Starting Article Page=" + articleByAuthorName.getContent());
        assertEquals(2L, articleByAuthorName.getTotalElements());


//        final List<Author> authors = asList(new Author("John Smith"), johnDoe);
//
//        Article article = new Article("Making Search Elastic");
//        article.setAuthors(authors);
//
//        article = articleRepository.save(article);
//        System.out.println("Starting article= "+ article);
//        assertNotNull(article.getId());


    }
}
