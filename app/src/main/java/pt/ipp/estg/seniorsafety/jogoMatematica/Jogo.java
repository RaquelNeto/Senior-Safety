package pt.ipp.estg.seniorsafety.jogoMatematica;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private List<Questoes> questoes ;
    private  int numeroCorreto;
    private int numeroIncorreto;
    private int totalQuestoes;
    private int score;
    private  Questoes currentQuestoes;


    public Jogo(){
        numeroCorreto = 0;
        numeroIncorreto = 0;
        totalQuestoes = 0;
        score = 0;
        currentQuestoes = new Questoes(10);
        questoes =  new ArrayList<Questoes>();

    }

    //Inserir questões até o tempo acabar
    public void fazerNovaQuestao(){
        currentQuestoes = new Questoes(totalQuestoes * 2 + 5);
        totalQuestoes++;
        questoes.add(currentQuestoes);
    }


    public  boolean verificaResposta(int submeterResposta){
        boolean isCorreto;

        if (currentQuestoes.getResposta() == submeterResposta){
            numeroCorreto++;
            isCorreto = true;
        }else{
            numeroIncorreto++;
            isCorreto = false;
        }

        score = numeroCorreto * 20 - numeroIncorreto * 10; //se acertar o score aumenta 20 senão diminui 10
        return  isCorreto;
    }

    public List<Questoes> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questoes> questoes) {
        this.questoes = questoes;
    }
    public int getNumeroCorreto() {
        return numeroCorreto;
    }

    public void setNumeroCorreto(int numeroCorreto) {
        this.numeroCorreto = numeroCorreto;
    }


    public int getNumeroIncorreto() {
        return numeroIncorreto;
    }

    public int getTotalQuestoes() {
        return totalQuestoes;
    }

    public Questoes getCurrentQuestoes() {
        return currentQuestoes;
    }

    public void setCurrentQuestoes(Questoes currentQuestoes) {
        this.currentQuestoes = currentQuestoes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTotalQuestoes(int totalQuestoes) {
        this.totalQuestoes = totalQuestoes;
    }

    public void setNumeroIncorreto(int numeroIncorreto) {
        this.numeroIncorreto = numeroIncorreto;
    }

}
