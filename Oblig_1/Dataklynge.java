//importlinjer
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//klassen dataklynge
public class Dataklynge {

    //instansvariabler
    private ArrayList <Rack> racks;
    private final int maksAntNoder = 12;
    private static int maksPros = 16;
    private static int maksMinne = 1024*4;

    //konstruktør
    public Dataklynge(){
        racks = new ArrayList<Rack>();
    }

    //metode for å sette inn node i dataklynge
    public void settInnNode(Node node){

        //legger til node i siste rack i dataklyngen
        if ((racks.size() >= 1) && (racks.get(racks.size()-1).getAntNoder() < maksAntNoder)){
            racks.get(racks.size()-1).leggTilNode(node);
        }
        //lager nytt rack dersom rack-listen ikke har noen plasser ledig eller ikke har plasser
        else{
            Rack nyRack = new Rack();
            racks.add(nyRack);
            nyRack.leggTilNode(node);
        }

    }

    //metode som finner antall noder med nok (oppført) minne i hele dataklyngen
    public int noderMedNokMinneIRack(int paakrevdMinne){
        int antNoderMedNokMinne = 0;
        for (Rack rack: racks){
            antNoderMedNokMinne += rack.noderMedNokMinne(paakrevdMinne);
        }
        return antNoderMedNokMinne;
    }

    //metode som finner antall prosessorer totalt
    public int antProsessorer(){
        int totalAntPros = 0;
        for (Rack rack: racks){
            totalAntPros += rack.antProsessorer();
        }
        return totalAntPros;

    }

    //metode som finner lengden på rack-lista / antall racks
    public int antRacks(){
        return racks.size();
    }

    //metode som skriver ut informasjon om dataklyngen
    public void skrivInfo(){
        System.out.print("Antall racks: " + antRacks() + "\nAntall prosessorer: " + antProsessorer() + "\nNoder med minst 128GB: " + noderMedNokMinneIRack(128) + "\nNoder med minst 512GB: " + noderMedNokMinneIRack(512)+ "\nNoder med minst 1024GB: " + noderMedNokMinneIRack(1024)+ "\n\n");
    }

    //metode som gjør at dataklyngen kan hente informasjon fra fil til å oprette objektet
    public void lesFraFil(String filnavn){
        Scanner fil = null;
        int antNoder = 0;
        int antProsPerNode = 0;
        int minnePerNode = 0;
        int linjenummer = 0;

        //hvis man skriver riktig filnavn
        try{
            fil = new Scanner(new File(filnavn));
        }
        //hvis man skriver feil filnan
        catch(FileNotFoundException e){
            System.out.println("Error fant ikke filen");
            return;
        }

        //legger inn data fra fil
        while (fil.hasNextInt()){
            antNoder = fil.nextInt();
            antProsPerNode = fil.nextInt();
            minnePerNode = fil.nextInt();
            linjenummer += 1;

            if (minnePerNode <= maksMinne && antProsPerNode <= maksPros){
            for(int a = 0; a < antNoder; a++){
                Node nodeTyp1 = new Node(antProsPerNode, minnePerNode);
                settInnNode(nodeTyp1);
            }
            
            }
            //gir feilmelding dersom man har oppgitt for høyt antall prosessorer per node eller for mye minne per node
            else if(minnePerNode > maksMinne || antProsPerNode > maksPros){
                if (minnePerNode > maksMinne){
                    System.out.println("I filen er det ikke oppgitt riktig minnestørrelse på linje "+linjenummer);
                }
                if (antProsPerNode > maksPros){
                    System.out.println("I filen er det ikke oppgitt riktig antall prosessorer på linje"+linjenummer);
                }
            }
        }
        //skriver informasjonen om dataklyngen
        skrivInfo();
        //lukker filen
        fil.close();
    }
}
