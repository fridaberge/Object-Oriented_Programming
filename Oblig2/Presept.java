public class Presept extends HviteResepter {
    final int rabatt = 108;

    public Presept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public int prisAaBetale(Legemiddel legemiddel){
        int pris;
        if (legemiddel.hentPris() > 108){ //hvis kjøper er ung
            pris = legemiddel.hentPris() -rabatt;
            return pris;
        }
        return 0; 
    }

    @Override
    public String toString(){
        return (super.toString() + "\nUnderkategori: P-resept\n\n");
    }
}
