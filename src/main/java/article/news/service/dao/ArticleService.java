package article.news.service.dao;

import article.news.dto.request.article.CreateArticleRequest;
import article.news.dto.request.article.UpdateArticleRequest;
import article.news.dto.response.News;
import article.news.model.Article;
import article.news.model.User;
import article.news.repository.ArticleRepository;
import article.news.util.CommonUtil;
import article.news.util.PageRequestUtil;
import article.news.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Date;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Fetches all articles that belongs to an author
    public Page<Article> getArticles(User author, HttpServletRequest request) {
        return articleRepository.findAllByUser(author, PageRequestUtil.getPageRequest(request));
    }

    // Fetches a single article for a particular author
    public Article getArticle(User author, Long articleId, Boolean shouldThrow) {
        Article article = articleRepository.findByUserAndId(author, articleId);
        CommonUtil.throwEntityNotFound(shouldThrow, article);
        return article;
    }

    // Create an article for an author
    public Article createArticle(CreateArticleRequest articleRequest, User user) {
        return articleRepository.save(new Article(
                getUniqueSlug(articleRequest.getTitle(), null),
                articleRequest.getTitle(),
                articleRequest.getDescription(),
                articleRequest.getContent(),
                articleRequest.getPublishedAt(),
                user
        ));
    }

    // Update's an author's article
    public Article updateArticle(Long articleId, User author, UpdateArticleRequest articleRequest) {
        Article article = getArticle(author, articleId, true);
        article.setSlug(getUniqueSlug(articleRequest.getTitle(), articleId));
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        article.setContent(articleRequest.getContent());
        article.setPublishedAt(articleRequest.getPublishedAt());
        return articleRepository.save(article);
    }

    // Generates a unique slug (by checking if it already exist) for the article
    private String getUniqueSlug(String title, Long id) {
        SecureRandom secureRandom = new SecureRandom();
        String slug;
        int count = 0;
        do{
            slug = StringUtils.generateSlug(title);
            if(count > 0){
                slug +=  "-" + secureRandom.nextInt(1000);
            }
            ++count;
        } while (id == null ? articleRepository.findBySlug(slug) != null : articleRepository.findBySlugAndIdNot(slug, id) != null);
        return slug;
    }

    // Delete an author's article
    public Boolean deleteArticle(Long id, User author) {
        articleRepository.delete(getArticle(author, id, true));
        return true;
    }

    // Returns all articles that have been published
    public Page<News> getPublishedArticles(HttpServletRequest request) {
        return articleRepository.getPublishedArticles(PageRequestUtil.getPageRequest(request));
    }

    // Returns a single published article by its unique slug
    public News getPublishedArticle(String slug) {
        Article article = articleRepository.findBySlugAndPublishedAtLessThanEqual(slug, new Date());
        CommonUtil.throwEntityNotFound(true, article);
        return new News(article);
    }
}
