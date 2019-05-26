package article.news.service.dao;

import article.news.constant.ErrorCode;
import article.news.dto.request.RegisterRequest;
import article.news.dto.request.user.CreateUserRequest;
import article.news.dto.request.user.UpdateUserRequest;
import article.news.exception.ErrorException;
import article.news.model.Role;
import article.news.model.User;
import article.news.repository.ArticleRepository;
import article.news.repository.RoleRepository;
import article.news.repository.SessionRepository;
import article.news.repository.UserRepository;
import article.news.shared.Author;
import article.news.util.CommonUtil;
import article.news.util.PageRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Handles all logic relating to User Management
 *
 * @author Nero Okiewhru
 * @since 2019-05-24
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ArticleRepository articleRepository;
    private final SessionRepository sessionRepository;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ArticleRepository articleRepository, SessionRepository sessionRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.articleRepository = articleRepository;
        this.sessionRepository = sessionRepository;
        this.roleService = roleService;
    }

    // Returns all available users from the database*/
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Returns a Page of users
    public Page<User> getAllUsers(HttpServletRequest request){
        return userRepository.findAll(PageRequestUtil.getPageRequest(request));
    }

    // Find a single user with id. If shouldThrow is set to true, it throws an EntityNotFoundException.
    public User getUser(Long id, boolean shouldThrow) {
        User user = userRepository.findById(id).orElse(null);
        CommonUtil.throwEntityNotFound(shouldThrow, user);
        return user;
    }

    // Creates a new user
    public User createUser(CreateUserRequest userRequest) {
        User user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getUsername(), userRequest.getPassword());

        if(userRequest.getRoleId() == null) {
            // Before author can sign-up, an Author role MUST be created.
            Role author = roleRepository.findByName("Author");
            if(author == null) throw new ErrorException(ErrorCode.INVALID_ENTRY,
                    "Author can't sign-up at the moment, please contact the system administrator.");
            user.setRole(author);
        } else {
            Role role = roleService.getRole(userRequest.getRoleId());
            if(role == null) throw new ErrorException(ErrorCode.INVALID_ENTRY, "Selected role is not valid");
            user.setRole(role);
        }

        User existingUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if(existingUser != null) {
            if(existingUser.getUsername().equals(user.getUsername()))
                throw new ErrorException(ErrorCode.DUPLICATE_ENTRY, "A user with username (" + user.getUsername() + ") already exist.");
            else
                throw new ErrorException(ErrorCode.DUPLICATE_ENTRY, "A user with email address (" + user.getEmail() + ") already exist.");
        }

        return userRepository.save(user);
    }

    //Update the given user. Throws EntityNotFoundException if user does not exist.
    public User updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = getUser(id, true);
        Role role = roleService.getRole(updateUserRequest.getRoleId());
        if(role == null) throw new ErrorException(ErrorCode.INVALID_ENTRY, "Selected role is not valid");
        user.setRole(role);
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setPassword(updateUserRequest.getPassword());
        return userRepository.save(user);
    }

    //Deletes a user. Throws EntityNotFoundException if user does not exist.
    public boolean deleteUser(Long id) {
        User user = getUser(id, true);

        // Ensures the user does not have an article before performing delete
        int articlesCount = Integer.valueOf(articleRepository.countUserArticles(user).toString());
        if(articlesCount > 0) throw new ErrorException(ErrorCode.OPERATION_FAILED, "The user (" + user.getName() + ") has " + articlesCount + " article(s), hence cannot be deleted.");

        // Delete all user sessions
        sessionRepository.deleteAll(sessionRepository.findAllByUser(user));

        // Finally, delete user
        userRepository.delete(user);
        return true;
    }

    // Author sign-up
    public User registerAuthor(RegisterRequest registerRequest) {
        return createUser(new CreateUserRequest(registerRequest.getFirstName(), registerRequest.getLastName(),
                registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getUsername()));
    }

    // Get a page of authors
    public Page<Author> getAuthors(HttpServletRequest request) {
        return userRepository.findAllAuthors(PageRequestUtil.getPageRequest(request));
    }

    // Gets a single author
    public Author getAuthor(String username) {
        User user = userRepository.getAuthorByUsername(username);
        if(user == null) throw new ErrorException(ErrorCode.RESOURCE_NOT_FOUND, "Author not found");
        return new Author(user);
    }
}
