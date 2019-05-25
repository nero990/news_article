package article.news.model;

import article.news.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    protected Long id;

    @NotNull
    private String title;

    @Lob
    @NotNull
    private String description;

    @Lob
    @NotNull
    private String content;

    private String imageUrl;

    @JsonProperty("author")
    @NotNull
    @ManyToOne
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATETIME_FORMAT)
    private Date publishedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATETIME_FORMAT)
    @CreatedDate
    protected Date createdAt = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATETIME_FORMAT)
    @LastModifiedDate
    protected Date updatedAt = new Date();

}
