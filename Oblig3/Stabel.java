public class Stabel<T> extends Lenkeliste<T>{

    //redefinere metoden leggTil(T x) slik at nye elementer legges først i listen.
    @Override
    public void leggTil(T x){
        Node<T> nyNode = new Node<T>(x);
        Node<T> denneNoden = start;

        //må lage startnode om det ikke eksisterer
        if (start == null){
            start = nyNode;
            slutt = nyNode;
            return;
        }

        //setter den nye noden flrst i lista og resten av eksisterende liste etter
        start.settForrige(nyNode);
        nyNode.settNeste(denneNoden);
        start = nyNode;
    }

}