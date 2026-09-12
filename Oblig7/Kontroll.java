
public class Kontroll {
    private GUI gui;
    private Modell modell;


    Kontroll(){
        gui = new GUI(this);
        modell = new Modell(gui);
    }

    public void avslutt(){
        modell.slange.interrupt();
        System.exit(0);
    }

    public void startSpill(){
        modell.startSlange();
        modell.startPenger();
    }

    public void settRetningNed(){
        if(modell.retning == 2){
            gui.oppdaterStatus();
            modell.slange.interrupt();
        }
        modell.retning = 1;
    }

    public void settRetningOpp(){
        if(modell.retning == 1){
            gui.oppdaterStatus();
            modell.slange.interrupt();
        }
        modell.retning = 2;
    }

    public void settRetningHoyre(){
        if(modell.retning == 4){
            gui.oppdaterStatus();
            modell.slange.interrupt();
        }
        modell.retning = 3;
    }

    public void settRetningVenstre(){
        if(modell.retning == 3){
            gui.oppdaterStatus();
            modell.slange.interrupt();
        }
        modell.retning = 4;
    }

}
