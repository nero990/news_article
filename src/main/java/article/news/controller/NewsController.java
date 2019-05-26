package article.news.controller;

import article.news.dto.response.News;
import article.news.service.dao.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages Published articles
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Api(tags = {"News"})
@RestController
@RequestMapping("/news")
public class NewsController {

    private final ArticleService articleService;

    @Autowired
    public NewsController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation(value = "List all published articles", response = News.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<Page<News>> getPublishedArticles(HttpServletRequest request) {
        return ResponseEntity.ok(articleService.getPublishedArticles(request));
    }

    @ApiOperation(value = "Show a single published articles", response = News.class, responseContainer = "List")
    @GetMapping("/{slug}")
    public ResponseEntity<News> getPublishedArticle(@PathVariable String slug) {
        return ResponseEntity.ok(articleService.getPublishedArticle(slug));
    }
}
