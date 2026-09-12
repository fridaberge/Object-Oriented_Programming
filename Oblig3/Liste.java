public interface Liste<T> {
    public int stoerrelse();
    public void leggTil(T x);
    public T hent();
    public T fjern();

}

