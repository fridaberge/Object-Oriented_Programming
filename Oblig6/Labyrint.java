import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrint {
    int antKol = 0;
    int antRad = 0;
    static Rute[][] rutenett = new Rute[0][0];

    public static void main(String[] args) {
        try{
            String filnavn = args[0];
            Labyrint lab = new Labyrint(filnavn);
            lab.finnUtveiFra(3, 3);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Mulige feil...\n-Feil filnavn\n-Feil ved stuktur i fil\n-Koordinatene til startpunktet er utenfor labyrinten");
        }
        catch(NumberFormatException e){
            System.out.println("Feil format i filen (det er ikke tall som indikerer dimensjonene på labyrinten)");
        }
        catch(NullPointerException e){
            System.out.println("Feil dimensjoner pa labyrinten, mulige feil: \n- dimensjonene samsvarer ikke med tallene i overste linje\n- Det er ikke like mange kolonner i hver rad");
            System.exit(1);
        }
        
    }

    public Labyrint(String filnavn){
        rutenett = lesFraFil(filnavn, this);
        antKol = rutenett[0].length;
        antRad = rutenett.length;
        System.out.println(this);
        this.settNaboer(rutenett);
    }

    public void finnUtveiFra(int rad, int kol){
        Rute startRute = rutenett[rad][kol];
        if(startRute instanceof SortRute){
            System.out.println("kan ikke starte pa sort rute");
        }
        System.out.println("Aapninger");
        startRute.finn(null);
    }

    public static ArrayList<Integer> settDimensjon(String filnavn){

        ArrayList<Integer> liste = new ArrayList<>();

        try{
            File minFil = new File(filnavn);
            Scanner sc = new Scanner(minFil);
            
            while(sc.hasNextLine()){
                String linje = sc.nextLine();
                String stripLinje[] = linje.split(" ");
                liste.add(Integer.parseInt(stripLinje[0]));
                liste.add(Integer.parseInt(stripLinje[1]));
                break;
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("finner ikke filen");
        }
        
        return liste;
    }

    public static Rute[][] lesFraFil(String filnavn, Labyrint lab){
        
        int antR = settDimensjon(filnavn).get(0);
        int antK = settDimensjon(filnavn).get(1);
        Rute[][] rutenett = new Rute[antR][antK];

        int plasseringR = 0;

        try{
            File minFil = new File(filnavn);
            Scanner sc = new Scanner(minFil);
            sc.nextLine();

            while(sc.hasNextLine()){
                int plasseringK = 0;
                String linje = sc.nextLine();
                String stripLinje[] = linje.split("");
                plasseringR += 1;
                
                for(String a: stripLinje){
                    plasseringK += 1;

                    //lager rute-objektene
                    if (a.equals(".")){
                        if(plasseringR-1 == 0 || plasseringK-1 == 0 || plasseringK == antK || plasseringR == antR){
                            rutenett[plasseringR-1][plasseringK-1] = new Aapning(plasseringR-1, plasseringK-1, lab);
                        }
                        else{
                            rutenett[plasseringR-1][plasseringK-1] = new HvitRute(plasseringR-1, plasseringK-1, lab);
                        }
                        
                    }
                    else if(a.equals("#")){
                        rutenett[plasseringR-1][plasseringK-1] = new SortRute(plasseringR-1, plasseringK-1, lab);
                    }
                    else{
                        System.out.println("I filen er det oppgitt noe annet enn '.' eller '#");
                    }
                }
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("finner ikke filen");
        }
        return rutenett;
    }


    public void settNaboer(Rute[][] rutenett){
        System.out.println("\n\n");
        for(int r = 0; r < this.antRad; r++){
            for (int k= 0; k < this.antKol; k++){

                //nord
                if(r == 0){ //nar man er på overste rad
                    rutenett[r][k].nord = null;
                }
                else if (r!= 0){
                    rutenett[r][k].nord = rutenett[r-1][k];
                }

                //sør
                if(r == this.antRad-1){ //nar man er på nederste rad
                    rutenett[r][k].sor = null;
                }
                else if (r!= this.antRad-1){
                    rutenett[r][k].sor = rutenett[r+1][k];
                }

                //øst
                if(k == this.antKol-1){ //nar man er lengst til hoyre
                    rutenett[r][k].oest = null;
                }
                else if(k != this.antKol-1){
                    rutenett[r][k].oest = rutenett[r][k+1];
                }

                //vest
                if(k == 0){ //nar man er lengst til venstre
                    rutenett[r][k].vest = null;
                }
                else if(k != 0){
                    rutenett[r][k].vest = rutenett[r][k-1];
                }

            }
        }
    }

    @Override
    public String toString() {
        String string = "\nSlik ser labyrinten ut\n";
        for(int r = 0; r < this.antRad; r++){
            for (int k= 0; k < this.antKol; k++){
                if(rutenett[r][k] instanceof HvitRute){
                    string += " . ";
                }
                else{
                    string += " # ";
                }
                 
            }
            string += "\n";
        }
        return "\nAntall rader: " +antRad+ " Antall Kolonner: " +antKol+string;
    }
}
