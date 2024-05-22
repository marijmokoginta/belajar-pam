package com.app.belajarretrofit.services;

import com.app.belajarretrofit.services.endpoints.GithubEndpoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

    private static final String BASE_URL = "https://api.github.com/";

    public static GithubEndpoint endpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GithubEndpoint.class);
    }
}