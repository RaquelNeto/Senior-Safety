package pt.ipp.estg.seniorsafety.jogoForca;

import java.util.Random;

public class Palavras  {

    private String[] palavras = new String[]{"AVIÃO", "AERONAVE", "AUTOMÓVEL", "BICICLETA", "IATE", "NAVIO", "TERRA", "MERCÚRIO", "PLUTÃO",
            "MARTE", "JUPITER", "NEPTUNO", "ELEFANTE", "ESCORPIÃO", "RINOCERONTE", "DINOSSAURO", "PORTO", "ALGARVE", "DIFICIL", "TELEVISÃO", "CADEIRA",
            "SECADOR", "ESCORREGA", "BRASIL", "ÁFRICA", "EUROPA", "TURQUIA", "ESTADOS UNIDOS", "GRÉCIA", "ARGENTINA", "VENEZUELA", "BOTAFOGO", "SÃO PAULO",
            "FLAMENGO", "PALMEIRAS", "FLUMINENSE", "AMOR", "INTELECTUAL", "SÁBIO", "CULTURA", "SABEDORIA", "COIMBRA", "LAGARTO", "ZEBRA", "CRUZEIRO",
            "COMPUTADOR", "FACULDADE", "PIPOCA", "MACARRÃO", "FEIJOADA", "DETERGENTE", "LAVANDARIA", "COZINHA", "CHURRASCO", "BIBERÃO", "AFRICANO",
            "BRASILEIRO", "AMERICANO", "CAFÉ", "MASSA", "TELEMÓVEL", "CARRO", "LIXEIRO", "PROGRAMADOR", "ARTISTA", "LUTADOR", "COZINHEIRO",
            "CARTEIRO", "VENDEDOR", "FLORISTA", "JAPÃO", "DUBAI", "EQUADOR", "MÉXICO", "PORTUGUAL", "ALEMANHA", "PROFESSOR", "CHAVES",
            "DOCUMENTOS", "DOCUMENTÁRIO", "FAMÍLIA", "FAMILIARES", "LAMBORGHINI", "FERRARI", "PORSCHE", "BANANA", "MACACO", "VOLKSWAGEN",
            "AMIGÁVEL", "ADORÁVEL", "AMÁVEL", "CORAGEM", "CUMPLICIDADE", "EQUILÍBRIO", "ESPERANÇA","HONESTIDADE", "GENTILEZA", "LIBERDADE",
            "MELANCOLIA", "METÁFORA", "BELENENSES", "FOGÃO", "PORTIMONENSE", "BOAVISTA"};


    public String sorteio(){
        String palavraSorteada = palavras[(int)(random()* palavras.length)];
        return palavraSorteada;
    }

    public static double random(){
        Random r = new Random();
        return  r.nextDouble();
    }
}
