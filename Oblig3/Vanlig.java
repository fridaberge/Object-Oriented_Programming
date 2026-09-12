public class Vanlig extends Legemiddel{

    public Vanlig(String navn, int pris, double virkestoff){
        super(navn, pris, virkestoff);
    }

    @Override
    public int hentId(){
        return unikID;
    }

    @Override
    public String toString(){
        return "\nID: " + hentId()+"\nNavn: " +hentNavn()+"\nPris: "+hentPris()+"\nVikrestoff: "+hentVirkestoff()+"\n";
    }
}