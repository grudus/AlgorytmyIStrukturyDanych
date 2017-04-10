public class Lista<T> {
    private Element<T> pierwszy;

    public boolean pusta() {
        return pierwszy == null;
    }

    public void dodajNaPoczatek(T elem) {
        pierwszy = new Element<>(elem, pierwszy);
    }

    public T getPierwszy() {
        return pierwszy == null ? null : pierwszy.wartosc;
    }

    public T usunPierwszyElement() {
        if (pierwszy == null) return null;
        Element<T> elem = pierwszy;
        pierwszy = pierwszy.nastepny;
        return elem.wartosc;
    }

    private final static class Element<T> {
        private final T wartosc;
        private final Element<T> nastepny;

        public Element(T wartosc) {
            this(wartosc, null);
        }

        public Element(T wartosc, Element<T> nastepny) {
            this.wartosc = wartosc;
            this.nastepny = nastepny;
        }
    }
}
