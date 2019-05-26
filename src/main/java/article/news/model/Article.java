package article.news.model;

import article.news.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Article model for managing users news.
 *
 * @author Nero Okiewhru
 * @since 2019-05-24
 */

@Entity
@Table(name="articles")
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Column(unique = true)
    private String slug;

    private String description;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String content;

    private String imageUrl;

    @JsonProperty("author")
    @NotNull
    @ManyToOne
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date publishedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    @CreationTimestamp
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    @UpdateTimestamp
    private Date updatedAt;

    public Article() {
    }

    public Article(@NotNull String slug, @NotNull String title, String description, @NotNull String content, Date publishedAt, User user) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
        this.user = user;
    }
}
