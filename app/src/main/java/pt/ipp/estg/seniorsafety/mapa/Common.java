package pt.ipp.estg.seniorsafety.mapa;


import pt.ipp.estg.seniorsafety.mapa.Remote.IGoogleApiService;
import pt.ipp.estg.seniorsafety.mapa.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL = "https://maps.googleapis.com/";

    public static IGoogleApiService getGoogleApiService() {
        return RetrofitClient.getClient(BASE_URL).create(IGoogleApiService.class);
    }
}
