package org.esisalama.mobile.repositoryapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.esisalama.mobile.repositoryapi.database.AppDatabase;
import org.esisalama.mobile.repositoryapi.database.model.GithubRepository;
import org.esisalama.mobile.repositoryapi.database.model.Repository;
import org.esisalama.mobile.repositoryapi.database.model.RepositoryOwner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView userName;
    private TextView repos1;
    private TextView repo2;
    private Button soumtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiserComposant();
        getData();
    }

    private void initialiserComposant(){
        userName = findViewById(R.id.user_name);
        repos1 = findViewById(R.id.repos1);
        repo2 = findViewById(R.id.repo2);
        soumtButton = findViewById(R.id.buttonSoumettre);
    }

    private void getData() {

                soumtButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDatabase.class, "database")
                                .allowMainThreadQueries()
                                .build();
                        syncData(db);

                        RepositoryOwner repositoryOwners = db.repositoryDao().findOwner();

                        userName.setText(repositoryOwners.owner.getLogin());
                        repos1.setText(repositoryOwners.repositories.get(0).name);
                        repo2.setText(repositoryOwners.repositories.get(1).name);
                    }
                });

    }

    private void syncData(AppDatabase db) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService githubService = retrofit.create(GithubService.class);

        Call<List<GithubRepository>> githubCallback = githubService.getRepositories();

        githubCallback.enqueue(new Callback<List<GithubRepository>>() {
            @Override
            public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response) {
                if (response.isSuccessful()) {

                    List<GithubRepository> githubRepositories = response.body();
                    if (githubRepositories == null) {
                        Toast.makeText(MainActivity.this, "User no found", Toast.LENGTH_SHORT).show();
                    } else {

                        List<Repository> repositories = new ArrayList<>();

                        for (GithubRepository githubRepository : githubRepositories) {
                            repositories.add(githubRepository.toRepository());
                        }

                        db.repositoryDao().insertAll(repositories);
                        db.repositoryDao().insertOwner(githubRepositories.get(0).owner.toOwner());

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<GithubRepository>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}