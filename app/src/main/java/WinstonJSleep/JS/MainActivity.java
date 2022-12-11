package WinstonJSleep.JS;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;

import WinstonJSleep.JS.model.Account;
import WinstonJSleep.JS.model.Room;
import WinstonJSleep.JS.request.BaseApiService;
import WinstonJSleep.JS.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    BaseApiService mApiService = UtilsApi.getApiService();
    ListView listView;
    int page = 0;
    int pageSize = 3;
    public static int currentPosition;
    public static ArrayList<Room> rooms = new ArrayList<Room>();

    public static ArrayList<String> extractName(ArrayList<Room> list) {
        Gson gson = new Gson();
        ArrayList<String> ret = null;
        int i;
        for (i = 0; i < list.size(); i++) {
            ret.add(list.get(i).name);
        }
        return ret;
    }

    public static Account aboutMePage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        Button nextPage = findViewById(R.id.Next);
        Button prevPage = findViewById(R.id.Prev);
        Button goPage = findViewById(R.id.Go);
        EditText pageNum = findViewById(R.id.PageNum);
        getRoomList(pageSize, page);
        listView = (ListView) findViewById(R.id.listviewmain);

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageSize++;
                getRoomList(pageSize, page);
                pageNum.setText(Integer.toString(pageSize));
            }
        });

        prevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageSize > 0){
                    pageSize--;
                }
                getRoomList(pageSize, page);
                pageNum.setText(Integer.toString(pageSize));
            }
        });
        goPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageSize = Integer.parseInt(pageNum.getText().toString());
                getRoomList(pageSize, page);
            }
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            currentPosition = position;
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(intent);
        });
    }

    protected ArrayList<Room> getRoomList (int pageSize, int page){

        mApiService.getAllRoom(page, pageSize).enqueue(new Callback<List<Room>>() {
                @Override
                public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                    rooms.clear();
                    rooms.addAll(response.body());
                    listView = (ListView) findViewById(R.id.listviewmain);
                    itemList itemAdapter = new itemList(mContext, rooms);
                    listView.setAdapter(itemAdapter);

                }

                @Override
                public void onFailure(Call<List<Room>> call, Throwable t) {
                    Toast.makeText(mContext, "Failed to load the room", Toast.LENGTH_SHORT).show();
                }
            });
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitems, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_button:
                return super.onOptionsItemSelected(item);
            case R.id.ic_baseline_person_24:
                startActivity(new Intent(this, aboutMe.class));
                return true;
            case R.id.payList:
                startActivity(new Intent(MainActivity.this, PaymentListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Room getSelectedRoom () {
        return rooms.get(currentPosition);
    }
}