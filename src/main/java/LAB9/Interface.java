package LAB9;

import LAB8.Client;

import java.io.IOException;

public class Interface {

    public static void main(String[] args){
        try {
            //GitHubClient.getAllRepositories();
            //GitHubClient.getInformationAboutRepository();
           // GitHubClient.saveRepositiories();
           // GitHubClient.saveContributors();
            GitHubClient.getRepositoryOwner();
            GitHubClient.getRepositoryLanguage();
            GitHubClient.getRepositoryContributors();
            GitHubClient.getRepositoryNumContributors();
            GitHubClient.getUserCommits();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
