package article.news.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserRequest extends UpdateUserRequest{
    @NotNull
    private String email;

    @NotNull
    private String username;
}
