public class HviteResepter extends Resept {

    //konstruktør
    public HviteResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    //matode som returnerer farge på resept
    public String farge(){
        return "hvit";
    }

    //metode som returnerer prisen på legemiddel
    public int prisAaBetale(){
        return legemiddel.hentPris(); 
    }

    //overskriver to-string-metoden fra resept, og leghger itl særegen info
    @Override
    public String toString(){
        return (super.toString() + "\nFarge: "+farge());
    }

}
