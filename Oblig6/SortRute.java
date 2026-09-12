public class SortRute extends Rute{

    public SortRute(int radNr, int kolNr, Labyrint lab){
        super(radNr, kolNr, lab, "sort");
    }
    
    @Override
    public String toString() {
        return "Farge: " + type + "\nKoordinater: ("+radNr+","+kolNr+")";
    }

    public void finn(Rute fra){
        return;
    }
}
