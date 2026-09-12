public class BlaResepter extends Resept {

    //konstruktør
    public BlaResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    
    //overskriver farge-metode fra superklasse
    @Override
    public String farge(){
        return "blaa";
    }

    //overskriber pris-metoden fra superklasse
    @Override
    public int prisAaBetale(){
        int pris = Math.round(legemiddel.hentPris()*0.75f); //runder av prisen til nærmeste hele tall (int)
        return pris;
    }

    //overskriber to-string-metode fra superklasse
    @Override
    public String toString(){
        return (super.toString() + "\nFarge: "+farge());
    }
}
