package WinstonJSleep.JS.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitClient class is used to create and return an instance of the Retrofit object.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient (String baseUrl){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
