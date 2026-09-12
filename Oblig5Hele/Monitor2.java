import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
  
public class Monitor2 {
    public static ReentrantLock laas = new ReentrantLock();
    public SubsekvensRegister subreg;
    public Condition forFaa = laas.newCondition(); //tilstand når det er for få hashmaps til å flette (under 2)
    public int antFlettGjort = 0;
    public int ferdigFlett; //når n antall flett er ferdig skal trådene termineres

    public Monitor2(){
        subreg = new SubsekvensRegister();
    }

    public void settFerdigFlett(int resterende){
        ferdigFlett = resterende;
    }

    public void settInn(HashMap<String, Subsekvens> sekvens){
        laas.lock(); //kritisk region = kode som endrer felles data
        try{
            subreg.settInn(sekvens);
            forFaa.signalAll(); //sier ifra til flette-trådene at det har blitt lagt inn nye hashmaps som kan flettes
        }
        finally{
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> hentUt(int indeks){
        return subreg.hentUt(indeks); //tar ut første? skal hente ut en vilkårlig
    }

    public HashMap<String, Subsekvens> taUt(){
        return subreg.taUt(); //tar ut første? skal hente ut en vilkårlig
    }

    public int antall(){
        return subreg.antall();
    }

    //utvider slik at kun én tråd kan sette inn et hashmap av gangen
    public static HashMap<String, Subsekvens> lesFil(String fil) throws FileNotFoundException{

        return SubsekvensRegister.lesFil(fil);
    }

    public static HashMap<String, Subsekvens> slaSammen(HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2){
        return SubsekvensRegister.slaSammen(map1, map2);
    }

    public ArrayList<HashMap<String, Subsekvens>> hentUtTo(){
        
        ArrayList<HashMap<String, Subsekvens>> toMaps = new  ArrayList<HashMap<String, Subsekvens>>();
        laas.lock();
        try{
            if(subreg.antall() < 2){ //når det er over eller lik 2 subsek
                return null;
            }
            //System.out.println("henter ut to...");
            toMaps.add(subreg.taUt());
            toMaps.add(subreg.taUt()); //la til (de første) to  maps i en egen liste
            //System.out.println("str: " + subreg.antall());
        }
        finally{
            laas.unlock();
        }
        return toMaps;
    }

    //metode som setter inn det flettede hashmappet
    public void settInnFlettet(HashMap<String, Subsekvens> flettet){
        //System.out.println("setter inn flettet...");
        laas.lock();
        try{
            subreg.settInn(flettet); 
        }
        finally{
            laas.unlock();
        }
        
    }

}