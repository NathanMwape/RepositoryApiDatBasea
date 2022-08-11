package org.esisalama.mobile.repositoryapi.database.model;

public class GithubRepository {
    public int id;
    public String name;
    public GithubOwner owner;

    public Repository toRepository() {
        return new Repository(this.id,this.name,this.owner.id);
    }
}
