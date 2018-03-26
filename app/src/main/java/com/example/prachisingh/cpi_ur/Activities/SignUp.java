package com.example.prachisingh.cpi_ur.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.ApiResponses.SignInResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.SignUpResponse;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.Network.ApiInterface;
import com.example.prachisingh.cpi_ur.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    @BindView(R.id.signup_username)
    EditText userName;
    @BindView(R.id.signup_password)
    EditText password;
    @BindView(R.id.signup_confirm_password)
    EditText confirmPassword;
    @BindView(R.id.signup)
    TextView SignUpButton;
    String tag="sign Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();

            }
        });
    }

    private void signUp() {
        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
        Call<SignUpResponse> call=apiInterface.userSignUp(userName.getText().toString(),password.getText().toString(),confirmPassword.getText().toString());
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.i("code",String.valueOf(response.code()));
                if(response.body().getStatus()==200 && response.body().getMessage().equals("Success")){

                    Log.i(tag,response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }
}
