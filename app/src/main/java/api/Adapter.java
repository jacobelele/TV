package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nazam on 17/01/18.
 */

public class Adapter {
    public static String BASE_URL = "http://195.110.58.237:8080/";
    public static Retrofit instance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static GranDhikaService service(){return instance().create(GranDhikaService.class);}
}
