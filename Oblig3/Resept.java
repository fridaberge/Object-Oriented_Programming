public abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;
    private static int id;
    protected int unikID;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        id++;
        unikID = id;
    }

    public int hentId(){
        return unikID; 
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return utskrivendeLege; 
    }

    public int hentPasientId(){
        return pasientId;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        reit --;
        if(reit < 0){
            return false;
        }
        else{
            return true;
        }
    }

    abstract public String farge();
    abstract public int prisAaBetale();
    
    public String toString(){
        return ("\n\nInfo: \n\nOm Legemiddel: " +hentLegemiddel()+ "\n\nOm Lege: "+hentLege()+  "\n\nOm Resept:\nID: " +hentId()+ "\nPasient: " +pasientId+ "\nReit: " +hentReit());
    } 
    


}
