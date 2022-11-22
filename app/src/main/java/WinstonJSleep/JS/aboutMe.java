package WinstonJSleep.JS;

import static WinstonJSleep.JS.MainActivity.aboutMePage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class aboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
        TextView name = findViewById(R.id.aboutMeName);
        TextView email = findViewById(R.id.aboutMeEmail);
        TextView balance = findViewById(R.id.Balance);
        name.setText(aboutMePage.name);
        email.setText(aboutMePage.email);
        balance.setText(String.valueOf(aboutMePage.balance));
    }

}