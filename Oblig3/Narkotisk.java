public class Narkotisk extends Legemiddel {
    protected int styrke;
    protected int id = 0;
    
    //konstruktør
    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
        this.id += 1;
    }

    @Override
    public int hentId(){
        return unikID;
    }

    public int hentStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        return "\nID: " + hentId()+"\nNavn: " +hentNavn()+"\nPris: "+hentPris()+"\nVikrestoff: "+hentVirkestoff()+"\nStyrke: "+hentStyrke()+"\n";
    }
    
}
