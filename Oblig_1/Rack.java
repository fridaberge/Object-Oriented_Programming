//klassen Rack
public class Rack {

    //instansvariabler
    private Node[] noder;
    private int teller = 0;
    private final int maksAntNoder = 12;

    //konstruktør
    public Rack(){
        noder = new Node[maksAntNoder];
    }

    //metode for å legge til node i rack
    public void leggTilNode(Node node){
        noder[teller]=node;
        teller ++;
    }

    //metode for å finne antall noder i rack / lengden på node-lista
    public int getAntNoder(){
        return teller;
    }

    //metode som returenerer antall prosessorer i hver av nodene i racket
    public int antProsessorer(){
        int totalAntPros = 0;
        for (Node node: noder){
            if (node != null){
                totalAntPros += node.antProsessorer();
            }
        }
        return totalAntPros;
    }

    //metode som returnerer antall noder med nok (påkrevd) minne
    public int noderMedNokMinne(int paakrevdMinne){
        int antNoderMedNokMinne = 0;
        for (Node node: noder){
            if (node != null){
                if (node.nokMinne(paakrevdMinne) == true){
                    antNoderMedNokMinne += 1;
                }
            } 
        }
        return antNoderMedNokMinne;
    }
}


