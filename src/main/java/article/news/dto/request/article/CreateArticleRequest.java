package article.news.dto.request.article;

import article.news.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import java.util.Date;

@Getter
@Setter
public class CreateArticleRequest extends UpdateArticleRequest {

    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date publishedAt;
}
