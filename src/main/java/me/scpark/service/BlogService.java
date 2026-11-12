package me.scpark.service;


import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
import me.scpark.dto.UpdateArticleRequest;
import me.scpark.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest dto) {
        return blogRepository.save(dto.toEntity());
    }

    public List<Article> getAll() {

        return blogRepository.findAll();
    }
    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));
    }

    public void deleteArticle(Long id) {
        blogRepository.deleteById(id);
    }

    public Article updateArticle(Long id, UpdateArticleRequest request) {
        Article article = findById(id);
        article.update(request.getTitle(), request.getContent());
        return blogRepository.save(article);
    }


}


