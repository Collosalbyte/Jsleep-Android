package WinstonJSleep.JS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;

import WinstonJSleep.JS.model.Room;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> extractName(ArrayList<Room> list) {
        Gson gson = new Gson();
        ArrayList<String> ret = null;
        int i;
        for (i = 0; i < list.size(); i++) {
            ret.add(list.get(i).name);
        }
        return ret;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listviewmain);
        //List<String> nameStr = new ArrayList<String>();
        Gson gson = new Gson();
        try {

            InputStream filepath = getAssets().open("randomRoomList.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
            ArrayList<Room> roomList = new ArrayList<Room>();
            Room[] acc = gson.fromJson(reader, Room[].class);
            Collections.addAll(roomList, acc);
            //nameStr = extractName(roomList);
            itemList itemAdapter = new itemList(this, roomList);
            listView.setAdapter(itemAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_button:
                return super.onOptionsItemSelected(item);
            case R.id.ic_baseline_person_24:
                startActivity(new Intent(this, aboutMe.class));
                return true;
            case R.id.ic_baseline_add_box_24:
                startActivity(new Intent(this, RegisterActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}