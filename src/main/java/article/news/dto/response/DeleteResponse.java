package article.news.dto.response;

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