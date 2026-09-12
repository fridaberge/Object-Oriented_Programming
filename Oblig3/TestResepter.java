public class TestResepter {
    public static void main(String[] args) {
        //lager objekter av lege
        Lege minFastlege = new Lege("Frida");
        //lager objekt av Vanlig (legemiddel)
        Legemiddel minMedisin = new Vanlig("Ibux", 120, 1.3);
        Legemiddel minMedisin2 = new Vanlig("Paracet", 50, 2.3);
        //lager objekter av resepter
        Presept presept1 = new Presept(minMedisin, minFastlege, 1, 10);
        MilResept milresept1 = new MilResept(minMedisin2, minFastlege, 2);
        BlaResepter blaresept = new BlaResepter(minMedisin, minFastlege, 2, 1);

        // System.out.println("resept1: \n" +presept1);
        // System.out.println("resept2: \n" +milresept1);
        // System.out.println("resept3: \n" +blaresept);

        //tester
        System.out.println("\n\nTester P-resept\nLegemiddel: " +testLegemiddel(presept1, minMedisin)+"\nLege: "+testLege(presept1, minFastlege)+ "\nPasient: " +testPasientID(presept1, 1)+"\nReit: " +testReit(presept1, 10) + "\nFarge: " +testFarge(presept1, "hvit")+ "\nPris: " +testPris(presept1, 120)+"\n\n");
        System.out.println("\n\nTester Millitarresept\nLegemiddel: " +testLegemiddel(milresept1, minMedisin2)+"\nLege: "+testLege(milresept1, minFastlege)+ "\nPasient: " +testPasientID(milresept1, 2)+"\nReit: " +testReit(milresept1, 3) + "\nFarge: " +testFarge(milresept1, "hvit")+ "\nPris: " +testPris(milresept1, 50)+ "\n\n");
        System.out.println("\n\nTester Blaa resept\nLegemiddel: " +testLegemiddel(blaresept, minMedisin)+"\nLege: "+testLege(blaresept, minFastlege)+ "\nPasient: " +testPasientID(blaresept, 2)+"\nReit: " +testReit(blaresept, 1) + "\nFarge: " +testFarge(blaresept, "blaa")+ "\n\n");

    }

    //tester Legemiddel
    public static boolean testLegemiddel(Resept resept, Legemiddel forventetLegemiddel){
        return resept.hentLegemiddel().equals(forventetLegemiddel);
    }

    //tester lege
    public static boolean testLege(Resept resept, Lege forventetLege){
        return resept.hentLege().equals(forventetLege);
    }

    //tester pasient id
    public static boolean testPasientID(Resept resept, int forventetPasientID){
        return resept.hentPasientId() == forventetPasientID;
    }

    //tester reit
    public static boolean testReit(Resept resept, int forventetReit){
        return resept.hentReit() == forventetReit;
    }

    //tester farge må caste for å finne særegne egenskaper
    public static boolean testFarge(Resept resept, String farge){
        if (resept instanceof Presept){
            Presept presept = (Presept)resept;
            return presept.farge() == farge;
        }
        if (resept instanceof MilResept){
            MilResept milResept = (MilResept)resept;
            return milResept.farge() == farge;
        }
        if (resept instanceof BlaResepter){
            BlaResepter blaResept = (BlaResepter)resept;
            return blaResept.farge() == farge;
        }
        return false;
    }

    public static boolean testPris(Resept resept, int pris){
        if (resept instanceof Presept){
            Presept presept = (Presept)resept;
            return presept.prisAaBetale() == pris;
        }
        if (resept instanceof MilResept){
            MilResept milResept = (MilResept)resept;
            return milResept.prisAaBetale() == pris;
        }
        if (resept instanceof BlaResepter){
            BlaResepter blaResept = (BlaResepter)resept;
            return blaResept.prisAaBetale() == pris;
        }
        return false;
    }
}
