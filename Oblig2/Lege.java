public class Lege {
    public String navn;

    //konstruktør
    public Lege(String navn){
        this.navn = navn;
    }

    //metode som retunerer navn på lege
    public String skrivNavn(){
        return navn;
    }

    //metode som printer info om lege
    public String toString(){
        return "\nLegeinfo: " +skrivNavn()+"\n";
    }
}
