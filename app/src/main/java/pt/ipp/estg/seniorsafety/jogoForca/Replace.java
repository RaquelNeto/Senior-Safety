package pt.ipp.estg.seniorsafety.jogoForca;


public class Replace {


    public static String replaceAll(String source, String pattern, String replacement) {
        if (source == null) {
            return "";
        } else {
            StringBuffer stringBuffer = new StringBuffer();

            int idx = 0;

            String palavra = source;

            while ((idx = palavra.indexOf(pattern, idx)) != -1) {

                //stringBuffer.append atualiza o valor do objeto que invocou o método
                stringBuffer.append(palavra.substring(0, idx));
                stringBuffer.append(replacement);
                stringBuffer.append(palavra.substring(idx + pattern.length()));

                palavra = stringBuffer.toString();
                stringBuffer.delete(0, stringBuffer.length());

                idx += replacement.length();
            }

            return palavra;
        }
    }
}

