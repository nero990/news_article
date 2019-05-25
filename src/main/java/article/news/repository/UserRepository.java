package article.news.repository;

import article.news.model.User;
import article.news.shared.Writer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameOrEmail(@NotNull String username, @NotNull String email);

    @Query("SELECT new article.news.shared.Writer(CONCAT(u.firstName, ' ', u.lastName), u.email, u.username) FROM User u WHERE u.role.name = 'Writer'")
    Page<Writer> findAllWriters(Pageable pageable);
}
