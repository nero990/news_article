package article.news.service.dao;

import article.news.constant.ErrorCode;
import article.news.dto.request.RegisterRequest;
import article.news.dto.request.user.CreateUserRequest;
import article.news.dto.request.user.UpdateUserRequest;
import article.news.exception.ErrorException;
import article.news.model.Role;
import article.news.model.User;
import article.news.repository.RoleRepository;
import article.news.repository.UserRepository;
import article.news.shared.Writer;
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
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    /*
    * Returns all available users from the database*/
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /*
    * Find a single user with id. If shouldThrow is set to true, it throws an EntityNotFoundException.
    * else return null */
    public User getUser(Long id, boolean shouldThrow) {
        User user = userRepository.findById(id).orElse(null);
        CommonUtil.throwEntityNotFound(shouldThrow, user);
        return user;
    }

    /*
    * Creates a new user
    *
    * */
    public User createUser(CreateUserRequest userRequest) {
        User user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getUsername(), userRequest.getPassword());

        if(userRequest.getRoleId() == null) {
            // Before writers can sign-up, a WRITER role MUST be created.
            Role writer = roleRepository.findByName("Writer");
            if(writer == null) throw new ErrorException(ErrorCode.INVALID_ENTRY,
                    "Writer can't sign-up at the moment, please contact the system administrator.");
            user.setRole(writer);
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

    /*
    * Update the given user. Throws EntityNotFoundException if user does not exist.
    * */
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

    /*
    * Deletes a user. Throws EntityNotFoundException if user does not exist.
    * */
    public boolean deleteUser(Long id) {
        userRepository.delete(getUser(id, true));
        return true;
    }

    // Writer sign-up
    public User registerWriter(RegisterRequest registerRequest) {
        return createUser(new CreateUserRequest(registerRequest.getFirstName(), registerRequest.getLastName(),
                registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getUsername()));
    }

    public Page<Writer> getWriters(HttpServletRequest request) {
        return userRepository.findAllWriters(PageRequestUtil.getPageRequest(request));
    }
}
