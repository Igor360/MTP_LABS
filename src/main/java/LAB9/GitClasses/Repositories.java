package LAB9.GitClasses;

import LAB9.GitClasses.Contributors;
import lombok.Getter;
import lombok.Setter;



public class Repositories {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String fullName;
    @Getter @Setter
    private RepositoryOwners owner;
    @Getter @Setter
    private boolean isPrivate;
    @Getter @Setter
    private String html_url;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Languages language;


    public Repositories(Integer id, String name, String fullName, RepositoryOwners owner, boolean isPrivate, String html_url, String description, Languages lang) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.isPrivate = isPrivate;
        this.html_url = html_url;
        this.description = description;
        this.language = lang;
    }


}
