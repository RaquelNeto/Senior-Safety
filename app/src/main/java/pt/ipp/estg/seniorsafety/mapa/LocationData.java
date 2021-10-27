package pt.ipp.estg.seniorsafety.mapa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationData {
    double latitude;
    double longitude;
    String hora;
    String data;
    Date d;
    SimpleDateFormat formataData;
    SimpleDateFormat formatahora;



    public LocationData(double latitude, double longitude ) {
        this.d=new Date();
        this.latitude = latitude;
        this.longitude = longitude;
        this.formataData = new SimpleDateFormat("dd_MM_yyyy");
        this.formatahora = new SimpleDateFormat("HH:mm:ss");
        this.hora=formatahora.format(d);
        this.data=formataData.format(d);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
