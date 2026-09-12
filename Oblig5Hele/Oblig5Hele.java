import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Hele {
    public static void main(String[] args) {

        //forste del av oppgaven med test av høyeste frekvens i testdataliten og testdata
        // Monitor2 syk1 = new Monitor2();
        // Monitor2 ikkeSyk1 = new Monitor2();
        //lesMedtrader("testdataliten", syk1, ikkeSyk1);
        // System.out.println("\n\nTestdataliten:");
        // flettMedTrader(syk1);
        // flettMedTrader(ikkeSyk1);
        // sammenLiknFrekevns(syk1, ikkeSyk1);

        // Monitor2 syk2 = new Monitor2();
        // Monitor2 ikkeSyk2 = new Monitor2();
        //lesMedtrader("testdata", syk2, ikkeSyk2);
        // System.out.println("\n\nTestdata:");
        // flettMedTrader(syk2);
        // flettMedTrader(ikkeSyk2);
        // sammenLiknFrekevns(syk2, ikkeSyk2);
        

        Monitor2 syk3 = new Monitor2();
        Monitor2 ikkeSyk3 = new Monitor2();

        try{
            String mappenavn = args[0];
            int antTrader = Integer.parseInt(args[1]);

            System.out.println(mappenavn+":");
            lesMedtrader(mappenavn, syk3, ikkeSyk3);
            flettMedTrader(syk3, antTrader);
            flettMedTrader(ikkeSyk3, antTrader);
            printDeStorste(syk3, ikkeSyk3);
        }
        catch(ArrayIndexOutOfBoundsException a){
            System.out.println("feil input, Oppgi mappenavn, sa antall trader");
        }

    }

    //metode for å flette med tråder
    public static void flettMedTrader(Monitor2 monitor, int antT){
        ArrayList <Thread> Ftrader = new ArrayList<>();
        for (int a = 0; a< antT; a++){
            Thread t = new Thread(new FletteTrad(monitor,a));
            Ftrader.add(t);
            t.start();
        }
        try {
            for(Thread t: Ftrader){
                t.join();
            }
        } 
        catch (InterruptedException i) {
            System.err.println(i);
        }        
    }

    //metode for å lese filer med tråder
    public static void lesMedtrader(String mappenavn, Monitor2 syk, Monitor2 ikkeSyk){
        try {
            ArrayList<String> filer = finnFilnavn(mappenavn);
            ArrayList<String> sykdom = finnSykdom(mappenavn);
            ArrayList <Thread> trader = new ArrayList<>();
            int antSykFlett = 0;
            int antIkkeSykFlett = 0;

            for (int f = 0; f< filer.size(); f++){
                if(sykdom.get(f).equalsIgnoreCase("True")){
                    Thread t = new Thread(new LeseTrad(mappenavn+"/"+filer.get(f), syk));
                    trader.add(t);
                    t.start();
                    antSykFlett ++;
                }
                else if(sykdom.get(f).equalsIgnoreCase("False")){
                    Thread t = new Thread(new LeseTrad(mappenavn+"/"+filer.get(f), ikkeSyk));
                    trader.add(t);
                    t.start();
                    antIkkeSykFlett ++;
                }
                
            }
            syk.settFerdigFlett(antSykFlett-1); //sier at flettingen skal foregå antall ganger det er linjer med true i filen minus én
            ikkeSyk.settFerdigFlett(antIkkeSykFlett-1); //sier at flettingen skal foregå antall ganger det er linjer med false i filen minus én

            try {
                for(Thread t: trader){
                    t.join();
                }
            } 
            catch (InterruptedException i) {
                System.err.println("feil bro: \n"+i);
                System.exit(1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    //lager metode for å returnere om sykdom
    public static ArrayList<String> finnSykdom(String filbaneMappe) throws FileNotFoundException{ //leser av metadata for å lage Subsekvenser
        File metadataFil = new File(filbaneMappe+"/metadata.csv");
        Scanner sc = new Scanner(metadataFil);

        ArrayList<String> listeSykdom = new ArrayList<String>(); 

        while(sc.hasNextLine()) {
            String linje = sc.nextLine();
            String stripLinje[] = linje.split(",");
            listeSykdom.add(stripLinje[1]);
        }
        sc.close();
        return listeSykdom;
    }

    //lager metode for å returnere filnavn
    public static ArrayList<String> finnFilnavn(String filbaneMappe) throws FileNotFoundException{ //leser av metadata for å lage Subsekvenser
        File metadataFil = new File(filbaneMappe+"/metadata.csv");
        Scanner sc = new Scanner(metadataFil);

        ArrayList<String> listeFiler = new ArrayList<String>(); 

        while(sc.hasNextLine()) {
            String linje = sc.nextLine();
            String stripLinje[] = linje.split(",");
            listeFiler.add(stripLinje[0]);
        }
        sc.close();
        return listeFiler;
    }

    public static void sjekkFrekvens(HashMap<String, Subsekvens> hash){
        //sjekker hvilken subsekves det er mest av (tar utgangspunkt i at kun én har flest) i en hashmap (en mappe)
        int frekvens = 0;
        String hoyestFrekvens = "";
        for(String s: hash.keySet()){
            if(hash.get(s).hentAntall() > frekvens){
                frekvens = hash.get(s).hentAntall();
                hoyestFrekvens = s;
            }
        }
        System.out.println(hash.get(hoyestFrekvens)); //høyeste frekevesen
    }

    //metode som skal sjekke alle frekvensene i en hashmap etter at subsekvensene i mappen er flettet
    public static ArrayList<Integer> listeFrekvenser(Monitor2 monitor){
        ArrayList<Integer> listeFrekevns = new ArrayList<>();

        for(HashMap<String, Subsekvens> hash: monitor.subreg.sekvenser){
            for(String s : hash.keySet()){
                listeFrekevns.add(hash.get(s).hentAntall());
            }
        }
        return listeFrekevns;
    }


    //kan etterpå bytte ut listen med hash.get(s).hentAntall()
    public static void sammenLiknFrekevns(Monitor2 syk, Monitor2 ikkesyk){
        int differanse = 0; //må finne den største differansen (der syk har flest forekomster)
        Subsekvens farligsteSub = new Subsekvens("");

        for(String s: syk.hentUt(0).keySet()){ //går gjennom hashmappet som er igjen i monitoren etter fletting
            if(ikkesyk.hentUt(0).containsKey(s)){ //hvis både syke og ikke-syke har samme subsekvens
                //sammenlikn
                if((syk.hentUt(0).get(s).hentAntall() - ikkesyk.hentUt(0).get(s).hentAntall() > 0) && (syk.hentUt(0).get(s).hentAntall() - ikkesyk.hentUt(0).get(s).hentAntall() > differanse)){
                    differanse = (syk.hentUt(0).get(s).hentAntall() - ikkesyk.hentUt(0).get(s).hentAntall());
                    farligsteSub = syk.hentUt(0).get(s);
                }
            }
            else{
                if(!ikkesyk.hentUt(0).containsKey(s) && syk.hentUt(0).get(s).hentAntall() > differanse){ //hvis subsekvensen kun finnes i syk-mapet
                differanse = syk.hentUt(0).get(s).hentAntall();
                farligsteSub = syk.hentUt(0).get(s);
                }
            }
        }

        System.out.println("den farligste genkombinasjonen er: "+farligsteSub+" med differanse pa: "+differanse);

    }

    //kan etterpå bytte ut listen med hash.get(s).hentAntall()
    public static void printDeStorste(Monitor2 syk, Monitor2 ikkesyk){
        int minDifferanse = 7; //må subsekvenser med differanse på 7 eller mer
        ArrayList<Subsekvens> farligsteSuber = new ArrayList<Subsekvens>();

        for(String s: syk.hentUt(0).keySet()){ //går gjennom hashmappet som er igjen i monitoren etter fletting
            if(ikkesyk.hentUt(0).containsKey(s)){ //hvis både syke og ikke-syke har samme subsekvens
                //sammenlikn
                if(syk.hentUt(0).get(s).hentAntall() - ikkesyk.hentUt(0).get(s).hentAntall() >= minDifferanse){
                    // farligsteSuber.add(syk.hentUt(0).get(s));
                    Subsekvens a = new Subsekvens(s);
                    a.antall = syk.hentUt(0).get(s).hentAntall() - ikkesyk.hentUt(0).get(s).hentAntall();
                    farligsteSuber.add(a);
                }
            }
            else if (syk.hentUt(0).get(s).hentAntall() >= minDifferanse){ //hvis subsekvensen kun finnes i syk-mapet
                farligsteSuber.add(syk.hentUt(0).get(s));
            }
        }

        System.out.println("de farligste genkombinasjonene er: "+farligsteSuber);

    }
}
