import java.util.ArrayList;
import java.util.HashMap;

public class FletteTrad implements Runnable{
    public Monitor2 monitor;
    public int id;

    public FletteTrad(Monitor2 m, int id){
        monitor = m;
        this.id = id;
    }

    @Override
    public void run(){
        while(monitor.antFlettGjort < monitor.ferdigFlett){
            ArrayList<HashMap<String, Subsekvens>> to = monitor.hentUtTo();
            
            if(to == null){
                return; //trådene termineres dersom de ikke har noe mer å gjøre
            }

            else{
                HashMap<String, Subsekvens> sammenslott = Monitor2.slaSammen(to.get(1), to.get(0)); //slår sammen de to maps-ene som trådene henter ut
                monitor.settInnFlettet(sammenslott);
                monitor.antFlettGjort++;
            }
            
        }
    }

}
