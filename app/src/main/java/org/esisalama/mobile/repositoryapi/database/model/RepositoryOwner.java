package org.esisalama.mobile.repositoryapi.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RepositoryOwner {
    @Embedded
    public Owner owner;

    @Relation(
            parentColumn = "id",
            entityColumn = "owner_id"
    )
    public List<Repository> repositories;
}
