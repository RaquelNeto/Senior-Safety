package pt.ipp.estg.seniorsafety.jogoForca;

import androidx.appcompat.app.AppCompatActivity;
import pt.ipp.estg.seniorsafety.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class JogoFActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextViewPalavra;
    ImageView forca;
    Button bA, bB, bC, bD, bE, bF, bG, bH, bI, bJ, bK, bL, bM,
            bN, bO, bP, bQ, bR, bS, bT, bU, bV, bW, bX, bY, bZ;

    Palavras palavras = new Palavras();
    Palavras sugestão = new Palavras();

    private String palavraSecreta, tracos;
    private int nTentativas;
    private char letra;
    private double score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_f);

        iniciarObjetos();
        iniciarJogo();
    }


    public void iniciarJogo() {
        palavraSecreta = this.palavras.sorteio();
        procedimentosInicio();
    }


    private void procedimentosInicio() {
        nTentativas = 6;
        tracos = "";
        letra = ' ';

        for (int x = 0; x < palavraSecreta.length(); x++) {
            if (palavraSecreta.charAt(x) == '-') { //charAt retorna o caractere no índice especificado em uma string.
                tracos += " - ";

            } else if (palavraSecreta.charAt(x) == ' ') {
                tracos += "   ";

            } else {
                tracos += " _ ";
            }
        }

        habilitarBotoes();
        mTextViewPalavra.setText(this.tracos);
        forca.setImageResource(R.drawable.forca_6);
    }


    private void verificarClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLetraA:
                bA.setEnabled(false);
                letra = 'A';
                break;
            case R.id.buttonLetraB:
                bB.setEnabled(false);
                letra = 'B';
                break;
            case R.id.buttonLetraC:
                bC.setEnabled(false);
                letra = 'C';
                break;
            case R.id.buttonLetraD:
                bD.setEnabled(false);
                letra = 'D';
                break;
            case R.id.buttonLetraE:
                bE.setEnabled(false);
                letra = 'E';
                break;
            case R.id.buttonLetraF:
                bF.setEnabled(false);
                letra = 'F';
                break;
            case R.id.buttonLetraG:
                bG.setEnabled(false);
                letra = 'G';
                break;
            case R.id.buttonLetraH:
                bH.setEnabled(false);
                letra = 'H';
                break;
            case R.id.buttonLetraI:
                bI.setEnabled(false);
                letra = 'I';
                break;
            case R.id.buttonLetraJ:
                bJ.setEnabled(false);
                letra = 'J';
                break;
            case R.id.buttonLetraK:
                bK.setEnabled(false);
                letra = 'K';
                break;
            case R.id.buttonLetraL:
                bL.setEnabled(false);
                letra = 'L';
                break;
            case R.id.buttonLetraM:
                bM.setEnabled(false);
                letra = 'M';
                break;
            case R.id.buttonLetraN:
                bN.setEnabled(false);
                letra = 'N';
                break;
            case R.id.buttonLetraO:
                bO.setEnabled(false);
                letra = 'O';
                break;
            case R.id.buttonLetraP:
                bP.setEnabled(false);
                letra = 'P';
                break;
            case R.id.buttonLetraQ:
                bQ.setEnabled(false);
                letra = 'Q';
                break;
            case R.id.buttonLetraR:
                bR.setEnabled(false);
                letra = 'R';
                break;
            case R.id.buttonLetraS:
                bS.setEnabled(false);
                letra = 'S';
                break;
            case R.id.buttonLetraT:
                bT.setEnabled(false);
                letra = 'T';
                break;
            case R.id.buttonLetraU:
                bU.setEnabled(false);
                letra = 'U';
                break;
            case R.id.buttonLetraV:
                bV.setEnabled(false);
                letra = 'V';
                break;
            case R.id.buttonLetraW:
                bW.setEnabled(false);
                letra = 'W';
                break;
            case R.id.buttonLetraX:
                bX.setEnabled(false);
                letra = 'X';
                break;
            case R.id.buttonLetraY:
                bY.setEnabled(false);
                letra = 'Y';
                break;
            case R.id.buttonLetraZ:
                bZ.setEnabled(false);
                letra = 'Z';
        }

    }


    private boolean verificarAcerto() {
        boolean acerto = false;

        for (int x = 0; x < palavraSecreta.length(); x++) {
            char tmp = ' ';
            char vogal = palavraSecreta.charAt(x);

            if (vogal == 'Á'){
                tmp = 'A';
            }
            if (vogal == 'Ã'){
                tmp = 'A';
            }
            if (vogal == 'À'){
                tmp = 'A';
            }
            if (vogal == 'É'){
                tmp = 'E';
            }
            if (vogal == 'Í'){
                tmp = 'I';
            }
            if (vogal == 'Ó'){
                tmp = 'O';
            }
            if (vogal == 'Ô'){
                tmp = 'O';
            }
            if (vogal == 'Õ'){
                tmp = 'O';
            }
            if (vogal == 'Ú'){
                tmp = 'U';
            }
            if (vogal == 'Ç'){
                tmp = 'C';
            }
            if (vogal == letra || tmp == letra) {
                tracos = tracos.substring(0, 3 * x + 1) + vogal + tracos.substring(3 * x + 2);// se a vogal está dentro
                acerto = true;
            }
        }
        mTextViewPalavra.setText(tracos);

        return acerto;
    }


    @Override
    public void onClick(View view) {
        verificarClick(view);

        boolean acerto = verificarAcerto();

        if (!acerto) {
            nTentativas--;
            switch (nTentativas) {
                case 5:
                    forca.setImageResource(R.drawable.forca_5);
                    break;
                case 4:
                    forca.setImageResource(R.drawable.forca_4);
                    break;
                case 3:
                    forca.setImageResource(R.drawable.forca_3);
                    break;
                case 2:
                    forca.setImageResource(R.drawable.forca_2);
                    break;
                case 1:
                    forca.setImageResource(R.drawable.forca_1);
                    break;
                case 0:
                    forca.setImageResource(R.drawable.forca_0);
                    desabilitarBotoes();
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                    dialogBuilder.setTitle("Perdeu!").setMessage("O seu score é: " + score + "\nA palavra secreta é : " + palavraSecreta + "\n \nDeseja jogar novamente? \n" ).setCancelable(false)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    iniciarJogo();
                                }
                            })
                            .setNegativeButton("Não", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    finish();
                                }
                            })
                            .create().show();
            }
        }

        // troca de fundo de jogo, apresenta um alertDialog (pequena janela para tomar uma decisão
        if (Replace.replaceAll(tracos, " ", "").equalsIgnoreCase(palavraSecreta)) {
            desabilitarBotoes();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this); //aparecer a janela
            //score da aplicação
            if(nTentativas>0 && nTentativas<=3){
                score+= 50.0;
            }else if(nTentativas>3 && nTentativas<=6){
                score+= 100.0;
            }

            dialogBuilder.setTitle("Parabéns!")
                    .setMessage("O seu score é: " + score + "\nDeseja jogar novamente?").setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            iniciarJogo();
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    })
                    .create().show();
        }
    }


    private void iniciarObjetos() {
        mTextViewPalavra = (TextView) this.findViewById(R.id.textViewPalavra);
        forca = (ImageView) findViewById(R.id.imageViewForca);

        bA = (Button) this.findViewById(R.id.buttonLetraA);
        bB = (Button) this.findViewById(R.id.buttonLetraB);
        bC = (Button) this.findViewById(R.id.buttonLetraC);
        bD = (Button) this.findViewById(R.id.buttonLetraD);
        bE = (Button) this.findViewById(R.id.buttonLetraE);
        bF = (Button) this.findViewById(R.id.buttonLetraF);
        bG = (Button) this.findViewById(R.id.buttonLetraG);
        bH = (Button) this.findViewById(R.id.buttonLetraH);
        bI = (Button) this.findViewById(R.id.buttonLetraI);
        bJ = (Button) this.findViewById(R.id.buttonLetraJ);
        bK = (Button) this.findViewById(R.id.buttonLetraK);
        bL = (Button) this.findViewById(R.id.buttonLetraL);
        bM = (Button) this.findViewById(R.id.buttonLetraM);
        bN = (Button) this.findViewById(R.id.buttonLetraN);
        bO = (Button) this.findViewById(R.id.buttonLetraO);
        bP = (Button) this.findViewById(R.id.buttonLetraP);
        bQ = (Button) this.findViewById(R.id.buttonLetraQ);
        bR = (Button) this.findViewById(R.id.buttonLetraR);
        bS = (Button) this.findViewById(R.id.buttonLetraS);
        bT = (Button) this.findViewById(R.id.buttonLetraT);
        bU = (Button) this.findViewById(R.id.buttonLetraU);
        bV = (Button) this.findViewById(R.id.buttonLetraV);
        bW = (Button) this.findViewById(R.id.buttonLetraW);
        bX = (Button) this.findViewById(R.id.buttonLetraX);
        bY = (Button) this.findViewById(R.id.buttonLetraY);
        bZ = (Button) this.findViewById(R.id.buttonLetraZ);

        //Para cada botão, usa o que está neste metodo ( public void onClick(View view) )
        bA.setOnClickListener(this);
        bB.setOnClickListener(this);
        bC.setOnClickListener(this);
        bD.setOnClickListener(this);
        bE.setOnClickListener(this);
        bF.setOnClickListener(this);
        bG.setOnClickListener(this);
        bH.setOnClickListener(this);
        bI.setOnClickListener(this);
        bJ.setOnClickListener(this);
        bK.setOnClickListener(this);
        bL.setOnClickListener(this);
        bM.setOnClickListener(this);
        bN.setOnClickListener(this);
        bO.setOnClickListener(this);
        bP.setOnClickListener(this);
        bQ.setOnClickListener(this);
        bR.setOnClickListener(this);
        bS.setOnClickListener(this);
        bT.setOnClickListener(this);
        bU.setOnClickListener(this);
        bV.setOnClickListener(this);
        bW.setOnClickListener(this);
        bX.setOnClickListener(this);
        bY.setOnClickListener(this);
        bZ.setOnClickListener(this);
    }


    private void habilitarBotoes() {
        bA.setEnabled(true);
        bB.setEnabled(true);
        bC.setEnabled(true);
        bD.setEnabled(true);
        bE.setEnabled(true);
        bF.setEnabled(true);
        bG.setEnabled(true);
        bH.setEnabled(true);
        bI.setEnabled(true);
        bJ.setEnabled(true);
        bK.setEnabled(true);
        bL.setEnabled(true);
        bM.setEnabled(true);
        bN.setEnabled(true);
        bO.setEnabled(true);
        bP.setEnabled(true);
        bQ.setEnabled(true);
        bR.setEnabled(true);
        bS.setEnabled(true);
        bT.setEnabled(true);
        bU.setEnabled(true);
        bV.setEnabled(true);
        bW.setEnabled(true);
        bX.setEnabled(true);
        bY.setEnabled(true);
        bZ.setEnabled(true);
    }


    private void desabilitarBotoes() {
        bA.setEnabled(false);
        bB.setEnabled(false);
        bC.setEnabled(false);
        bD.setEnabled(false);
        bE.setEnabled(false);
        bF.setEnabled(false);
        bG.setEnabled(false);
        bH.setEnabled(false);
        bI.setEnabled(false);
        bJ.setEnabled(false);
        bK.setEnabled(false);
        bL.setEnabled(false);
        bM.setEnabled(false);
        bN.setEnabled(false);
        bO.setEnabled(false);
        bP.setEnabled(false);
        bQ.setEnabled(false);
        bR.setEnabled(false);
        bS.setEnabled(false);
        bT.setEnabled(false);
        bU.setEnabled(false);
        bV.setEnabled(false);
        bW.setEnabled(false);
        bX.setEnabled(false);
        bY.setEnabled(false);
        bZ.setEnabled(false);
    }
}