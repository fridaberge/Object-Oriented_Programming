import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
  
public class SubsekvensRegister {
    public ArrayList<HashMap<String, Subsekvens>> sekvenser = new ArrayList<HashMap<String, Subsekvens>>(); 

    public void settInn(HashMap<String, Subsekvens> sekvens){
        sekvenser.add(sekvens);
    }

    public HashMap<String, Subsekvens> hentUt(int indeks){
        return sekvenser.get(indeks); //tar ut første? skal hente ut en vilkårlig
    }

    public HashMap<String, Subsekvens> taUt(){
        return sekvenser.remove(0); //tar ut første? skal hente ut en vilkårlig
    }

    public int antall(){
        return sekvenser.size();
    }

    //utvider slik at kun én tråd kan sette inn et hashmap av gangen
    public static HashMap<String, Subsekvens> lesFil(String fil) throws FileNotFoundException{
        HashMap<String, Subsekvens> nyMappe = new HashMap<String, Subsekvens>();
        File minFil = new File(fil);
        Scanner sc = new Scanner(minFil);

        //passer på at hvis man leser filer fra data-mappen vil overskriften (amino_acid) bli fjernet før innlesingen
        try{
            sc.skip("amino_acid");
        }
        catch(NoSuchElementException e){
        }

        while(sc.hasNextLine()) {
            String linje = sc.nextLine();

            while(linje.length() > 2) { //så lenge linja har minst tre tegn
                int start = 0;
                ArrayList<String> unikListe = new ArrayList<String>();
                for(int i = start; i<(linje.length()-2); i++){
                    String sekvens = linje.substring(i, (i+3));

                    unikListe.add(sekvens);  //liste med duplikater av sekvenser

                    Set<String> set  = new HashSet<String>(unikListe);
                    unikListe.clear();
                    unikListe.addAll(set);
                    //liste er nå en liste over alle unike Subsekvenser på en linje

                    for(String sek: unikListe){ //legger til i hashmap-et for hver unike Subsekvens
                        Subsekvens nySubsekvens = new Subsekvens(sek);
                        nySubsekvens.oek(1);
                        nyMappe.put(sek, nySubsekvens);
                    }
                }
                break;
                
            }
        }
        sc.close();
        return nyMappe;
    }

    public static HashMap<String, Subsekvens> slaSammen(HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2){
        HashMap <String, Subsekvens> nyMap = new HashMap<String, Subsekvens>();

        for(String s: map1.keySet()){
            nyMap.put(s, map1.get(s));
        }
        for(String s: map2.keySet()){
            if(nyMap.containsKey(s)){
                nyMap.get(s).oek(map2.get(s).hentAntall());
            }
            else{
                nyMap.put(s, map2.get(s));
            }
        }

        return nyMap;
    }
}