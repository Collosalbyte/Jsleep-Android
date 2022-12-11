package WinstonJSleep.JS.request;

import java.util.ArrayList;
import java.util.List;

import WinstonJSleep.JS.model.Account;
import WinstonJSleep.JS.model.BedType;
import WinstonJSleep.JS.model.City;
import WinstonJSleep.JS.model.Facility;
import WinstonJSleep.JS.model.Payment;
import WinstonJSleep.JS.model.Price;
import WinstonJSleep.JS.model.Renter;
import WinstonJSleep.JS.model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * An interface for a base API service.
 * This interface defines a set of RESTful API methods for accessing and modifying data on a remote server.
 */

public interface BaseApiService {
    /**
     * Gets an account by its ID.
     */
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    /**
     * Attempts to log in an account with the given email and password.
     */

    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String password);

    /**
     * Registers a new account with the given name, email, and password.
     */
    @POST("account/register")
    Call<Account> register (@Query("name") String name,
                            @Query("email") String email,
                            @Query("password") String password);
    /**
     * Registers a new renter for the specified account.
     */

    @POST("account/{id}/registerRenter")
    Call<Renter> registerMe (
                            @Path("id") int id,
                            @Query("username") String username,
                            @Query("address") String address,
                            @Query("phoneNumber") String phoneNumber);

    /**
     * Gets a paginated list of all rooms.
     */

    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom (@Query("page") int page,
                                @Query("pageSize") int pageSize);

    /**
     * Creates a new room with the given properties.
     */

    @POST("room/create")
    Call<Room> createRooms (@Query("accountId") int accountId,
                      @Query("name") String name,
                      @Query("size") int size,
                      @Query("price") int price,
                      @Query("facility") ArrayList<Facility> facility,
                      @Query("city") City city,
                      @Query("bedType") BedType bedType,
                      @Query("address") String address);

    /**
     * Gets a room by its ID.
     */

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    /**
     * Gets the price of a room by its ID.
     */

    @GET("price/{id}")
    Call<Price> getPrice (@Path("id") int id);

    /**
     * Gets a renter by its ID.
     */

    @GET("renter/{id}")
    Call<Renter> getRenter (@Path("id") int id);

    /**
     * Adds funds to an account's balance
     */

    @POST("account/{id}/topUp")
    Call<Boolean> topUp (
            @Path ("id") int id,
            @Query("balance") double balance);

    /**
     * Accepts a pending action for an account
     */

    @POST("account/{id}/accept")
    Call<Boolean> accept (
            @Path("id") int id
    );

    @POST("account/{id}/cancel")
    Call<Boolean> cancel (
            @Path("id") int id
    );

    @GET("payment/getAllPayment")
    Call<List<Payment>> getAllPayment(

    );

}


