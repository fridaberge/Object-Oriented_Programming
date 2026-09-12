import java.io.FileNotFoundException;
import java.util.HashMap;

public class LeseTrad implements Runnable{
    String filnavn;
    Monitor2 monitor;

    public LeseTrad(String f, Monitor2 m){
        filnavn = f; 
        monitor = m;
    }

    @Override
    public void run(){
        try{
            HashMap<String, Subsekvens> nyMap = Monitor2.lesFil(filnavn);
            monitor.settInn(nyMap);
        }
        catch(FileNotFoundException e){
            System.err.println("finner ikke filen");
        }
        
    }


}