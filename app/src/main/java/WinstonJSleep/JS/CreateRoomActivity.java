package WinstonJSleep.JS;

import static WinstonJSleep.JS.MainActivity.aboutMePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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

/**
 * The aboutMe class extends the AppCompatActivity class and is used to display the user's profile information
 * and allow the user to register as a renter.
 */

public class CreateRoomActivity extends AppCompatActivity {

    /**
     * The API service used to make requests to the API.
     */

    Context mContext;


    BaseApiService mApiService = UtilsApi.getApiService();
    CheckBox AC, Wifi, Refrigerator, Bathtub, Balcony, Restaurant, Swimming, Fitness;
    Button roomCreation, roomCancelation;
    ArrayList<Facility> facility = new ArrayList<>();
    Spinner spinCity;
    Spinner spinBedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        spinCity = findViewById(R.id.spinnerCity);
        spinBedType = findViewById(R.id.spinnerBedType);
        spinCity.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_dropdown_item, City.values()));
        spinBedType.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_dropdown_item, BedType.values()));
        BedType bedData = BedType.valueOf(spinBedType.getSelectedItem().toString());
        City cityData = City.valueOf(spinCity.getSelectedItem().toString());
        roomCreation = findViewById(R.id.roomCreate);
        roomCancelation = findViewById(R.id.cancelCreate);
        roomCreation.setOnClickListener(x ->{
            requestRoom(bedData, cityData);
            finish();
        });

    }

    protected Room requestRoom (BedType bedType, City city){
        EditText nameRoom = findViewById(R.id.RoomName);
        EditText priceRoom = findViewById(R.id.RoomPrice);
        EditText sizeRoom = findViewById(R.id.RoomSize);
        EditText addressName = findViewById(R.id.RoomAddress);

        int price = Integer.parseInt(priceRoom.getText().toString());
        int size = Integer.parseInt(sizeRoom.getText().toString());

        String address = addressName.getText().toString();
        String name = nameRoom.getText().toString();
        AC = findViewById(R.id.CheckBoxAC);
        Wifi = findViewById(R.id.checkBoxWifi);
        Refrigerator = findViewById(R.id.checkBoxRefrigator);
        Bathtub = findViewById(R.id.checkBoxBathtub);
        Balcony = findViewById(R.id.checkBoxBalcony);
        Restaurant = findViewById(R.id.checkBoxRestaurant);
        Swimming = findViewById(R.id.checkBoxPool);
        Fitness = findViewById(R.id.checkBoxFitness);

        if(AC.isChecked()){
            facility.add(Facility.AC);
        }
        if(Wifi.isChecked()){
            facility.add(Facility.WiFi);
        }
        if(Refrigerator.isChecked()){
            facility.add(Facility.Refrigerator);
        }
        if(Bathtub.isChecked()){
            facility.add(Facility.Bathtub);
        }
        if(Balcony.isChecked()){
            facility.add(Facility.Balcony);
        }
        if(Restaurant.isChecked()){
            facility.add(Facility.Restaurant);
        }
        if(Swimming.isChecked()){
            facility.add(Facility.SwimmingPool);
        }
        if(Fitness.isChecked()){
            facility.add(Facility.FitnessCenter);
        }


        mApiService.createRooms (aboutMePage.id, name, size, price, facility, city, bedType, address).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Create Room Success!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "Create Room Failed!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }

}