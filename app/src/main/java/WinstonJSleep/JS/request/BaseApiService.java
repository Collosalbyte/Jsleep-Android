package WinstonJSleep.JS.request;

import WinstonJSleep.JS.model.Account;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);
}
