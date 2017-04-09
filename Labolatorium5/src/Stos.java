public class Stos<T> implements Stack<T> {

    private final Lista<T> lista;

    public Stos() {
        this.lista = new Lista<T>();
    }

    @Override
    public boolean empty() {
        return lista.pusta();
    }

    @Override
    public T push(T elem) {
        lista.dodajPrzed(elem, e -> true);
        return elem;
    }

    @Override
    public T pop() {
        return lista.usunPierwszyElement();
    }

    @Override
    public T peek() {
        return lista.get((e) -> true);
    }
}
