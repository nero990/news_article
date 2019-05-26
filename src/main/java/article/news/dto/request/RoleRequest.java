package article.news.dto.request;

import javax.validation.constraints.NotNull;

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
