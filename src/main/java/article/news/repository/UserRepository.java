package article.news.repository;

import article.news.model.User;
import article.news.shared.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * UserRepository
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameOrEmail(@NotNull String username, @NotNull String email);

    @Query("SELECT new article.news.shared.Author(CONCAT(u.firstName, ' ', u.lastName), u.email, u.username) " +
            "FROM User u " +
            "WHERE u.role.name = article.news.constant.CommonConstant.AUTHOR")
    Page<Author> findAllAuthors(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.role.name = article.news.constant.CommonConstant.AUTHOR AND u.username = :username")
    User getAuthorByUsername(@Param("username") @NotNull String username);
}
