package pt.ipp.estg.seniorsafety.jogoMatematica;

import androidx.appcompat.app.AppCompatActivity;
import pt.ipp.estg.seniorsafety.MenuLapsosMemoria;
import pt.ipp.estg.seniorsafety.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuJogoMatematica extends AppCompatActivity{

    Button bJogarMatematica;
    Button bSairMatematica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jogo_matematica);

        bJogarMatematica = (Button) this.findViewById(R.id.buttonJogarMatematica);
        bSairMatematica = (Button) this.findViewById(R.id.buttonSairMatematica);


        //para jogar
        bJogarMatematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuJogoMatematica.this, JogoMActivity.class);
                intent.putExtra("tipo", "Jogar");
                startActivity(intent);
            }
        });

        //para regressar ao menu
        bSairMatematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuJogoMatematica.this, MenuLapsosMemoria.class);
                finish();
            }
        });
    }

}