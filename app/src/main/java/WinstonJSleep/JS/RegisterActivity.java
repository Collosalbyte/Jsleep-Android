package WinstonJSleep.JS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import WinstonJSleep.JS.model.Account;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    BaseApiService mApiService;
    EditText username, email, password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        username = findViewById(R.id.NameReg);
        email = findViewById(R.id.EmailReg);
        password = findViewById(R.id.PasswordReg);
        Button regButton = findViewById(R.id.Registerbutton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = requestRegister();
            }
        });
    }

    protected Account requestRegister(){
        mApiService.register(username.getText().toString(), email.getText().toString(), password.getText().toString()).enqueue((new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Toast.makeText(mContext,"Registered" + response.body().name + "Please login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "Account was already used or wrong email", Toast.LENGTH_SHORT).show();
            }
        }));
        return null;
    }
}