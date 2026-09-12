import java.util.ArrayList;

//feil som må fikses
//slangen skal gjøre ruta bak siste hale-rute til hvit/grå--------------------


public class Modell {
    GUI gui;
    int lengde = 0;
    boolean spilletErFerdig = false;
    Thread slange = new Thread(new Timer());
    int denneRad;
    int denneKol;
    int dimensjon = 12;
    String[][] ruter;
    boolean muligStartS = true;
    boolean muligStartP = true;
    final int antPenger = 10;
    int retning = trekk(1,4); //1-ned, 2-opp, 3-høyre, 4-venstre
    ArrayList<Haledel> hale = new ArrayList<>();


    Modell(GUI g){
        ruter = new String[dimensjon][dimensjon];
        denneRad = trekk(0,dimensjon-1);
        denneKol = trekk(0,dimensjon-1);
        gui = g;
    } 

    void oekLengde(){
        lengde ++;
        gui.oppdaterLengde(lengde);
    }

    //klasse for å trekke tilfeldig tall
    private static int trekk (int a, int b) {
        // Trekk et tilfeldig heltall i intervallet [a..b];
        return (int)(Math.random()*(b-a+1))+a;
    }

    class Timer implements Runnable{
        public void run(){
            while(denneRad < ruter.length && denneKol <ruter[0].length){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("spill stoppet");
                    return;
                }
                spis(denneRad, denneKol); //spiser og oppdaterer hvis det er en pengerute
                flyttSlange(denneRad, denneKol);
    
                //flytter kroppen
                for(int d = 0; d<hale.size()-1; d++){        
                    hale.get(d+1).rad = hale.get(d).rad;
                    hale.get(d+1).kol = hale.get(d).kol;
                    System.out.println(hale.get(d).rad + " " + hale.get(d).kol);
                    System.out.println(d);
                    gui.flyttRute(hale.get(d).rad, hale.get(d).kol); 
                }

            }
        }
    }

    public void spis(int rad, int kol){
        if(gui.sjekkPenger(rad, kol)){
            oekLengde();
            gui.leggPenger(trekk(1,dimensjon-1), trekk(1,dimensjon-1));
            // System.out.println("spiser");
            leggTilHale(rad, kol);
        }
        // if(gui.sjekkKollisjon(rad, kol)){
        //     slange.interrupt();
        //     gui.oppdaterStatus();
        // }
    }

    public void leggTilHale(int rad, int kol){
        // hvis slnagen spiser skal den få lenger hale
        // 1-ned, 2-opp, 3-høyre, 4-venstre
        int haleRad;
        int haleKol;

        if(hale.size() < 2){
            if(retning == 1){
                haleRad = rad-hale.size();
                haleKol = kol;
            }
            else if(retning == 2){
                haleRad = rad+hale.size();
                haleKol = kol;
            }
            else if(retning == 3){
                haleRad = rad;
                haleKol = kol-hale.size();
            }
            else{
                haleRad = rad;
                haleKol = kol+hale.size();
            }
            Haledel nyHale = new Haledel(haleRad, haleKol);
            hale.add(nyHale);
            gui.markerRute(nyHale.rad, nyHale.kol);
        }
        else{
            int sisteHalerad = hale.get(hale.size()-1).rad;
            int nestSisteHalerad = hale.get(hale.size()-1).rad;
            int sisteHalekol = hale.get(hale.size()-2).kol;
            int nestSisteHalekol = hale.get(hale.size()-2).kol;
            //horisontalt
            if(sisteHalerad == nestSisteHalerad){
                //høyre
                if(sisteHalekol == nestSisteHalekol-1){
                    Haledel nyHale = new Haledel(sisteHalerad, sisteHalekol-1);
                    hale.add(nyHale);
                    gui.markerRute(nyHale.rad, nyHale.kol);
                }
                else if(sisteHalekol == nestSisteHalekol+1){
                    Haledel nyHale = new Haledel(sisteHalerad, sisteHalekol+1);
                    hale.add(nyHale);
                    gui.markerRute(nyHale.rad, nyHale.kol);
                }
            }
            //vertikalt
            else if(sisteHalekol == nestSisteHalekol){
                //nedover
                if(sisteHalerad == nestSisteHalerad-1){
                    Haledel nyHale = new Haledel(sisteHalerad-1, sisteHalekol);
                    hale.add(nyHale);
                    gui.markerRute(nyHale.rad, nyHale.kol);
                }
                //oppover
                else if(sisteHalerad == nestSisteHalerad+1){
                    Haledel nyHale = new Haledel(sisteHalerad+1, sisteHalekol);
                    hale.add(nyHale);
                }
            }
        }
        
        
        
        
    }


    public void startSlange(){
        if(muligStartS == true){
            gui.markerRute(denneRad, denneKol);
            //System.out.println(denneRad+" , "+denneKol);
            oekLengde();
            slange.start();
            muligStartS = false;
        }
    }

    //når man ikke endrer retning skal slange fortsette i reningen den går
    public void flyttSlange(int rad, int kol){

        if(retning == 1){ //ned
            flyttSlangeNed(rad,kol);
        }
        else if(retning == 2){ //opp
            flyttSlangeOpp(rad,kol);
        }
        else if(retning == 3){ //høyre
            flyttSlangeHoyre(rad,kol);
        }
        else{ //venstre
            flyttSlangeVenstre(rad, kol);
        }

        if(hale.size()==0){
            gui.leggBak(rad, kol);
        }
        // else{
            // System.out.println(hale.get(hale.size()-1).rad + " " + hale.get(hale.size()-1).kol);
            // gui.leggBak(hale.get(hale.size()-1).rad, hale.get(hale.size()-1).kol);
        // }
        else if (hale.size() >0){
            //gui.leggBak(hale.get(hale.size()-1).rad, hale.get(hale.size()-1).kol);
            // rad-hale.size() burde være lik hale.get(hale.size()-1).rad i retning 1(ned) når den går rett nedover (uten å svinge)
            //1-ned, 2-opp, 3-høyre, 4-venstre
            if(retning == 1){
                gui.leggBak(hale.get(hale.size()-1).rad, kol);
            }
            else if(retning == 2){
                gui.leggBak(rad+hale.size(), kol);
            }
            else if(retning == 3){
                gui.leggBak(rad, kol-hale.size());
            }
            else{
                gui.leggBak(rad, kol+hale.size());
            }

        // }
        // else if(hale.size() >1){
            
        //     int haleRad = hale.get(hale.size()-1).rad;
        //     int haleKol = hale.get(hale.size()-1).kol;
        //     int nestSisteHalerad = hale.get(hale.size()-2).rad;
        //     int nestSisteHalekol = hale.get(hale.size()-2).kol;
        //     //gui.leggBak(haleRad, haleKol);
            
        //     // if(retning == 1){ //ned
        //     //     gui.leggBak(haleRad-1, haleKol);
        //     // }
        //     // else if(retning == 2){ //opp
        //     //     gui.leggBak(haleRad+1, haleKol);
        //     // }
        //     // else if(retning == 3){ //høyre
        //     //     gui.leggBak(haleRad, haleKol-1);
        //     // }
        //     // else{ //venstre
        //     //     gui.leggBak(haleRad, haleKol+1);
        //     // }

        //     // //horisontalt
        //     if(nestSisteHalerad == haleRad){
        //         //venstre
        //         if(nestSisteHalekol== haleKol-1){
        //             gui.leggBak(haleRad, haleKol+1);
        //         }
        //         //høyre
        //         else if(nestSisteHalekol == haleKol+1){
        //             gui.leggBak(haleRad, haleKol-1);
        //         }
        //     }
        //     //vertikalt
        //     else if(nestSisteHalekol == haleKol){
        //         //opp
        //         if(nestSisteHalerad == haleRad-1){
        //             gui.leggBak(haleRad+1, haleKol);
        //         }
        //         //ned
        //         else if(nestSisteHalekol == haleRad+1){
        //             gui.leggBak(haleRad-1, haleKol);
        //         }
        //     }

           }
    }

    public void startPenger(){
        if(muligStartP == true){
            for (int i = 0; i < antPenger; i++ ){
                gui.leggPenger(trekk(0,dimensjon-1), trekk(0,dimensjon-1));
            }
            muligStartP = false;
        }
    }

    public void flyttSlangeNed(int rad, int kol){
        //hvis slangen er helt nederst
        if(rad == dimensjon-1){
            gui.oppdaterStatus();
            slange.interrupt();
        }
        else{
            gui.flyttRute(rad+1, kol);
            denneRad = rad+1;
        }
    }

    public void flyttSlangeOpp(int rad, int kol){
        //hvis slangen er helt nederst
        if(rad == 0){
            gui.oppdaterStatus();
            slange.interrupt();
        }
        else{
            gui.flyttRute(rad-1, kol);
            denneRad = rad-1;
        }
    }

    public void flyttSlangeHoyre(int rad, int kol){
        //hvis slangen er helt nederst
        if(kol == dimensjon-1){
            gui.oppdaterStatus();
            slange.interrupt();
        }
        else{
            gui.flyttRute(rad, kol+1);
            denneKol = kol+1;
        }
    }

    public void flyttSlangeVenstre(int rad, int kol){
        //hvis slangen er helt nederst
        if(kol == 0){
            gui.oppdaterStatus();
            slange.interrupt();
        }
        else{
            gui.flyttRute(rad, kol-1);
            denneKol = kol-1;
        }
    }

    class Haledel{
        int rad;
        int kol;

        Haledel(int rad, int kol){
            this.rad = rad;
            this.kol = kol;
        }
    }

}
