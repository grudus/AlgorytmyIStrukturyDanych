
public class Kolejka<T> implements Queue<T> {

    private final Lista<T> lista = new Lista<T>();

    @Override
    public void insert(T element) {
        lista.dodajNaKoncu(element);
    }

    @Override
    public T remove() {
        return lista.usunPierwszyElement();
    }

    @Override
    public boolean isEmpty() {
        return lista.pusta();
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
