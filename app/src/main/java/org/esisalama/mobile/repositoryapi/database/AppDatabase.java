package org.esisalama.mobile.repositoryapi.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.esisalama.mobile.repositoryapi.database.Dao.RepositoryDao;
import org.esisalama.mobile.repositoryapi.database.model.Owner;
import org.esisalama.mobile.repositoryapi.database.model.Repository;

@Database(entities = {
        Owner.class,
        Repository.class
}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepositoryDao repositoryDao();
}


