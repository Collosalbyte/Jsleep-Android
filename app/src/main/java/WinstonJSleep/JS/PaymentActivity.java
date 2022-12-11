package WinstonJSleep.JS;

import static WinstonJSleep.JS.LoginActivity.accounts;
import static WinstonJSleep.JS.MainActivity.aboutMePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import WinstonJSleep.JS.model.Room;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText editText, editTexts;
    Date startDateValue, endDateValue;
    TextView dateAmount, totalPayment, onePrice;
    Button paid, notPaid, butBack;
    CardView payAcceptedPage;
    Context mContext;
    BaseApiService mApiService = UtilsApi.getApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        dateAmount = findViewById(R.id.dayAmount);
        onePrice = findViewById(R.id.priceDay);
        onePrice.setText(Double.toString(DetailActivity.currentRoom.price.price));
        totalPayment = findViewById(R.id.finalTotal);
        paid = findViewById(R.id.acceptPayment);
        notPaid = findViewById(R.id.cancelPayment);
        payAcceptedPage = findViewById(R.id.paymentSuccess);
        butBack = findViewById(R.id.backToHome);
        mContext = this;

        DatePickerDialog.OnDateSetListener startingDate =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                startDateValue = myCalendar.getTime();
                updateLabel();
            }
        };

        editText = findViewById(R.id.startDatePick);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PaymentActivity.this,startingDate,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener endingDate =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                endDateValue = myCalendar.getTime();
                updateLabels();
                long diff = endDateValue.getTime() - startDateValue.getTime();
                dateAmount.setText(Long.toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
                totalPayment.setText(Double.toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)*DetailActivity.currentRoom.price.price));

            }
        };

        editTexts = findViewById(R.id.endDatePick);
        editTexts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PaymentActivity.this,endingDate,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        payAcceptedPage.setVisibility(View.INVISIBLE);
        paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay(aboutMePage.id);
                payAcceptedPage.setVisibility(View.VISIBLE);
            }
        });

        notPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(move);
            }
        });

        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(move);
            }
        });

    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void updateLabels(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editTexts.setText(dateFormat.format(myCalendar.getTime()));
    }

    protected ArrayList<Boolean> pay (int id){

        mApiService.accept(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(mContext, "Payment Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext, "Payment Failed, please top up", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}