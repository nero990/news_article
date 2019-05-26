package article.news.dto.response;

import article.news.constant.CommonConstant;
import article.news.model.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Custom News response
 *
 * @author Nero Okiewhru
 * @since 2019-05-26
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class News {
    private String title;
    private String description;
    private String content;
    private String slug;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date publishedAt;

    private String author;

    public News(Article article) {
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.content = article.getContent();
        this.slug = article.getSlug();
        this.publishedAt = article.getPublishedAt();
        this.author = article.getUser().getName();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getSlug() {
        return slug;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getAuthor() {
        return author;
    }
}
