package me.scpark.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
import me.scpark.dto.UpdateArticleRequest;
import me.scpark.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void cleanUp() {
        blogRepository.deleteAll();
    }

    private Article saveArticle(String title, String content) {
        return blogRepository.save(new Article(title, content));
    }

    @DisplayName("1. POST /api/articles : 成功添加博客文章")
    @Test
    void addArticle() throws Exception {
        String title = "Spring Boot 学习记录";
        String content = "第 11 周：完成文章增删改查接口与 MockMvc 测试";
        AddArticleRequest request = new AddArticleRequest(title, content);
        String requestBody = objectMapper.writeValueAsString(request);
        String url = "/api/articles";

        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));

        List<Article> articles = blogRepository.findAll();
        assertThat(articles).hasSize(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("2. GET /api/articles : 成功查询全部文章")
    @Test
    void getAllArticles() throws Exception {
        saveArticle("Java 基础", "面向对象三大特性：封装、继承、多态");
        saveArticle("Spring 入门", "IoC 容器与依赖注入");

        ResultActions result = mockMvc.perform(get("/api/articles"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Java 基础"))
                .andExpect(jsonPath("$[0].content").value("面向对象三大特性：封装、继承、多态"))
                .andExpect(jsonPath("$[1].title").value("Spring 入门"))
                .andExpect(jsonPath("$[1].content").value("IoC 容器与依赖注入"));
    }

    @DisplayName("3. GET /api/articles/{id} : 成功查询单篇文章")
    @Test
    void getArticle() throws Exception {
        Article article = saveArticle("单篇查询的标题", "单篇查询的内容");
        Long id = article.getId();

        ResultActions result = mockMvc.perform(get("/api/articles/{id}", id));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("单篇查询的标题"))
                .andExpect(jsonPath("$.content").value("单篇查询的内容"));
    }

    @DisplayName("4. DELETE /api/articles/{id} : 成功删除单篇文章")
    @Test
    void deleteArticle() throws Exception {
        Article article = saveArticle("即将删除的文章", "这篇文章马上就会被删除");
        Long id = article.getId();
        assertThat(blogRepository.existsById(id)).isTrue();

        ResultActions result = mockMvc.perform(delete("/api/articles/{id}", id));

        result.andExpect(status().isOk());
        assertThat(blogRepository.existsById(id)).isFalse();
        assertThat(blogRepository.findAll()).isEmpty();
    }

    @DisplayName("5. PUT /api/articles/{id} : 成功修改单篇文章")
    @Test
    void updateArticle() throws Exception {
        Article article = saveArticle("更新前的标题", "更新前的内容");
        Long id = article.getId();
        String newTitle = "更新后的标题";
        String newContent = "更新后的内容";
        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions result = mockMvc.perform(put("/api/articles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(newTitle))
                .andExpect(jsonPath("$.content").value(newContent));

        Article updated = blogRepository.findById(id).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo(newTitle);
        assertThat(updated.getContent()).isEqualTo(newContent);
    }
}
