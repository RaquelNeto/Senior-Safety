package pt.ipp.estg.seniorsafety.BD_ROOM;

import java.text.SimpleDateFormat;
import java.util.Date;

//classe para definir data de registo do medicamento
public class Utility {

    public static String getCurrentData(){

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-yyyy");
            String currentDateTime = dateFormat.format(new Date());

            return currentDateTime;
        }catch(Exception e){
            return null;
        }
    }

    public static String getMonthFromNumber(String monthNumber){
       switch (monthNumber){
           case "01":{
               return "Jan";
           }
           case "02":{
               return "Feb";
           }
           case "03":{
               return "Mar";
           }
           case "04":{
               return "Abr";
           }
           case "05":{
               return "Mai";
           }
           case "06":{
               return "Jun";
           }
           case "07":{
               return "Jul";
           }
           case "08":{
               return "Ago";
           }
           case "09":{
               return "Set";
           }
           case "10":{
               return "Out";
           }
           case "11":{
               return "Nov";
           }
           case "12":{
               return "Dez";
           }

           default:{
               return "Erro";
           }
       }
    }
}
