package article.news.shared;

import article.news.model.User;

/**
 * Custom User Class for rendering uses having a role of Author
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class Author {
    private String name;
    private String email;
    private String username;

    public Author(User user) {
        this(user.getFirstName() + " " + user.getLastName(), user.getEmail(), user.getUsername());
    }

    public Author(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
