package article.news.database.seeder;

import article.news.model.Role;
import article.news.model.User;
import article.news.repository.RoleRepository;
import article.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Seeds setup data
 *
 * @author Nero Okiewhru
 * @since 2019-05-26
 */
@Component
public class SetupSeeder {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public SetupSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        if(ddlAuto.equals("create")){ // Seed data only when ddl is set to create
            // Creating Default Roles
            Role administrator = roleRepository.save(new Role("Administrator"));
            roleRepository.save(new Role("Author"));
            roleRepository.save(new Role("Editor"));

            // Creating system user
            User user = new User(
                    "Nero",
                    "Okiewhru",
                    "admin@example.com",
                    "admin",
                    "password"
            );
            user.setRole(administrator);

            userRepository.save(user);
        }
    }
}
