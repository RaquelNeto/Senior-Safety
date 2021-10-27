package pt.ipp.estg.seniorsafety.medicamentos;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipp.estg.seniorsafety.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaginaInicial_Medicamentos extends AppCompatActivity {

    TextView textViewPIM;
    Button buttonPIM;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial__medicamentos);

        textViewPIM = (TextView) findViewById(R.id.textViewPIM);
        buttonPIM = (Button) findViewById(R.id.buttonPIM);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaginaInicial_Medicamentos.this, PlanoTomas.class));
            }
        });

        buttonPIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaginaInicial_Medicamentos.this, FragmentListaMedicamentos.class));
            }
        });




    }
}
