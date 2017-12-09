package LAB9.GitClasses;

import LAB7.User;
import com.sun.jmx.mbeanserver.Repository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Contributors {

    @Setter @Getter
    private Integer id;

    @Getter @Setter
    private Users user = null;
    @Getter @Setter
    private Integer commits = null;


    public Contributors(Users users, Integer commits) {
        this.id = (new Random()).nextInt();
        this.user = users;
        this.commits = commits;
    }

    public Contributors(Integer id, Users users, Integer commits) {
        this.id = id;
        this.user = users;
        this.commits = commits;
    }
}

