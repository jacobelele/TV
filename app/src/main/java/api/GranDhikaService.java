package api;

import java.util.List;

import model.AdvImage;
import model.Food;
import model.Promo;
import model.Room;
import model.Setting;
import model.TvChannel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GranDhikaService {
    @GET("GetAdvImage")
    Call<List<String>> listAdvImage();

    @GET("GetFood")
    Call<List<Food>> listFood(@Query("typeId") Integer typeId);

    @GET("GetPromotion")
    Call<List<Promo>> listPromo(@Query("id") Integer id);

    @GET("GetClient")
    Call<Room> room(@Query("mac") String mac);

    @GET("GetSettings")
    Call<Setting> setting();

    @GET("GetLiveTV")
    Call<List<TvChannel>> tvChannel(@Query("mac") String mac, @Query("packageId") Integer packageId);
}
