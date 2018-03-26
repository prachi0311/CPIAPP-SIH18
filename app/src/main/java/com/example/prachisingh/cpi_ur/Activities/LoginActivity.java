package com.example.prachisingh.cpi_ur.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.ApiResponses.SignInResponse;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.Network.ApiInterface;
import com.example.prachisingh.cpi_ur.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_username)
    EditText userName;
    @BindView(R.id.login_password)
    EditText password;
    @BindView(R.id.sign_up)
    TextView signUpButton;
    @BindView(R.id.Login)
    TextView login;
    String tag="signIn";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String acessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sp=getSharedPreferences("CPI",MODE_PRIVATE);
        editor=sp.edit();
        if(!sp.getString("access_token","").equals("")){
            Intent i=new Intent(LoginActivity.this,ScheduleActivity.class);
            i.putExtra("access_token",acessToken);
            startActivity(i);
        }
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,SignUp.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });
    }

    private void signIn() {
        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
        Call<SignInResponse> call=apiInterface.userSignIn(userName.getText().toString(),password.getText().toString());
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.body().getStatus()==200){
                    acessToken=response.body().getData().getAccessToken();
                    editor.putString("access_token",response.body().getData().getAccessToken());
                    editor.commit();
                    Intent i=new Intent(LoginActivity.this,ScheduleActivity.class);
                    i.putExtra("access_token",acessToken);
                    startActivity(i);
                    Log.i(tag,response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

            }
        });
    }
}
