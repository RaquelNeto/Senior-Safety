package pt.ipp.estg.seniorsafety.jogoMatematica;

import java.util.Calendar;
import java.util.Random;
import static java.util.Calendar.*;

public class Questoes {

    private int primeiroNumero;
    private int segundoNumero;
    private int resposta;

    //existem 4 respostas possiveis para o utilizador escolher
    private int[] respostasArray;

    //qual das 4 posições está correta 0,1,2,3
    private int posicaoRespostas;

    //o máximo valor para o primeiro ou segundo numero
    private int limiteSuperior;

    //O output do problema, exemplo "4+5= "
    private String questaoFrase;

    //Calendário para ir bucar os dias da semana
    Calendar calendar = getInstance();
    int dayOfWeek = calendar.get(DAY_OF_WEEK);


    //gerar uma nova questão random
    public Questoes(int limiteSuperior){
        this.limiteSuperior = limiteSuperior;
        Random randomCriadorNumero = new Random();

        if (MONDAY == dayOfWeek){
            primeiroNumero = randomCriadorNumero.nextInt(limiteSuperior);
            segundoNumero = randomCriadorNumero.nextInt(limiteSuperior);

            resposta = primeiroNumero + segundoNumero;
            questaoFrase = primeiroNumero + " + " + segundoNumero + "=" ;

            posicaoRespostas = randomCriadorNumero.nextInt(4);
            respostasArray = new  int[] {0,1,2,3};

            respostasArray[0] = resposta + 1;
            respostasArray[1] = resposta + 10;
            respostasArray[2] = resposta - 5;
            respostasArray[3] = resposta - 2;

            respostasArray = shuffleArray(respostasArray); //shuffle serve para embaralhar o array

            respostasArray[posicaoRespostas] = resposta;

        }else if(TUESDAY == dayOfWeek){
            primeiroNumero = randomCriadorNumero.nextInt(limiteSuperior);
            segundoNumero = randomCriadorNumero.nextInt(limiteSuperior);

            resposta = primeiroNumero - segundoNumero;
            questaoFrase = primeiroNumero + " - " + segundoNumero + "=" ;

            posicaoRespostas = randomCriadorNumero.nextInt(4);
            respostasArray = new  int[] {0,1,2,3};

            respostasArray[0] = resposta + 1;
            respostasArray[1] = resposta + 10;
            respostasArray[2] = resposta - 5;
            respostasArray[3] = resposta - 2;

            respostasArray = shuffleArray(respostasArray); //shuffle serve para embaralhar o array

            respostasArray[posicaoRespostas] = resposta;

        }else if(WEDNESDAY == dayOfWeek){
            primeiroNumero = randomCriadorNumero.nextInt(limiteSuperior);
            segundoNumero = randomCriadorNumero.nextInt(limiteSuperior);
            resposta = primeiroNumero * segundoNumero;
            questaoFrase = primeiroNumero + " * " + segundoNumero + "=" ;

            posicaoRespostas = randomCriadorNumero.nextInt(4);
            respostasArray = new  int[] {0,1,2,3};

            respostasArray[0] = resposta + 1;
            respostasArray[1] = resposta + 10;
            respostasArray[2] = resposta - 5;
            respostasArray[3] = resposta - 2;

            respostasArray = shuffleArray(respostasArray); //shuffle serve para embaralhar o array

            respostasArray[posicaoRespostas] = resposta;

        }else if(THURSDAY == dayOfWeek){
            primeiroNumero = randomCriadorNumero.nextInt(limiteSuperior);
            segundoNumero = randomCriadorNumero.nextInt(limiteSuperior);
            resposta = primeiroNumero + segundoNumero * segundoNumero;
            questaoFrase = primeiroNumero + " + " + segundoNumero + " * " + segundoNumero  + "=" ;

            posicaoRespostas = randomCriadorNumero.nextInt(4);
            respostasArray = new  int[] {0,1,2,3};

            respostasArray[0] = resposta + 1;
            respostasArray[1] = resposta + 10;
            respostasArray[2] = resposta - 5;
            respostasArray[3] = resposta - 2;

            respostasArray = shuffleArray(respostasArray); //shuffle serve para embaralhar o array

            respostasArray[posicaoRespostas] = resposta;

        }else if(FRIDAY == dayOfWeek) {
            primeiroNumero = randomCriadorNumero.nextInt(limiteSuperior);
            segundoNumero = randomCriadorNumero.nextInt(limiteSuperior);

            resposta = primeiroNumero + (segundoNumero - primeiroNumero);
            questaoFrase = primeiroNumero + " + " + "(" + segundoNumero + "-" + primeiroNumero +")" + "=";

            posicaoRespostas = randomCriadorNumero.nextInt(4);
            respostasArray = new int[]{0, 1, 2, 3};

            respostasArray[0] = resposta + 1;
            respostasArray[1] = resposta + 10;
            respostasArray[2] = resposta - 5;
            respostasArray[3] = resposta - 2;

            respostasArray = shuffleArray(respostasArray); //shuffle serve para embaralhar o array

            respostasArray[posicaoRespostas] = resposta;

        }else if(SATURDAY == dayOfWeek) {
            primeiroNumero = randomCriadorNumero.nextInt(limiteSuperior);
            segundoNumero = randomCriadorNumero.nextInt(limiteSuperior);
            resposta = primeiroNumero - (segundoNumero - primeiroNumero);
            questaoFrase = primeiroNumero + " - "  + segundoNumero + "-" + primeiroNumero  + "=";

            posicaoRespostas = randomCriadorNumero.nextInt(4); //vai de 0 a 3
            respostasArray = new int[]{0, 1, 2, 3};

            respostasArray[0] = resposta + 1;
            respostasArray[1] = resposta + 10;
            respostasArray[2] = resposta - 5;
            respostasArray[3] = resposta - 2;

            respostasArray = shuffleArray(respostasArray); //shuffle serve para embaralhar o array

            respostasArray[posicaoRespostas] = resposta;

        }else if(SUNDAY == dayOfWeek) {
            primeiroNumero = randomCriadorNumero.nextInt(limiteSuperior);
            segundoNumero = randomCriadorNumero.nextInt(limiteSuperior);
            resposta = primeiroNumero * (segundoNumero - primeiroNumero);
            questaoFrase = primeiroNumero + " * " + "(" +segundoNumero + "-" + primeiroNumero + ")" + "=";

            posicaoRespostas = randomCriadorNumero.nextInt(4);
            respostasArray = new int[]{0, 1, 2, 3};
            respostasArray[0] = resposta + 1;
            respostasArray[1] = resposta + 10;
            respostasArray[2] = resposta - 5;
            respostasArray[3] = resposta - 2;

            respostasArray = shuffleArray(respostasArray); //shuffle serve para embaralhar o array

            respostasArray[posicaoRespostas] = resposta;
        }
    }

    private  int[] shuffleArray(int[] array){
        int index, temp;
        Random randomGerarNumero = new Random();

        for (int i= array.length - 1; i > 0 ;i--){
            index = randomGerarNumero.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int getPrimeiroNumero() {
        return primeiroNumero;
    }

    public void setPrimeiroNumero(int primeiroNumero) {
        this.primeiroNumero = primeiroNumero;
    }

    public int getSegundoNumero() {
        return segundoNumero;
    }

    public void setSegundoNumero(int segundoNumero) {
        this.segundoNumero = segundoNumero;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public int[] getRespostasArray() {
        return respostasArray;
    }

    public void setRespostasArray(int[] respostasArray) {
        this.respostasArray = respostasArray;
    }

    public int getPosicaoRespostas() {
        return posicaoRespostas;
    }

    public void setPosicaoRespostas(int posicaoRespostas) {
        this.posicaoRespostas = posicaoRespostas;
    }

    public int getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(int limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public String getQuestaoFrase() {
        return questaoFrase;
    }

    public void setQuestaoFrase(String questaoFrase) {
        this.questaoFrase = questaoFrase;
    }

}
