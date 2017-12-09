package LAB9;

import LAB7.User;
import LAB9.GitClasses.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class QueryGenerator {
    private QueryGenerator(){}

    public static String getGenerateTableUsers(){
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE Users")
                .append("(ID INT PRIMARY KEY    NOT NULL,")
                .append("login         TEXT,")
                .append("Avatar_url    TEXT,")
                .append("URL           TEXT,")
                .append("html_url      TEXT,")
                .append("repos_url     TEXT,")
                .append("type          TEXT);");
        return query.toString();
    }



    public static String getGenerateTableLanguages(){
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE Languages")
                .append("(ID INT PRIMARY KEY    NOT NULL,")
                .append("name         TEXT,");
        return query.toString();
    }


    public static String getGenerateTableRepositories(){
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE Repositories")
                .append("(ID INT PRIMARY KEY    NOT NULL,")
                .append("name         TEXT,")
                .append("fullName     TEXT,")
                .append("private      boolean,")
                .append("html_url     TEXT,")
                .append("language_id     INT  references Languages(ID),")
                .append("description  TEXT);");
        return query.toString();
    }


    public static String getGenerateTableContributors(){
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE Contributors")
                .append("(ID INT PRIMARY KEY    NOT NULL,")
                .append("user_id     INT references Users(ID),")
                .append("repositories_id  INT references Repositories(ID));");
        return query.toString();
    }

    public static String getGenerateTableRepositoryOwners(){
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE Owners")
                .append("(ID INT PRIMARY KEY    NOT NULL,")
                .append("user_id     INT references Users(ID),")
                .append("repositories_id  INT references Repositories(ID) UNIQUE);");
        return query.toString();
    }

   public static String insertIntoUsers(Connection con, Users user) throws Exception{
       StringBuilder query = new StringBuilder();
       query.append("INSERT INTO Users").append(" Values(?, ?, ?, ?, ?, ?, ?);");
       PreparedStatement pstmt = con.prepareStatement(query.toString());
       pstmt.setInt(1, user.getId());
       pstmt.setString(2,user.getLogin());
       pstmt.setString(3, user.getAvatar_url());
       pstmt.setString(4, user.getUrl());
       pstmt.setString(5, user.getHtml_url());
       pstmt.setString(6, user.getRepos_url());
       pstmt.setString(7, user.getType());
       return pstmt.toString();
   }

    public static String insertIntoLanguages(Connection con, Languages lang) throws Exception{
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Languages").append(" Values( ?);");
        PreparedStatement pstmt = con.prepareStatement(query.toString());
        pstmt.setString(1, lang.getName());
        return pstmt.toString();
    }

    public static String insertIntoRepositories(Connection con, Repositories repository) throws Exception{
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Repositories").append(" Values(?, ?, ?, ?, ?, ?, ?);");
        PreparedStatement pstmt = con.prepareStatement(query.toString());
        pstmt.setInt(1, repository.getId());
        pstmt.setString(2, repository.getName());
        pstmt.setString(3, repository.getFullName());
        pstmt.setBoolean(4, repository.isPrivate());
        pstmt.setString(5, repository.getHtml_url());
        pstmt.setString(6, repository.getLanguage().getName());
        pstmt.setString(7, repository.getDescription());
        return pstmt.toString();
    }


    public static String insertIntoContributors(Connection con, Contributors contributor, Repositories repository) throws Exception{
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Contributors").append(" Values(?, ?, ?, ?);");
        PreparedStatement pstmt = con.prepareStatement(query.toString());
        pstmt.setInt(1, contributor.getId());
        pstmt.setInt(2, contributor.getUser().getId());
        pstmt.setInt(3, repository.getId());
        pstmt.setInt(4, contributor.getCommits());
        return pstmt.toString();
    }


    public static String insertIntoRepositoryOwners(Connection con, Repositories repository) throws Exception{
        StringBuilder query = new StringBuilder();
        Random random = new Random();
        query.append("INSERT INTO RepositoryOwners").append(" Values(?, ?, ?);");
        PreparedStatement pstmt = con.prepareStatement(query.toString());
        pstmt.setInt(1, random.nextInt());
        pstmt.setInt(2, repository.getOwner().getId());
        pstmt.setInt(3, repository.getId());
        return pstmt.toString();
    }


    public static String getUsersRepositories(){
        return  new StringBuilder().append("Select u.login, r.name From")
                .append(" Users u inner join RepositoryOwners ro on  u.id = ro.user_id  inner join  Repositories r  on r.id = ro.repositories_id;").toString();
    }


    public static String getRepositoryLang(){
        return "Select r.name, lang.name" +
                " From Repositories r  inner join languages lang on r.language = lang.name;";
    }


    public static String getContributorsRepository(){
        return  "Select r.name, u.login, cont.commits " +
                "From Repositories r  inner join Contributors cont on r.id = cont.repositories_id inner join Users u on u.id = cont.user_id ";
    }

    public static String getCommitsData(){
        return "Select r.name, count(u), sum(cont.commits) total_commits " +
                "From Repositories r  inner join Contributors cont on r.id = cont.repositories_id inner join Users u on u.id = cont.user_id " +
                "Group BY r.name";
    }

    public static String getCommitsUser(){
        return "Select u.login, count(rep)" +
                " From Users u inner join Contributors cont on cont.user_id = u.id inner join Repositories rep on rep.id = cont.repositories_id" +
                " Group by u.login";
    }
}
