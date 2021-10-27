package pt.ipp.estg.seniorsafety.medicamentos;

import androidx.appcompat.app.AppCompatActivity;
import pt.ipp.estg.seniorsafety.R;
import pt.ipp.estg.seniorsafety.medicamentos.alarme.MainActivityAlarm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class PlanoTomas extends AppCompatActivity {

    public static final String PLANO_ADDED = "new_plan";
    public static final String Quantidade_ADDED = "new_quantidade";
    public static final String Data_ADDED = "new_data";
    public static final String hora_ADDED = "new_hora";
    TextView textViewPlanoTomas, textViewData,textViewHorario;
    EditText editTextPlanoTomas, editTextNumero, editTextQuantidadeTomar;
    CalendarView calendarView;
    Button buttonRelogio, buttonGuardar, button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_tomas);

        textViewData = (TextView) findViewById(R.id.textViewData);
        textViewPlanoTomas = (TextView) findViewById(R.id.textViewPlanoTomas);
        textViewHorario = (TextView) findViewById(R.id.textViewHorario);

        editTextPlanoTomas = (EditText) findViewById(R.id.editTextPlanoTomas);
        editTextNumero = (EditText) findViewById(R.id. editTextNumero);
        editTextQuantidadeTomar = (EditText) findViewById(R.id.editTextQuantidadeTomar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        buttonRelogio = (Button) findViewById(R.id.buttonRelogio);
        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
        button=(Button) findViewById(R.id.button);



        buttonRelogio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlanoTomas.this, MainActivityAlarm.class));
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(editTextPlanoTomas.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String medicamentos = editTextPlanoTomas.getText().toString();
                    resultIntent.putExtra(PLANO_ADDED, medicamentos);
                    String Quantidade = editTextQuantidadeTomar.getText().toString();
                    resultIntent.putExtra(Quantidade_ADDED, Quantidade);
                    String Data = textViewData.getText().toString();
                    resultIntent.putExtra(Data_ADDED, Data);
                    String Hora = textViewHorario.getText().toString();
                    resultIntent.putExtra(hora_ADDED, Hora);
                    setResult(RESULT_OK, resultIntent);
                }

                finish();
            }
        });


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(PlanoTomas.this, MedicamentoActivity.class));
        }
    });


    }
}
