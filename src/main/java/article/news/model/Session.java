package article.news.model;

import article.news.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Session model for user active session / last activities
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Getter
@Setter
@Entity
@Table(name="sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date loggedInAt = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date loggedOutAt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date lastActiveAt = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    @CreatedDate
    private Date createdAt = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    @LastModifiedDate
    private Date updatedAt = new Date();

    public Session() {
    }

    public Session(@NotNull User user) {
        this.user = user;
    }
}
