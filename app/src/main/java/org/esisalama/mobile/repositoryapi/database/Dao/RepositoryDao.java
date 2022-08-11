package org.esisalama.mobile.repositoryapi.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import org.esisalama.mobile.repositoryapi.database.model.Owner;
import org.esisalama.mobile.repositoryapi.database.model.Repository;
import org.esisalama.mobile.repositoryapi.database.model.RepositoryOwner;

import java.util.List;

@Dao
public interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwner(Owner owner);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Repository> repositories);

    @Transaction
    @Query("SELECT * FROM Owner LIMIT 1")
    RepositoryOwner findOwner();


}
