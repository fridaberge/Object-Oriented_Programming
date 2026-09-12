public abstract class Lenkeliste<T> implements Liste<T>{ //abstract

    public Node<T> start;
    public Node<T> slutt;

    //Metoden stoerrelse() skal returnere hvor mange elementer det er i listen.
    public int stoerrelse(){
        //må starte på første element
        int antall = 0;
        Node<T> denneNoden = start;

        while(denneNoden != null){ //går gjennom elementene
            if (denneNoden.hentData() != null){ //hvis det ligger data i elementene (nodene) teller den antallet
                antall ++;
            }
            denneNoden = denneNoden.hentNeste(); //går videre til neste node
        }
        return antall;
    }

    //Metoden leggTil(T x) skal legge inn et nytt element; det skal legges sist i listen.
    public void leggTil(T x){
        Node<T> nyNode = new Node<T>(x);
        slutt = nyNode;
        Node<T> denneNoden = start;

        //må lage startnode om det ikke eksisterer
        if (start == null){
            start = nyNode;
        }
        
        else{
        //må gå gjennom lista og finne element som ikke har neste-referasne
            while (denneNoden.hentNeste() != null){
                denneNoden = denneNoden.hentNeste();
            }
            denneNoden.settNeste(nyNode); //setter oppgitt node som neste node etter siste node
        }
    }

    //Metoden hent() skal returnere det første elementet i listen, men det skal ikke fjernes fra listen.
    public T hent(){
        return start.hentData();
    }

    //Metoden fjern() skal fjerne det første elementet i listen og returnere det. må passe på å flytte alle andre elementer fremover
    public T fjern(){
        if(start == null){
            throw new UgyldigListeindeks(-1);
        }
        else{
            Node<T> midlertidigStart = start; //lager en kopi av starten
            Node <T> nyStart = start.hentNeste(); //setter andre node til å være første node
            start = nyStart; //setter den nye starten
        
            Node<T> denneNoden = start;
            while (denneNoden != null){
                denneNoden = denneNoden.hentNeste(); //flytter alle nodene forover i lista
            }
            return midlertidigStart.hentData(); //returnerer første node
        }
    }
    
    public String toString(){
        String svar = "\nInnhold: \n";
        Node<T> denneNoden = start;
        
        while (denneNoden != null){
            svar += "Node: " + denneNoden.hentData() + "\n"; 
            denneNoden = denneNoden.hentNeste();
        }
        return svar;
    }
}


class Node <T> {
    T verdi;
    Node<T> neste;
    Node<T> forrige;

    public Node(T verdi){ //konstruktør med parameter
        this.verdi = verdi;
    }

    public T hentData(){
        return verdi;
    }

    public Node(){ //komnstruktør uten parametere
    }

    public void settNeste(Node<T> n){
        neste = n;
        neste.settForrige(this);
    }

    public Node<T> hentNeste(){
        return neste;
    }

    public void settForrige(Node<T> n){
        forrige = n;
    }

    public Node<T> hentForrige(){
        return forrige;
    }

}
