package article.news.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserRequest extends UpdateUserRequest{
    @NotNull
    @Email
    private String email;

    @NotNull
    private String username;

    public CreateUserRequest() {
    }

    public CreateUserRequest(@NotNull String firstName, @NotNull String lastName, @NotNull String password,
                             @NotNull @Email String email, @NotNull String username) {
        super(firstName, lastName, password);
        this.email = email;
        this.username = username;
    }
}
