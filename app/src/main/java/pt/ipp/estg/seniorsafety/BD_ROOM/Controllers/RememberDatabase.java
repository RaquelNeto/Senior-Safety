package pt.ipp.estg.seniorsafety.BD_ROOM.Controllers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import pt.ipp.estg.seniorsafety.BD_ROOM.DAO.MedicamentoDao;
import pt.ipp.estg.seniorsafety.BD_ROOM.Medicamento;


@Database(entities = {Medicamento.class}, version =1)
public abstract class RememberDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "remember_db";

    private static RememberDatabase instance;

    public static RememberDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    RememberDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract MedicamentoDao getMedicamentoDao();


}


