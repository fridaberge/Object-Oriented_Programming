public class HvitRute extends Rute{

    public HvitRute(int radNr, int kolNr, Labyrint lab){
        super(radNr, kolNr, lab, "hvit");
    }

    @Override
    public String toString() {
        return "Farge: " + type + "\nKoordinater: ("+radNr+","+kolNr+")";
    }

    @Override
    public void finn(Rute fra){
        //System.out.println("("+this.radNr+","+this.kolNr+")"); //printer koordinater

        if(fra == null){
            this.nord.finn(this);
            this.sor.finn(this);
            this.oest.finn(this);
            this.vest.finn(this);
        }
        else if(this.nord == fra){
            this.sor.finn(this);
            this.oest.finn(this);
            this.vest.finn(this);
        }
        else if(this.sor == fra){
            this.nord.finn(this);
            this.oest.finn(this);
            this.vest.finn(this);
        }
        else if(this.oest == fra){
            this.nord.finn(this);
            this.sor.finn(this);
            this.vest.finn(this);
        }
        else if(this.vest == fra){
            this.nord.finn(this);
            this.sor.finn(this);
            this.oest.finn(this);
        }

    }
}