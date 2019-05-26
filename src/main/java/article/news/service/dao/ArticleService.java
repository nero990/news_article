package article.news.service.dao;

import article.news.dto.request.ArticleRequest;
import article.news.dto.response.News;
import article.news.model.Article;
import article.news.model.User;
import article.news.repository.ArticleRepository;
import article.news.util.CommonUtil;
import article.news.util.PageRequestUtil;
import article.news.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<Article> getArticles(User author, HttpServletRequest request) {
        return articleRepository.findAllByUser(author, PageRequestUtil.getPageRequest(request));
    }

    public Article getArticle(User author, Long articleId, Boolean shouldThrow) {
        Article article = articleRepository.findByUserAndId(author, articleId);
        CommonUtil.throwEntityNotFound(shouldThrow, article);
        return article;
    }

    public Article createArticle(ArticleRequest articleRequest, User user) {
        return articleRepository.save(new Article(
                getUniSlug(articleRequest, null),
                articleRequest.getTitle(),
                articleRequest.getDescription(),
                articleRequest.getContent(),
                articleRequest.getPublishedAt(),
                user
        ));
    }

    public Article updateArticle(Long articleId, User author, ArticleRequest articleRequest) {
        Article article = getArticle(author, articleId, true);
        article.setSlug(getUniSlug(articleRequest, articleId));
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        article.setContent(articleRequest.getContent());
        article.setPublishedAt(articleRequest.getPublishedAt());
        return articleRepository.save(article);
    }

    private String getUniSlug(ArticleRequest articleRequest, Long id) {
        SecureRandom secureRandom = new SecureRandom();
        String slug;
        int count = 0;
        do{
            slug = StringUtils.generateSlug(articleRequest.getTitle());
            if(count > 0){
                slug +=  "-" + secureRandom.nextInt(1000);
            }
            ++count;
        } while (id == null ? articleRepository.findBySlug(slug) != null : articleRepository.findBySlugAndIdNot(slug, id) != null);
        return slug;
    }

    public Boolean deleteArticle(Long id, User author) {
        articleRepository.delete(getArticle(author, id, true));
        return true;
    }

    // Returns all articles that have been published
    public Page<News> getPublishedArticles(HttpServletRequest request) {
        return null;
    }

    // Returns a single published article by its unique slug
    public News getPublishedArticle(String slug) {
        return null;
//        return articleRepository.findAllByUser()
    }
}
