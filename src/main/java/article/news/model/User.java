package article.news.model;

import article.news.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * User model for managing all users of the application. Every user is expected to have a role
 *
 * @author Nero Okiewhru
 * @since 2019-05-24
 */

@Entity
@Table(name="users")
@Getter
@Setter
@Proxy(lazy = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true, length = 100)
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @NotNull
    @Column(unique = true, length = 100)
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    @CreatedDate
    protected Date createdAt = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstant.DATE_TIME_FORMAT)
    @LastModifiedDate
    protected Date updatedAt = new Date();

    public User() {
    }

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull String username, @NotNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
