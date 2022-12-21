package com.example.test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.test.Api.ApiService;
import com.example.test.Helper.ContextHelper;
import com.example.test.Model.AuthResponse;
import com.example.test.Model.LoginModel;

import java.time.LocalDateTime;
import java.time.ZoneId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public AuthResponse authInfo;
    TextView fullName;
    TextView password;

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextHelper.getInstance());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        fullName = findViewById(R.id.fullname);
        password = findViewById(R.id.passwprd);


        Button btn = findViewById(R.id.loginBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = fullName.getText().toString();
                String pass = password.getText().toString();
                LoginModel login = new LoginModel(userName,pass);
                Login(login);




            }
        });






    }
    void Login(LoginModel login){

        ApiService.apiService.login(login).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful())
                {
                    authInfo = response.body();
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("accessToken", authInfo.accessToken);
                    editor.putString("refreshToken", authInfo.refreshToken);


                    editor.apply();


                    Log.d("Main",response.headers().toString());
                    Log.d("Main",Integer.toString(response.code()));
                    Toast.makeText(LoginActivity.this,"Login thanh con", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Login that bai" , Toast.LENGTH_SHORT).show();

                }




            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Login that bai", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
