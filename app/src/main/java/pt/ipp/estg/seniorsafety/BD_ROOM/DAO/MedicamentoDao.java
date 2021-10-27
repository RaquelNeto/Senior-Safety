package pt.ipp.estg.seniorsafety.BD_ROOM.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


import pt.ipp.estg.seniorsafety.BD_ROOM.Medicamento;

@Dao
public interface MedicamentoDao {

    @Insert
    long[] insertMedicamentos(Medicamento... medicamentos);

    @Query("SELECT * FROM medicamentos")
    LiveData<List<Medicamento>> getMedicamentos();

    /*
    @Query("SELECT * FROM medicamentos WHERE name LIKE :name")
    List<Medicamento> getMedicamentoWithCustomName(String title);

    getMedicamentoWithCustomName("ben*")
    */

    @Delete
    int delete(Medicamento... medicamentos);

    @Update
    int update(Medicamento... medicamentos);

}
