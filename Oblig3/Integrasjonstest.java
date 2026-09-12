//tester hvordan klassene fungerer sammen
public class Integrasjonstest {
    public static void main(String[] args) {

        //lager lege-objekter
        Lege minLege = new Lege("Eirik");
        Spesialist minSpesialist = new Spesialist("Frida", "3F47");

        //lager legemiddel-objekter
        Vanlig vanligMedisin = new Vanlig("Paracet", 120, 2.1);
        Vanedannende vaneMedisin = new Vanedannende("Nesespray", 60, 3.1, 5);
        Narkotisk narkotiskMedisin = new Narkotisk("Oksygen", 15, 2.1, 2);

        //lager resept-objekter
        MilResept milResept = new MilResept(vanligMedisin, minLege, 1);
        Presept presept = new Presept(vaneMedisin, minSpesialist, 2, 5);
        BlaResepter blaResept = new BlaResepter(narkotiskMedisin, minLege, 3, 2);
        
        //printer og ser om informasjonen stemmer med det jeg forventer
        System.out.println("Millitaarresept: "+ milResept); //id 1
        System.out.println("P-resept: "+ presept); //id 2
        System.out.println("Blaa resept: "+ blaResept); //id 3
        

    }
}

