public class Bil3 {
    //instansvariabler
    private String regnummer;

    //konstruktør
    public Bil3(String bilnummer){
        this.regnummer = bilnummer;
    }
    
    //metode som printer registreringsnummer
    public void skrivBil3(){
        System.out.println(this.regnummer);
    }
    
    //metode som henter registreringsnummeret
    public String hentNummer(){
        return this.regnummer;
    }
}
 