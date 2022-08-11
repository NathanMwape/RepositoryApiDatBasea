package org.esisalama.mobile.repositoryapi.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Repository {
    @PrimaryKey
    public int id;
    public String name;
    public int owner_id;

    public Repository() {
    }

    public Repository(int id, String name, int owner_id) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
    }
}
