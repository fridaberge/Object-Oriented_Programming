public class Person {
    private String navn;
    private Bil3 bilnummer;

    //konstruktør
    public Person(String navn, Bil3 bil){
        this.navn = navn;
        this.bilnummer = bil;
    }

    //metode som skriver ut bilen til personen
    public void skrivBilTilPerson(){
        System.out.println(this.bilnummer);
    }
}
