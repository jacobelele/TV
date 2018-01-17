package api;

import java.util.List;

import model.AdvImage;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nazam on 17/01/18.
 */

public interface GranDhikaService {
    @GET("GetAdvImage")
    Call<List<String>> listAdvImage();
}
