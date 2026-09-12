public class Vanedannende extends Legemiddel{
    protected int styrke;
    protected int id = 0;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
        id += 1;
    }

    public int hentStyrke(){
        return styrke;
    }

    @Override
    public int hentId(){
        return unikID;
    }

    @Override
    public String toString(){
        return "\nID: " + hentId()+"\nNavn: " +hentNavn()+"\nPris: "+hentPris()+"\nVikrestoff: "+hentVirkestoff()+"\nStyrke: "+hentStyrke()+"\n";
    }
    
}
