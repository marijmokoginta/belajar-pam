package com.app.belajarretrofit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.belajarretrofit.models.GithubUser;
import com.app.belajarretrofit.services.GithubService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView name, location, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        bio = findViewById(R.id.bio);

        GithubService.endpoint().getUser("marijmokoginta")
                .enqueue(new Callback<GithubUser>() {
                    @Override
                    public void onResponse(@NonNull Call<GithubUser> call, @NonNull Response<GithubUser> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            name.setText(response.body().getName());
                            location.setText(response.body().getLocation());
                            bio.setText(response.body().getBio());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GithubUser> call, @NonNull Throwable throwable) {
                        name.setText("gagal");
                    }
                });
    }
}