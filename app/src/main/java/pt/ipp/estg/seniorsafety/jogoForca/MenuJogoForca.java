package pt.ipp.estg.seniorsafety.jogoForca;

import androidx.appcompat.app.AppCompatActivity;
import pt.ipp.estg.seniorsafety.MenuLapsosMemoria;
import pt.ipp.estg.seniorsafety.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuJogoForca extends AppCompatActivity{

    Button bJogar;
    Button bSair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogoforca);

        bJogar = (Button) this.findViewById(R.id.buttonJogarMatematica);
        bSair = (Button) this.findViewById(R.id.buttonSairMatematica);


        //para jogar
        bJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuJogoForca.this, JogoFActivity.class);
                intent.putExtra("tipo", "Jogar");
                startActivity(intent);
            }
        });

        //para regressar ao menu
        bSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuJogoForca.this, MenuLapsosMemoria.class);
                finish();
            }
        });
    }

}




