package WinstonJSleep.JS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import WinstonJSleep.JS.model.Account;
import WinstonJSleep.JS.model.Renter;
import WinstonJSleep.JS.model.Room;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentFlowActivity extends AppCompatActivity {
    TextView nameRoom = findViewById(R.id.roomName);
    TextView orderName = findViewById(R.id.ownerName);
    TextView nameRenter = findViewById(R.id.renterName);
    TextView priceActual = findViewById(R.id.actualPrices);
    TextView payStat = findViewById(R.id.paidStatus);
    Button accPay = findViewById(R.id.acceptRoomPayment);
    Button canPay = findViewById(R.id.cancelRoomPayment);
    Context mContext;
    BaseApiService mApiService = UtilsApi.getApiService();
    Room r;
    Account a;
    Renter rent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_flow);
        getRoom();
        getAccount();
        getRenter();

        accPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accPay.setVisibility(View.INVISIBLE);
                canPay.setVisibility(View.INVISIBLE);
                accept();
            }
        });

        canPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
                accPay.setVisibility(View.INVISIBLE);
                canPay.setVisibility(View.INVISIBLE);
            }
        });
    }

    protected void getRoom() {
        mApiService.getRoom(PaymentListActivity.paying.get(PaymentListActivity.Index).getRoomId()).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                r = response.body();
                nameRoom.setText(r.name);
                priceActual.setText(String.valueOf(r.price.price));
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {

            }
        });
    }

    protected void getAccount() {
        mApiService.getAccount(PaymentListActivity.paying.get(PaymentListActivity.Index).buyerId).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                a = response.body();
                orderName.setText(a.name);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    protected void getRenter() {
        mApiService.getRenter(PaymentListActivity.paying.get(PaymentListActivity.Index).renterId).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                rent = response.body();
                nameRenter.setText(rent.username);
                payStat.setText(String.valueOf(PaymentListActivity.paying.get(PaymentListActivity.Index).status));
            }

            @Override
            public void onFailure(Call<Renter> call, Throwable t) {

            }
        });
    }

    protected void accept() {
        mApiService.accept(PaymentListActivity.paying.get(PaymentListActivity.Index).id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    protected void cancel() {
        mApiService.cancel(PaymentListActivity.paying.get(PaymentListActivity.Index).id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

}