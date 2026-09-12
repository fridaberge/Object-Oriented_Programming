public class Subsekvens {
    public final String subsek;
    public int antall = 0;

    public Subsekvens(String s){
        subsek = s; 
    }

    public int hentAntall(){
        return antall;
    } 

    public void oek(int tall){
        antall += tall;
    }

    public void mink(){
        antall--;
    }

    public String toString(){
        return ("(" +subsek+ ","+antall+")"); //eks. (ABC,4)
    }
}
