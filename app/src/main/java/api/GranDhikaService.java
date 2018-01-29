package api;

import java.util.List;

import model.AdvImage;
import model.Food;
import model.Promo;
import model.Room;
import model.RunningText;
import model.Setting;
import model.TvChannel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GranDhikaService {
    @GET("iptvportal/GetAdvImage")
    Call<List<String>> listAdvImage();

    @GET("iptvportal/GetFood")
    Call<List<Food>> listFood(@Query("typeId") Integer typeId);

    @GET("iptvportal/GetPromotion")
    Call<List<Promo>> listPromo(@Query("id") Integer id);

    @GET("iptvportal/GetClient")
    Call<Room> room(@Query("mac") String mac);

    @GET("iptvportal/GetSettings")
    Call<Setting> setting();

    @GET("iptvportal/GetLiveTV")
    Call<List<TvChannel>> tvChannel(@Query("mac") String mac, @Query("packageId") Integer packageId);

    @GET("iptvportal/GetSubtitles")
    Call<List<RunningText>> runningText(@Query("mac") String mac);
}
