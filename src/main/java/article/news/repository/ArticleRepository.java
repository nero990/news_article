package article.news.repository;

import article.news.model.Article;
import article.news.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByUser(@NotNull User user, Pageable pageable);

    Article findByUserAndId(@NotNull User user, Long id);

    Article findBySlug(@NotNull String slug);

    Article findBySlugAndIdNot(@NotNull String slug, Long id);


    Article findByPublishedAtNotNullOrderByPublishedAt();
}
