package pt.ipp.estg.seniorsafety.jogoMatematica;

import androidx.appcompat.app.AppCompatActivity;
import pt.ipp.estg.seniorsafety.R;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.DistributionOrBuilder;

public class JogoMActivity extends AppCompatActivity {

    Button bResposta0, bResposta1, bResposta2,  bResposta3, bComeçar;
    TextView tvTempo, tvQuestao, tvScore, tvNumeroQuestões;
    ProgressBar pbTempo;

    Jogo jogo = new Jogo();
    int segundosRestantes;

    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            segundosRestantes--;
            tvTempo.setText(Integer.toString(segundosRestantes) + "seg");
            pbTempo.setProgress(60 - segundosRestantes); //tempo
        }

        @Override
        public void onFinish() {
            bResposta0.setEnabled(false);
            bResposta1.setEnabled(false);
            bResposta2.setEnabled(false);
            bResposta3.setEnabled(false);

            tvNumeroQuestões.setText("Tempo acabou!" + jogo.getNumeroCorreto() + "/" + (jogo.getTotalQuestoes() - 1));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bComeçar.setVisibility(View.VISIBLE);
                }
            }, 4000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_m);

        bComeçar = (Button) findViewById(R.id.buttonComeçar);
        bResposta0 = (Button) findViewById(R.id.buttonResposta0);
        bResposta1 = (Button) findViewById(R.id.buttonResposta1);
        bResposta2 = (Button) findViewById(R.id.buttonResposta2);
        bResposta3 = (Button) findViewById(R.id.buttonResposta3);

        tvTempo = (TextView) findViewById(R.id.textViewTempo);
        tvNumeroQuestões = (TextView) findViewById(R.id.textViewNumeroQuestões);
        tvQuestao = (TextView) findViewById(R.id.textViewQuestao);
        tvScore = (TextView) findViewById(R.id.textViewScore);

        pbTempo = (ProgressBar) findViewById(R.id.progressBarTempo);

        tvTempo.setText("0seg");
        tvQuestao.setText("");
        tvNumeroQuestões.setText("Numero Questões");
        tvScore.setText("0%");
        pbTempo.setProgress(0);

        View.OnClickListener buttonComeçarClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonComeçar = (Button) view;
                buttonComeçar.setVisibility(View.INVISIBLE);
                segundosRestantes = 60;
                jogo = new Jogo();
                rondaSeguinte();
                timer.start();

            }
        };

        bComeçar.setOnClickListener(buttonComeçarClickListener);


        View.OnClickListener buttonRespostaClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked = (Button) view;

                int respostaSelecionada = Integer.parseInt(buttonClicked.getText().toString());

                jogo.verificaResposta(respostaSelecionada);
                tvScore.setText(Integer.toString(jogo.getScore()));
                rondaSeguinte();

                tvNumeroQuestões.setText(jogo.getNumeroCorreto() + "/" + (jogo.getTotalQuestoes()-1));


            }
        };

        bResposta0.setOnClickListener(buttonRespostaClickListener);
        bResposta1.setOnClickListener(buttonRespostaClickListener);
        bResposta2.setOnClickListener(buttonRespostaClickListener);
        bResposta3.setOnClickListener(buttonRespostaClickListener);
    }


    private  void rondaSeguinte(){
        jogo.fazerNovaQuestao();
        int[] resposta = jogo.getCurrentQuestoes().getRespostasArray();

        bResposta0.setText(Integer.toString(resposta[0]));
        bResposta1.setText(Integer.toString(resposta[1]));
        bResposta2.setText(Integer.toString(resposta[2]));
        bResposta3.setText(Integer.toString(resposta[3]));

        bResposta0.setEnabled(true);
        bResposta1.setEnabled(true);
        bResposta2.setEnabled(true);
        bResposta3.setEnabled(true);

        tvQuestao.setText(jogo.getCurrentQuestoes().getQuestaoFrase());

    }
}
