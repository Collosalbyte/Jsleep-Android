package WinstonJSleep.JS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import WinstonJSleep.JS.model.BedType;
import WinstonJSleep.JS.model.City;
import WinstonJSleep.JS.model.Facility;
import WinstonJSleep.JS.model.Room;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoomActivity extends AppCompatActivity {

    Context mContext;
    BaseApiService mApiService = UtilsApi.getApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
    }

    protected Room requestRoom (String name, ArrayList<Facility> facility, City city, BedType bedType, String address){
        mApiService.createRoom (LoginActivity.accounts.id, name, 0, 1000, facility, city, bedType, address).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    Room room = response.body();
                    Toast.makeText(mContext, "Create Room Success!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Toast.makeText(mContext, "Create Room Failed!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }

}