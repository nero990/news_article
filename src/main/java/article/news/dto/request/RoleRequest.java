package article.news.dto.request;

import javax.validation.constraints.NotNull;

/**
 * Role request class
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class RoleRequest {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
