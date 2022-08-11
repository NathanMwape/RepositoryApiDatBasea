package org.esisalama.mobile.repositoryapi.database.model;

public class GithubOwner {
    public int id;
    public String login;

    public Owner toOwner() {
        return new Owner(this.id,this.login);
    }
}
