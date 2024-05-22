package com.app.belajarfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signInButton;
    private Button signOutButton;
    private ImageView image;
    private TextView name, email;
    private GoogleApiClient client;
    private static final String TAG = "MainActicity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.id_token))
                .requestEmail()
                .build();
        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();

        image = findViewById(R.id.profile_img_2);
        name = findViewById(R.id.name_2);
        email = findViewById(R.id.email_2);
        signInButton = findViewById(R.id.signin_btn_2);
        signOutButton = findViewById(R.id.sign_out_btn);

        signInButton.setOnClickListener(v -> {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(client);
            startActivityForResult(signInIntent, 1234);
        });

        signOutButton.setOnClickListener(v -> {
            Auth.GoogleSignInApi.signOut(client).setResultCallback(status -> {
                image.setVisibility(View.INVISIBLE);
                name.setText("Logged Out");
                email.setText("");
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (signInResult.isSuccess()) {
                GoogleSignInAccount signInAccount = signInResult.getSignInAccount();
                assert signInAccount != null;
                Glide.with(MainActivity.this).load(signInAccount.getPhotoUrl()).into(image);
                name.setText(signInAccount.getDisplayName());
                email.setText(signInAccount.getEmail());
            }
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, connectionResult.getErrorMessage());
    }
}