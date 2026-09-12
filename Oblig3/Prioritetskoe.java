public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {


    //legge til elementer i prioritert rekkefølge (lav til høy), likt kan ligge uavhengig
    @Override
    public void leggTil(T x){

        Node<T> nyNode = new Node<T>(x);
        Node<T> denneNoden = start;

        //dersom det ikke finnes noen noder fra før
        if (start == null){
            start = nyNode;
            slutt = nyNode;
        }

        //hvis den nye noden skal legges sist
        else if (x.compareTo(slutt.hentData()) > 0){
            Node<T> kopiSlutt = slutt;
            slutt = nyNode;
            slutt.settForrige(kopiSlutt);
            kopiSlutt.settNeste(slutt);
        }

        //hvis den nye noden skal legges først
        else if(x.compareTo(start.hentData()) < 0){
            Node<T> kopiStart = start;
            start = nyNode;
            start.settNeste(kopiStart);
            kopiStart.settForrige(nyNode);

        }

        //dersom noden skal legges midt i koen
        else{
            while(x.compareTo(denneNoden.hentData()) >= 0 && denneNoden.neste != null){
                denneNoden = denneNoden.neste;
            }
            //er nå på node med lik eller lavere verdi enn den vi legger til
            //hvis noden er midt i koen
            if (denneNoden.hentNeste() != null){
                nyNode.settForrige(denneNoden.hentForrige());
                denneNoden.hentForrige().settNeste(nyNode);
                nyNode.settNeste(denneNoden);
                denneNoden.settForrige(nyNode);
            }            

        }
        
        
    }

    //det minste (første) elementet skal fjernes slik som i Lenkeliste<T>, så trenger ikke override metoden fjern()
    //det minste elemenetet skal hentes frem (det første), som også er likt som i Lenkeliste<T> hent()
}
