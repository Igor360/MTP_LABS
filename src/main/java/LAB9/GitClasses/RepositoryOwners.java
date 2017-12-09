package LAB9.GitClasses;

import com.sun.jmx.mbeanserver.Repository;
import lombok.Getter;
import lombok.Setter;

public class RepositoryOwners extends Users{
    public RepositoryOwners(Integer id, String login, String avatar_url, String url, String html_url, String repos_url, String type){
        super(id, login, avatar_url, url, html_url, repos_url, type);
    }
}

