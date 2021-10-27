package pt.ipp.estg.seniorsafety.BD_ROOM;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pt.ipp.estg.seniorsafety.R;

public class MedicamentosRecyclerAdapter extends RecyclerView.Adapter<MedicamentosRecyclerAdapter.ViewHolder>{

    private static final String TAG = "MedicamentosRecyclerAda";


    private ArrayList<Medicamento> mMedicamentos = new ArrayList<>();
    private OnMedicamentoListener mOnMedicamentoListener;

    public MedicamentosRecyclerAdapter(ArrayList<Medicamento> medicamentos, OnMedicamentoListener onMedicamentoListener ) {

        this.mMedicamentos = medicamentos;
        this.mOnMedicamentoListener = onMedicamentoListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_medicamento_layout,viewGroup,false);
        return new ViewHolder(view, mOnMedicamentoListener);//return new viewHolder object from that view
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        try {
            String day = mMedicamentos.get(i).getDate().substring(0,2);

            String month = mMedicamentos.get(i).getDate().substring(3,5);
            month = Utility.getMonthFromNumber(month);

            String year = mMedicamentos.get(i).getDate().substring(6);
            String date = day + " " +month + " " + year;

            viewHolder.date.setText(date);
            viewHolder.name.setText(mMedicamentos.get(i).getName());

        }catch(NullPointerException e){
            Log.e(TAG, "onBindViewHolder: NullPointerException"+ e.getMessage() );
        }


    }

    @Override
    public int getItemCount() {
        return mMedicamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView name, date;
        OnMedicamentoListener onMedicamentoListener;

        public ViewHolder(@NonNull View itemView , OnMedicamentoListener onMedicamentoListener) {
            super(itemView);
            name = itemView.findViewById(R.id.med_title);
            date = itemView.findViewById(R.id.med_date);
            this.onMedicamentoListener = onMedicamentoListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMedicamentoListener.onMedicamentoClick(getAdapterPosition());
        }

    }

    public interface OnMedicamentoListener{
        void onMedicamentoClick(int position);
    }

}
