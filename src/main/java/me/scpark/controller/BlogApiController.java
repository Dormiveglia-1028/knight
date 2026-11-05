package me.scpark.controller;

import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
import me.scpark.dto.ArticleResponse;
import me.scpark.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
       // List<Article> articleList = blogService.getAll();
        //List<ArticleResponse> articleResponsesList = new ArrayList<>();
        //for (Article article : articleList) {
        //    ArticleResponse ar = new ArticleResponse(article);
          //  articleResponsesList.add(ar);
        //}
        List<ArticleResponse> articleResponsesList = blogService.getAll()
                .stream()
                .map(a->new ArticleResponse(a))
                .toList();
        return ResponseEntity.ok().body(articleResponsesList);
    }



}
