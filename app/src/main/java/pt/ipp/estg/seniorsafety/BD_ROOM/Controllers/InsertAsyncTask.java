package pt.ipp.estg.seniorsafety.BD_ROOM.Controllers;

import android.os.AsyncTask;
import android.util.Log;

import pt.ipp.estg.seniorsafety.BD_ROOM.DAO.MedicamentoDao;
import pt.ipp.estg.seniorsafety.BD_ROOM.Medicamento;

public class InsertAsyncTask extends AsyncTask<Medicamento, Void, Void> {

    private static final String TAG = "InsertAsyncTask";

    private MedicamentoDao mMedicamentoDao;

    public InsertAsyncTask(MedicamentoDao dao){
        mMedicamentoDao = dao;
    }

    @Override
    protected Void doInBackground(Medicamento... medicamentos) {
        Log.d(TAG, "doInBackground: thread: "+ Thread.currentThread().getName());
        mMedicamentoDao.insertMedicamentos(medicamentos);
        return null;
    }
}
