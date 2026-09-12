public class MilResept extends HviteResepter {

    //konstruktør
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
        super(legemiddel, utskrivendeLege, pasientId, 3); //alle millitærrespeter skal ha reit = 3
    }

    //metode som returnerer prisen
    public int prisAaBetale(Legemiddel legemiddel){
        return 0;
    }

    //metode som overskriver to-string-metoden fra superklassen resept og legger til særegen info
    @Override
    public String toString(){
        return (super.toString() + "\nUnderkategori: Millitarresept\n\n");
    }
}
