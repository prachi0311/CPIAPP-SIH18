package com.example.prachisingh.cpi_ur.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    @BindView(R.id.login_password_edit_text)
    EditText password;
    @BindView(R.id.sign_up)
    Button signUpButton;
    @BindView(R.id.Login)
    Button login;
    String tag="signIn";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String acessToken;
    int userId;
    double latitude;
    double longitude;
    ProgressDialog progressDialog;
   // String userNameString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sp=getSharedPreferences("CPI",MODE_PRIVATE);
        editor=sp.edit();
//        if(sp.getString("access_token","")!=null){
//            Intent i=new Intent(LoginActivity.this,MainActivity.class);
//            i.putExtra("access_token",sp.getString("access_token",null));
//            i.putExtra("user_id",sp.getInt("user_id",-1));
//            i.putExtra("user_lat",sp.getFloat("user_lat",-1));
//            i.putExtra("user_lon",sp.getFloat("user_lon",-1));
//            startActivity(i);
//        }
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,SignUp.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("loging in");
                progressDialog.show();
                signIn();

            }
        });
    }

    private void signIn() {
        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage("Assigning Markets");
            }
        }, 800);
        Call<SignInResponse> call=apiInterface.userSignIn(userName.getText().toString(),password.getText().toString());
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.body().getStatus()==200){
                    acessToken=response.body().getData().getUser().getAccessToken();
                    userId=response.body().getData().getUser().getId();
                    latitude=response.body().getData().getUser().getLatitude();
                    longitude=response.body().getData().getUser().getLongitude();
                    editor.putString("access_token",response.body().getData().getUser().getAccessToken());
                    editor.putInt("user_id",response.body().getData().getUser().getId());
                    editor.putFloat("user_lat",response.body().getData().getUser().getLatitude());
                    editor.putFloat("user_lon",response.body().getData().getUser().getLongitude());
                    editor.commit();
                    final Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    i.putExtra("access_token",acessToken);
                    i.putExtra("user_id",response.body().getData().getUser().getId());
                    i.putExtra("user_lat",latitude);
                    i.putExtra("user_lon",longitude);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                            startActivity(i);
                            finish();
                        }
                    }, 600);

                    Log.i(tag,response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

            }
        });
    }
}
