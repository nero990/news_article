package article.news.repository;

import article.news.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(@NotNull String name);

    Role findByNameAndIdNot(@NotNull String name, Long id);
}