package article.news.repository;

import article.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameOrEmail(@NotNull String username, @NotNull String email);
}
