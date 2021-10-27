package pt.ipp.estg.seniorsafety;

import androidx.appcompat.app.AppCompatActivity;
import pt.ipp.estg.seniorsafety.jogoForca.MenuJogoForca;
import pt.ipp.estg.seniorsafety.jogoMatematica.MenuJogoMatematica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuLapsosMemoria extends AppCompatActivity{

    TextView mTextViewMenuPrincipal;
    Button mButtonJogoForca,mButtonJogoMatematica, mButtonRegressarMenu;
    ImageView mImageViewLapsos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lapsos_memoria);

        mButtonJogoForca = (Button) findViewById(R.id.buttonJogoForca);
        mButtonJogoMatematica = (Button) findViewById(R.id.buttonJogoMatematica);
        mButtonRegressarMenu = (Button) findViewById(R.id.buttonRegressarMenu);
        mTextViewMenuPrincipal = (TextView) findViewById(R.id.textViewMenuPrincipal);
        mImageViewLapsos = (ImageView) findViewById(R.id.imageViewLapsos);

        //para abrir o jogo da Forca
        mButtonJogoForca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLapsosMemoria.this, MenuJogoForca.class);
                startActivity(intent);
            }
        });


          //para abrir o jogo da Matemática
        mButtonJogoMatematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLapsosMemoria.this, MenuJogoMatematica.class);
                startActivity(intent);
            }
        });

        //para regressar ao menu
        mButtonRegressarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLapsosMemoria.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }


}
