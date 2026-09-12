public class IndeksertListe<T> extends Lenkeliste<T> {

    public int str = 0;

    //sette inn x i listen i posisjon pos der 0<=pos<=stoerrelse(). Dette betyr at alle elementene lenger ut i listen forskyves og heretter får en høyere indeks
    public void leggTil(int pos, T x){ //pos = indeks

        //hvis man oppgir riktig pos
        if (pos >= 0 && pos <= stoerrelse()){
            Node<T> denneNoden = start;
            Node<T> nyNode = new Node<T>(x);
            int posisjon = 0; 
            
            //hvis det ikke finnes startnode
            if (start == null){
                start = nyNode;
                slutt = nyNode;
            }
            
            else{

                //når man legger til først
                if(pos == 0){
                    Node<T> kopiStart = start;
                    start = nyNode;
                    nyNode.settNeste(kopiStart);
                    kopiStart.settForrige(nyNode);
                    return;
                }

                //når man legger til på siste plassen
                else if (pos == stoerrelse()-1) {
                    slutt.hentForrige().settNeste(nyNode);
                    nyNode.settForrige(slutt.hentForrige());
                    nyNode.settNeste(slutt);
                    slutt.settForrige(nyNode);
                }

                //når man legger til på etter siste
                else if (pos == stoerrelse()){
                    slutt.settNeste(nyNode);
                    nyNode.settForrige(slutt);
                    slutt = nyNode;
                }

                //når man legger til på alle andre plasser
                else{
                    while(posisjon < pos-1){
                        denneNoden = denneNoden.hentNeste();
                        posisjon ++;
                        }
        
                        //posisjonen til denneNoden før der vi skal legge til
                        if (denneNoden.hentNeste() != null){
                            denneNoden.settNeste(nyNode);
                            nyNode.settForrige(denneNoden);
                            denneNoden.hentNeste().settForrige(nyNode);
                            nyNode.settNeste(denneNoden.hentNeste());
                        }
                }
                
                
            }
        }

        else{
            throw new UgyldigListeindeks(pos);
        }
    }

    //erstatte elementet i posisjon pos med x. Lovlig pos er 0<=pos<stoerrelse().
    public void sett (int pos, T x){
        if (pos >= 0 && pos < stoerrelse()){
            Node<T> denneNoden = start;
            Node<T> nyNode = new Node<T>(x);
            int posisjon = 0;
            
            while(posisjon < pos){
                denneNoden = denneNoden.hentNeste();
                posisjon ++;
            }
            //denneNoden er nå den noden vi vil bytte
            denneNoden.hentForrige().settNeste(nyNode);
            nyNode.settForrige(denneNoden.hentForrige());
            denneNoden.hentNeste().settForrige(nyNode);
            nyNode.settNeste(denneNoden.hentNeste());
        }
        
        else{
            throw new UgyldigListeindeks(pos);
        }
    }

    //hente elementet i gitt posisjon der 0<=pos<stoerrelse(). Elementet skal bli stående i listen.
    public T hent(int pos){
        Node<T> denneNoden = start;

        //hvis man oppgir ugyldig indeks
        if(pos < 0 || pos > stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }

        //hvis man henter
        else{
            int posisjon = 0;
            while(posisjon < pos){
                denneNoden = denneNoden.hentNeste();
                posisjon ++;
            }
        
            //posisjonen til denneNoden er nå den noden vi vil hente ut
            return denneNoden.hentData();
        }
    }

    //Metoden fjern(int pos) skal fjerne elementet i posisjon pos (der 0<=pos<stoerrelse()) og returnere det.
    public T fjern(int pos){
        //kaster feilmelding om instansvariabler ikke stemmer
        if(pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }

        else{
            //hvis man fjerner første node
            if (pos == 0){
                Node<T> kopiStart = start;
                start = start.hentNeste();
                Node<T> denneNoden = start;
                // while(denneNoden.hentNeste() != null){
                //     denneNoden = denneNoden.hentNeste();
                // }
                return kopiStart.hentData();
            }

            //hvis man fjerner siste node
            else if(pos == stoerrelse()-1){
                Node<T> kopiSlutt = slutt;
                slutt = slutt.hentForrige();
                slutt.neste = null;
                return kopiSlutt.hentData();
            }

            //fjerner andre elementer
            else{
                Node<T> denneNoden = start;
                int posisjon = 0;
                
                while(posisjon < pos){
                    denneNoden = denneNoden.hentNeste();
                    posisjon ++;
                }

                //er på noden jeg vil fjerne 
                Node<T> nyDenne = denneNoden.hentNeste();
                nyDenne.settForrige(denneNoden.hentForrige());
                return denneNoden.hentData();
            }
                
        }
    }


}

