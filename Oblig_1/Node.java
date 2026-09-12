//klassen Node
public class Node {
    //insatansvariaber
    private int antProsessorer;
    private int minnestorrelse;

    //konstruktør
    public Node(int antPros, int minnestr){
        antProsessorer = antPros;
        minnestorrelse = minnestr;
    }

    //metode som returnerer antall prosessorer i noden
    public int antProsessorer(){
        return this.antProsessorer;
    }

    //metode osm finner ut om noder har nok (påkrevd) minne
    public boolean nokMinne(int paakrevdMinne){
        if (paakrevdMinne <= minnestorrelse){
            return true;
        }
        else{
            return false;
        }
    }
}
