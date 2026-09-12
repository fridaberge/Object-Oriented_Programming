import java.util.Iterator;

public class VaarArray<T> implements Iterable<T> {
    T [] arr;

    public VaarArray(int strl){
        arr = (T[]) new Object[strl];
    }

    //Finner ledig plass og setter inn. Hvis det er plass returneres true,
    //hvis det er fullt returneres false
    public boolean settInn(T elem){
        for(int i = 0; i<arr.length; i++){
            if (arr[i] == null){
                return true;
            }
        }
        return false;
    }

    //Returnerer true dersom det var mulig å fjerne, false ellers.
    public boolean fjernFraIndeks(int n){
        //ugydg indeks
        if(arr.length <= n || n<0) return false;

        //hvis plassen er tom
        if(arr[n] == null) return false;

        //hvis man kan fjerne
        arr[n] = null;
        return true;
        
    }

    public Iterator<T> itaretor(){
        return new mittArray(); //vanlig å kalle arrayIterator
    }

    private class mittArray implements Iterator<T>{
        int pos = 0;

        public boolean hasNext(){
            for(int i = pos; i< arr.length; i++){
                if(arr[i] != null){
                    return true;
                }
            }
            return false;
        }

        public T next(){
            for(int i = pos; i< arr.length; i++){
                if(arr[i] != null){
                    T returverdi = arr[i];
                    pos++;
                    return returverdi;
                }
            }
            return null;
        }
    }

    



}
