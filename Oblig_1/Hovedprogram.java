//klassen hovedprogram
public class Hovedprogram {

    public static void main(String[] args) {
        Dataklynge saga = new Dataklynge(); //oppretter dataklynge-objekt
        System.out.println("Info om saga");
        saga.lesFraFil("Dataklynge2.txt"); //legger til data i dataklyngen saga og printer info

    }   
}