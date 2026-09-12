import java.util.Scanner;

public class Oblig6 {
    public static void main(String[] args) {
        try{
            String filnavn = args[0];
            Labyrint lab = new Labyrint(filnavn);
            taKommando(lab);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Mulige feil...\n-Feil filnavn\n-Feil ved stuktur i fil\n-Koordinatene til startpunktet er utenfor labyrinten\n-Feil input av koordinater (eks. ikke to tall)");
        }
        catch(NumberFormatException e){
            System.out.println("Mulige feil...\n-Feil format i filen (det er ikke tall som indikerer dimensjonene pa labyrinten)\n-Bruker skrev feil format ved input");
        }
        catch(NullPointerException e){
            System.out.println("Feil dimensjoner pa labyrinten, mulige feil: \n- dimensjonene samsvarer ikke med tallene i overste linje\n- Det er ikke like mange kolonner i hver rad");
            System.exit(1);
        }
    }


    public static void taKommando(Labyrint lab){
        Scanner sc = new Scanner(System.in);
        System.out.println("Oppgi startkoordinater <rad> <kolonne>: (-1 for a avslutte) ");
        String input = sc.nextLine();

        while(input != "-1"){
            try{
                String koordinater[] = input.split(" ");
                if(koordinater.length > 2){
                    System.out.println("For mange tall, bruker bare de to forste tallene oppgitt");
                }
                lab.finnUtveiFra(Integer.parseInt(koordinater[0]), Integer.parseInt(koordinater[1]));

            }
            catch(ArrayIndexOutOfBoundsException e){
                if(input.equals("-1")){
                    System.out.println("ute");
                    System.exit(1);
                }
                else{
                System.out.println("Koordinatene er utenfor dimesnjonene til labyrinten");
                }
            }
            System.out.println("Oppgi startkoordinater <rad> <kolonne>: ");
            input = sc.nextLine();
        }
        sc.close();
        System.exit(0);
        

    }
}

