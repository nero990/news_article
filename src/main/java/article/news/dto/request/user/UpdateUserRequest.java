package article.news.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;

    @NotNull
    private Long roleId;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(@NotNull String firstName, @NotNull String lastName, @NotNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
