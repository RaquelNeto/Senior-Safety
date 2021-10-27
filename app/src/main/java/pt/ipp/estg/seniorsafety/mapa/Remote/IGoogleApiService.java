package pt.ipp.estg.seniorsafety.mapa.Remote;


import pt.ipp.estg.seniorsafety.mapa.Models.MyPlaces;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IGoogleApiService {
    @GET
    Call<MyPlaces> getNearByPlaces(@Url String url);


}
