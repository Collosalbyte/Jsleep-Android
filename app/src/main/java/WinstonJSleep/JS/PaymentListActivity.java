package WinstonJSleep.JS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import WinstonJSleep.JS.model.Payment;
import WinstonJSleep.JS.model.Room;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentListActivity extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService = UtilsApi.getApiService();
    ListView payListView;
    public static ArrayList<Payment> paying = new ArrayList<Payment>();
    public static int Index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mApiService = UtilsApi.getApiService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);
        mContext = this;
        payListView = (ListView) findViewById(R.id.listViewPayment);

        getAllPayment();
        payListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Index = i;
                startActivity(new Intent(PaymentListActivity.this, PaymentFlowActivity.class));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    protected ArrayList<Payment> getAllPayment (){

        mApiService.getAllPayment().enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                paying.clear();
                paying.addAll(response.body());
                ArrayList <String> payList = new ArrayList<>();
                for (Payment p: paying
                     ) {
                    payList.add(String.valueOf(p.getRoomId()));

                }
                ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(
                        mContext,
                        android.R.layout.simple_list_item_1,
                        payList
                );
                payListView.setAdapter(itemAdapter);

            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Toast.makeText(mContext, "Failed to load the room", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}