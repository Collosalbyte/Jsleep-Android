package WinstonJSleep.JS.request;

import java.util.ArrayList;
import java.util.List;

import WinstonJSleep.JS.model.Account;
import WinstonJSleep.JS.model.BedType;
import WinstonJSleep.JS.model.City;
import WinstonJSleep.JS.model.Facility;
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

    @POST("renter/register")
    Call<Renter> registerMe (@Query("username") String username,
                           @Query("address") String address,
                           @Query("phoneNumber") String phoneNumber);

    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom (@Query("page") int page,
                                @Query("pageSize") int pageSize);

    @POST("room/create")
    Call<Room> createRoom (@Query("accountId") int accountId,
                      @Query("name") String name,
                      @Query("size") int size,
                      @Query("price") int price,
                      @Query("facility") ArrayList<Facility> facility,
                      @Query("city") City city,
                      @Query("bedType") BedType bedType,
                      @Query("address") String address);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    @GET("price/{id}")
    Call<Price> getPrice (@Path("id") int id);

    @GET("renter/{id}")
    Call<Renter> getRenter (@Path("id") int id);
}


