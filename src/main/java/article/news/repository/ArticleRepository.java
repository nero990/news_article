package article.news.repository;

import article.news.dto.response.News;
import article.news.model.Article;
import article.news.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT COUNT(a) FROM Article a WHERE a.user = :user")
    Object countUserArticles(@Param("user") @NotNull User user);

    Page<Article> findAllByUser(@NotNull User user, Pageable pageable);

    Article findByUserAndId(@NotNull User user, Long id);

    Article findBySlug(@NotNull String slug);

    Article findBySlugAndIdNot(@NotNull String slug, Long id);

    @Query("SELECT new article.news.dto.response.News(a) FROM Article a WHERE a.publishedAt <= CURRENT_TIMESTAMP")
    Page<News> getPublishedArticles(Pageable pageable);

    Article findBySlugAndPublishedAtLessThanEqual(@NotNull String slug, Date publishedAt);
}
