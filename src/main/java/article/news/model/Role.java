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
 * Role model for managing user roles of the application. A user must belong to one role.
 *
 * @author Nero Okiewhru
 * @since 2019-05-24
 */


@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 20)
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATETIME_FORMAT)
    @CreatedDate
    protected Date createdAt = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATETIME_FORMAT)
    @LastModifiedDate
    protected Date updatedAt = new Date();
}
