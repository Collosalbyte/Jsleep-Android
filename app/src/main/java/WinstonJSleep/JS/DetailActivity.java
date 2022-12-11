package WinstonJSleep.JS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import WinstonJSleep.JS.model.Facility;
import WinstonJSleep.JS.model.Room;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;

public class DetailActivity extends AppCompatActivity {
    BaseApiService mApiService;
    TextView nameFill, bedTypeFill, sizeFill, priceFill, addressFill;
    CheckBox AC, Wifi, Refrigerator, Bathtub, Balcony, Restaurant, Swimming, Fitness;
    public static Room currentRoom = MainActivity.rooms.get(MainActivity.currentPosition);
    Button payBut;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView roomActualName = findViewById(R.id.actualName);
        TextView roomActualBedType = findViewById(R.id.actualBedType);
        TextView roomActualSize = findViewById(R.id.actualSize);
        TextView roomActualPrice = findViewById(R.id.actualPrice);
        TextView roomActualAddress = findViewById(R.id.actualAddress);
        AC = findViewById(R.id.checkAC);
        Refrigerator = findViewById(R.id.checkRefrigerator);
        Wifi = findViewById(R.id.checkWiFi);
        Bathtub = findViewById(R.id.checkBathtub);
        Balcony = findViewById(R.id.checkBalcony);
        Restaurant = findViewById(R.id.checkRestaurant);
        Swimming = findViewById(R.id.checkSwimming);
        Fitness = findViewById(R.id.checkFitness);
        payBut = findViewById(R.id.paymentButton);

        roomActualName.setText(currentRoom.name);
        roomActualBedType.setText(currentRoom.bedtype.toString());
        roomActualSize.setText(Integer.toString(currentRoom.size));
        roomActualPrice.setText(Double.toString(currentRoom.price.price));
        roomActualAddress.setText(currentRoom.address);
        checkBoxTick();

        payBut.setOnClickListener(view -> {
            Intent move = new Intent(DetailActivity.this, PaymentActivity.class);
            startActivity(move);
        });
    }

    void checkBoxTick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            currentRoom.facility.forEach(r -> {
                if(r.equals(Facility.AC)){
                    AC.setChecked(true);
                }
                if(r.equals(Facility.WiFi)){
                    Wifi.setChecked(true);
                }
                if(r.equals(Facility.Bathtub)){
                    Bathtub.setChecked(true);
                }
                if(r.equals(Facility.Refrigerator)){
                    Refrigerator.setChecked(true);
                }
                if(r.equals(Facility.Bathtub)){
                    Bathtub.setChecked(true);
                }
                if(r.equals(Facility.Balcony)){
                    Balcony.setChecked(true);
                }
                if(r.equals(Facility.Restaurant)){
                    Restaurant.setChecked(true);
                }
                if(r.equals(Facility.SwimmingPool)){
                    Swimming.setChecked(true);
                }
                if(r.equals(Facility.FitnessCenter)){
                    Fitness.setChecked(true);
                }
            });
        }
    }

}