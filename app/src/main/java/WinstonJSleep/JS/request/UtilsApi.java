package WinstonJSleep.JS.request;

/**
 * UtilsApi class is used to provide a convenient way to access the BaseApiService.
 */

public class UtilsApi {
    /**
     * The base URL for the API.
     */

    public static final String BASE_URL_API = "http://10.0.2.2:8080/";

    /**
     * Returns an instance of the BaseApiService. This method uses the RetrofitClient to create a new
     * instance of the Retrofit object with the base URL specified by BASE_URL_API, and then uses the
     * Retrofit object to create a new instance of the BaseApiService.
     */

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
