package me.scpark.controller;


import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;


    @GetMapping("/articles")
    public String articles(Model model) {
        List<Article> articles = blogService.getAll();
        model.addAttribute("articles", articles);
        return "articles";
    }
}
