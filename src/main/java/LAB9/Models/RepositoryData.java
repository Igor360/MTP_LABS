package LAB9.Models;

import lombok.Getter;

public class RepositoryData {
    @Getter
    private String name;
    @Getter
    private String lang;
    @Getter
    private String numContributors = null;
    @Getter
    private String totalCommits = null;

    public RepositoryData(String name, String numContributors, String totalCommits) {
        this.name = name;
        this.numContributors = numContributors;
        this.totalCommits = totalCommits;
    }

    public RepositoryData(String name, String lang) {
        this.name = name;
        this.lang = lang;
    }
}
