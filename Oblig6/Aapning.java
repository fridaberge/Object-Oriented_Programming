public class Aapning extends HvitRute{

    public Aapning(int radNr, int kolNr, Labyrint lab){
        super(radNr, kolNr, lab);
    }

    @Override
    public void finn(Rute fra){
        System.out.println("("+this.radNr+","+this.kolNr+")"); //printer koordinater
        return;
    }
}
