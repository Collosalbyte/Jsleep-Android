package WinstonJSleep.JS.request;

import WinstonJSleep.JS.model.Account;
import WinstonJSleep.JS.model.Price;
import WinstonJSleep.JS.model.Renter;
import WinstonJSleep.JS.model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String password);

    @POST("account/register")
    Call<Account> register (@Query("name") String name,
                            @Query("email") String email,
                            @Query("password") String password);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    @GET("price/{id}")
    Call<Price> getPrice (@Path("id") int id);

    @GET("renter/{id}")
    Call<Renter> getRenter (@Path("id") int id);
}


