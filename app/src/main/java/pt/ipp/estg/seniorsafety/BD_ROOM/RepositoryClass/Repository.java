package pt.ipp.estg.seniorsafety.BD_ROOM.RepositoryClass;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;
import pt.ipp.estg.seniorsafety.BD_ROOM.Controllers.DeleteAsyncTask;
import pt.ipp.estg.seniorsafety.BD_ROOM.Controllers.InsertAsyncTask;
import pt.ipp.estg.seniorsafety.BD_ROOM.Controllers.RememberDatabase;
import pt.ipp.estg.seniorsafety.BD_ROOM.Controllers.UpdateAsyncTask;
import pt.ipp.estg.seniorsafety.BD_ROOM.Medicamento;


public class Repository {

    private RememberDatabase mRememberDatabase;

    public Repository(Context context) {
        mRememberDatabase = RememberDatabase.getInstance(context);
    }

    public void insertMedicamento(Medicamento medicamento){
        new InsertAsyncTask(mRememberDatabase.getMedicamentoDao()).execute(medicamento);
    }

    public void updateMedicamento(Medicamento medicamento){
        new UpdateAsyncTask(mRememberDatabase.getMedicamentoDao()).execute(medicamento);
    }

    public LiveData<List<Medicamento>> retrieveMedicamentoTask() {

        return  mRememberDatabase.getMedicamentoDao().getMedicamentos();
    }

    public void deleteMedicamento(Medicamento medicamento){
        new DeleteAsyncTask(mRememberDatabase.getMedicamentoDao()).execute(medicamento);
    }



}
