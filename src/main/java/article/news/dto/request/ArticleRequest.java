package article.news.dto.request;

import article.news.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ArticleRequest {
    @NotNull
    @Length(max = 200)
    private String title;

    @Length(max = 255)
    private String description;

    @NotNull
    private String content;

    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date publishedAt;
}
