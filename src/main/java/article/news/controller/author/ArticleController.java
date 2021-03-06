package article.news.controller.author;

import article.news.dto.request.article.CreateArticleRequest;
import article.news.dto.request.article.UpdateArticleRequest;
import article.news.dto.response.DeleteResponse;
import article.news.util.ResponseBuilder;
import article.news.model.Article;
import article.news.service.dao.ArticleService;
import article.news.shared.Author;
import article.news.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Manages an author's articles
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Api(tags = {"Authors"})
@RestController(value = "authorArticleController")
@RequestMapping("/authors/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation(value = "List the authenticated author's articles", response = Author.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<Page<Article>> getArticles(HttpServletRequest request) {
        return ResponseEntity.ok(articleService.getArticles(RequestUtil.getUser(), request));
    }

    @ApiOperation(value = "Get an article for the authenticated author", response = Author.class)
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticle(RequestUtil.getUser(), id,true));
    }

    @ApiOperation(value = "Create an article for the authenticated author", response = Author.class)
    @PostMapping
    public ResponseEntity<Article> createArticle(@Valid @RequestBody CreateArticleRequest articleRequest) {
        Article article = articleService.createArticle(articleRequest, RequestUtil.getUser());
        return ResponseBuilder.created(article, article.getId());
    }

    @ApiOperation(value = "Update an article for the authenticated author", response = Author.class)
    @PutMapping("/{id}")
    public ResponseEntity<Article> createArticle(@PathVariable Long id, @Valid @RequestBody UpdateArticleRequest articleRequest) {
        return ResponseEntity.ok(articleService.updateArticle(id, RequestUtil.getUser(), articleRequest));
    }

    @ApiOperation(value = "Delete an article for the authenticated author", response = DeleteResponse.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteArticle(@PathVariable Long id) {
        return ResponseEntity.ok(new DeleteResponse(articleService.deleteArticle(id, RequestUtil.getUser())));
    }
}
