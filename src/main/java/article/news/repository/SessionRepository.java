package article.news.repository;

import article.news.model.Session;
import article.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * SessionRepository
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAllByUser(@NotNull User user);
}