package LAB9.GitClasses;

import lombok.Getter;
import lombok.Setter;

public class Users {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String login;
    @Getter @Setter
    private String avatar_url;
    @Getter @Setter
    private String url;
    @Getter @Setter
    private String html_url;
    @Getter @Setter
    private String repos_url;
    @Getter @Setter
    private String type;

    public Users(Integer id, String login, String avatar_url, String url, String html_url, String repos_url, String type) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.url = url;
        this.html_url = html_url;
        this.repos_url = repos_url;
        this.type = type;
    }
}
