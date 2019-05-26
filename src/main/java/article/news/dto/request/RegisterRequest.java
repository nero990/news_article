package article.news.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * User registration/sign-up request class
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Getter
@Setter
public class RegisterRequest {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
