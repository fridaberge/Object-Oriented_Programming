public abstract class Rute {
    //variabel som heter besøkt?
    //sjekk ved veivalg hvilken vei som kan brukes
    int radNr = 0;
    int kolNr = 0;
    Labyrint lab;
    Rute nord;
    Rute sor;
    Rute vest;
    Rute oest;
    String type;

    Rute(int radNr, int kolNr, Labyrint lab, String type){
        this.radNr = radNr;
        this.kolNr = kolNr;
        this.lab = lab;
        this.type = type;
    }

    public void finn(Rute fra){
    }

}
