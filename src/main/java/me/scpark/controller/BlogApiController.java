package me.scpark.controller;

import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
import me.scpark.dto.ArticleResponse;
import me.scpark.dto.UpdateArticleRequest;
import me.scpark.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ArticleResponse(savedArticle));
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
       // List<Article> articleList = blogService.getAll();
        //List<ArticleResponse> articleResponsesList = new ArrayList<>();
        //for (Article article : articleList) {
        //    ArticleResponse ar = new ArticleResponse(article);
          //  articleResponsesList.add(ar);=
        //}
        List<ArticleResponse> articleResponsesList = blogService.getAll()
                .stream()
                .map(a->new ArticleResponse(a))
                .toList();
        return ResponseEntity.ok().body(articleResponsesList);
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(new ArticleResponse(blogService.findById(id)));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id,
                                                         @RequestBody UpdateArticleRequest req) {
        Article article = blogService.updateArticle(id,req);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }



}
