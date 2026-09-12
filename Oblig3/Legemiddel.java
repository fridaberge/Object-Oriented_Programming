public abstract class Legemiddel {
    protected String navn;
    protected int pris;
    protected double virkestoff;
    private static int id;
    protected int unikID;

    //konstruktør
    public Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        id ++;
        unikID = id;
    }

    //metode som returnerer den unike id-en
    public int hentId(){
        return unikID;
    }

    //metode som returnerer navn
    public String hentNavn(){
        return navn;
    }

    //metode som returnerer pris
    public int hentPris(){
        return pris;
    }

    //metode som returnerer virkestoffet
    public double hentVirkestoff(){
        return virkestoff;
    }

    //metode som returnerer en ny pris
    public void settNyPris(int nyPris){
        pris = nyPris;
    }

    //abstarakt metode som overskrives i subklasse
    abstract public String toString();
    
}
