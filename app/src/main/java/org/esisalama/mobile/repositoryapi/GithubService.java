package org.esisalama.mobile.repositoryapi;

import org.esisalama.mobile.repositoryapi.database.model.GithubRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {
    @GET("users/NathanMwape/repos")
    Call<List<GithubRepository>> getRepositories();
}
