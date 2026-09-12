import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class oveInput {

    public static void main(String[] args) {
        valgLokke();
    }

        
        public static void valgLokke() {
            String legemiddelListe = {"legemid1", "legemid2"};
            
            System.out.println("Legesystem hovedmeny: \nSkriv...\no for å få en oversikt over alle elementer i systemet\nn for å opprette og legge til nye objekter i systemet\nb for å bruke en resept\ns for å skrive ut statistikk\nf for å skirive alle data til fil\ne for å avslutte\n");
            Scanner input = new Scanner(System.in);
            String kommando = "";
            
            while(!kommando.equalsIgnoreCase("e")){
                kommando = input.nextLine();
    
                //skriver info om alle objekter
                // if(kommando.equalsIgnoreCase("o")){
                //     skrivInfo();
                // }
    
                //opprette og legge til nye objekter i systemet
                if (kommando.equalsIgnoreCase("n")){
                    System.out.println("Hva vil du oprette? \nSkriv... \nl for lege,r for resept,m for legemiddel,p for pasient");
                    String objektType = input.nextLine();
    
                    //oppretter lege
                    if(objektType.equalsIgnoreCase("l")){
                        System.out.println("v hvis du vil opprette vanlig lege og s hvis du vil opprette spesialist\n");
                        String legetype = input.nextLine();
                        //oppretter vanlig lege
                        if(legetype.equalsIgnoreCase("v")){
                            System.out.println("Oppgi legens navn: ");
                            String navn = input.nextLine();
                            Lege nyLege = new Lege(navn);
                            System.out.println("Du opprettet legen: \n" + nyLege);
                        }    
                        //oppretter spesialist
                        else if(legetype.equalsIgnoreCase("s")){
                            System.out.println("Oppgi spesialsitens navn: ");
                            String navn = input.nextLine();
                            System.out.println("Oppgi spesialsitens kontrollID: ");
                            String kontrollID = input.nextLine();
                            Spesialist nyLege = new Spesialist(navn, kontrollID);
                            System.out.println("Du oprettet legen: \n" + nyLege);
                        }
                        else{
                            System.out.println("ikke gyldig input");
                        }
                    }
                    //oppretter resept-objekt
                    //Legemidler legemiddelArg, Leger legeArg, Pasient pasientArg, int reitArg
                    else if (objektType.equalsIgnoreCase("r")){
                        System.out.println("oppgi...\nb hvis du vil opprette blå resept, h hvis du vil opprette hvit resept, m hvis du vil opprette milresept og p hvis du vil opprette p-resept");
                        String reseptType = input.nextLine();
                        if(reseptType.equalsIgnoreCase("b") || reseptType.equalsIgnoreCase("h") || reseptType.equalsIgnoreCase("p")){
                            System.out.println("Oppgi et legemiddel fra lista: " + legemiddelListe);
                            String legemiddel = 
                        }
                    }
                    else if (objektType.equalsIgnoreCase("m")){
                        System.out.println("oppretter legemiddel");
                    }
                    else if (objektType.equalsIgnoreCase("p")){
                        System.out.println("oppretter pasient");
                    }
                    else{
                        System.out.println("ikke gyldig input");
                    }
                }
            }
            System.out.println("Ferdig");
            
        }
    }