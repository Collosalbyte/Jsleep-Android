package WinstonJSleep.JS;

import static WinstonJSleep.JS.MainActivity.aboutMePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import WinstonJSleep.JS.model.Account;
import WinstonJSleep.JS.model.Renter;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class aboutMe extends AppCompatActivity {
    BaseApiService mApiService;
    EditText regName, regAddress, regPhone;
    Context mContext;
    CardView aboutMeReg, aboutMeDet;
    Button regRen, regBut, regCan, regTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        TextView name = findViewById(R.id.aboutMeName);
        TextView email = findViewById(R.id.aboutMeEmail);
        TextView balance = findViewById(R.id.Balance);

        name.setText(aboutMePage.name);
        email.setText(aboutMePage.email);
        balance.setText(String.valueOf(aboutMePage.balance));

        aboutMeReg = (CardView) findViewById(R.id.AboutMeRegister);
        aboutMeDet = (CardView) findViewById(R.id.AboutMeDetails);

        regRen = (Button) findViewById(R.id.RegisterRenter);
        regCan = (Button) findViewById(R.id.RegisterCancel);
        regBut = (Button) findViewById(R.id.RegisterButton);
        regTop = (Button) findViewById(R.id.topUpButton);

        regName = findViewById(R.id.RegisterName);
        regAddress = findViewById(R.id.RegisterAddress);
        regPhone = findViewById(R.id.RegisterPhoneNumber);

        if (aboutMePage.renter == null) {
            aboutMeReg.setVisibility(View.INVISIBLE);
            aboutMeDet.setVisibility(View.INVISIBLE);
            regBut.setVisibility(View.VISIBLE);
        }

        if (aboutMePage.renter != null) {
            regRen.setVisibility(View.INVISIBLE);
            aboutMeReg.setVisibility(View.INVISIBLE);
            aboutMeDet.setVisibility(View.VISIBLE);
            regBut.setVisibility(View.INVISIBLE);

            TextView regName = findViewById(R.id.AboutMeRName);
            TextView regAddress = findViewById(R.id.AboutMeRAddress);
            TextView regPhone = findViewById(R.id.AboutMeRPhoneNumber);

            regName.setText(aboutMePage.renter.username);
            regAddress.setText(aboutMePage.renter.address);
            regPhone.setText(aboutMePage.renter.phoneNumber);
        }

        regRen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMeReg.setVisibility(View.VISIBLE);
                aboutMeDet.setVisibility(View.INVISIBLE);
                regBut.setVisibility(View.VISIBLE);
                regRen.setVisibility(View.INVISIBLE);
            }
        });
        regCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMeReg.setVisibility(View.INVISIBLE);
                aboutMeDet.setVisibility(View.INVISIBLE);
                regBut.setVisibility(View.VISIBLE);
            }
        });

        regTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText moneyAmount = findViewById(R.id.EditTextAmount);
                topUp(Double.parseDouble(moneyAmount.getText().toString()));
            }
        });

    }
    protected Account requestRenter() {

        mApiService.registerMe(aboutMePage.id, regName.getText().toString(),
                regAddress.getText().toString(),
                regPhone.getText().toString()).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Renter Registered!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                Toast.makeText(mContext, "Renter Register Failed!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
    protected Boolean topUp (double Balance) {

        mApiService.topUp(aboutMePage.id, Balance).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Top Up Successful!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext, "Top Up Failed!", Toast.LENGTH_LONG).show();
            }
        });
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_baseline_add_box_24:
                startActivity(new Intent(this, CreateRoomActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.registerenu, menu);
        return true;
    }
}
