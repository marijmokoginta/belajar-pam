package com.app.belajarretrofit.services.endpoints;

import com.app.belajarretrofit.models.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubEndpoint {

    @GET("users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);
}