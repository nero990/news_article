package article.news.dto.response;

/**
 * Custom response for delete operations
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class DeleteResponse {
    private Boolean deleted;

    public DeleteResponse(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}