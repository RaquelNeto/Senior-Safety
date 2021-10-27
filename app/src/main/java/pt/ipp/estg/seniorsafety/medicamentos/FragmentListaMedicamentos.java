package pt.ipp.estg.seniorsafety.medicamentos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.ipp.estg.seniorsafety.BD_ROOM.Medicamento;
import pt.ipp.estg.seniorsafety.BD_ROOM.MedicamentosRecyclerAdapter;
import pt.ipp.estg.seniorsafety.BD_ROOM.RepositoryClass.Repository;
import pt.ipp.estg.seniorsafety.BD_ROOM.VerticalSpacingItemDecorator;
import pt.ipp.estg.seniorsafety.R;

public class FragmentListaMedicamentos extends Fragment implements MedicamentosRecyclerAdapter.OnMedicamentoListener, View.OnClickListener {

    private static final String TAG = "FragmtListMedicamentos";

    //UI componentes
    private Context mContext;
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<Medicamento> mMedicamentos = new ArrayList<>();
    private MedicamentosRecyclerAdapter mMedicamentosRecyclerAdapter;
    private Repository mRepository;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_lista_medicamentos,container,false);



        int a = 0;
        mRecyclerView = view.findViewById(R.id.recyclerView);

        view.findViewById(R.id.fab).setOnClickListener(this);

        mRepository = new Repository(getContext());

        initRecyclerView();

        //insertFakeMeds();
        retrieveMedicamentos();

        Toolbar toolbar = view.findViewById(R.id.toolbar1);
        toolbar.setTitle("Medicamentos");

        return view;
    }

    //attach o obeserver ao livedata obj que retorna da db, sempre que ha alteração na db o observer chama o onChanged
    // e limpa, adiciona e informa a recycler view adapter que os dados foram alterados
    private void retrieveMedicamentos() {
        mRepository.retrieveMedicamentoTask().observe(this, new Observer<List<Medicamento>>() {
            @Override
            public void onChanged(@Nullable List<Medicamento> medicamentos) {
                if(mMedicamentos.size() > 0){
                    mMedicamentos.clear();
                }
                if(medicamentos != null){
                    mMedicamentos.addAll(medicamentos);
                }
                mMedicamentosRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }




    private void initRecyclerView(){
        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linerLayoutManager);
        //linhas caixa de texto
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(15);
        mRecyclerView.addItemDecoration(itemDecorator);
        //swipe to delete
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mMedicamentosRecyclerAdapter = new MedicamentosRecyclerAdapter(mMedicamentos,this);
        mRecyclerView.setAdapter(mMedicamentosRecyclerAdapter);

    }

    @Override
    public void onMedicamentoClick(int position) {
        Log.d(TAG, "onMedicamentoClick: Click no medicamento  "+position);
        Intent intent = new Intent(getContext(), MedicamentoActivity.class);
        intent.putExtra("selected_med", mMedicamentos.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), MedicamentoActivity.class);
        startActivity(intent);
    }

    private void deleteMedicamento(Medicamento medicamento) {
        mMedicamentos.remove(medicamento);
        mMedicamentosRecyclerAdapter.notifyDataSetChanged();

        mRepository.deleteMedicamento(medicamento);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteMedicamento(mMedicamentos.get(viewHolder.getAdapterPosition()));

        }
    };
}
