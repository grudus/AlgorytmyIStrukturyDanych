import java.util.function.Predicate;

public class Lista<T> {
    private Element<T> pierwszy;

    public void wyswietl() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (pierwszy == null) {
            System.out.println(sb.append("]").toString());
            return;
        }
        Element<T> temp = pierwszy;
        while (temp != null) {
            sb.append(temp.wartosc);
            sb.append("\n");
            temp = temp.nastepny;
        }
        System.out.println(sb.deleteCharAt(sb.lastIndexOf("\n")).append("]").toString());
    }

    public T get(Predicate<T> warunek) {
        Element<T> elem = pierwszy;
        while (elem != null) {
            if (warunek.test(elem.wartosc))
                return elem.wartosc;
            elem = elem.nastepny;
        }
        return null;
    }

    public boolean pusta() {
        return pierwszy == null;
    }

    public void dodajNaKoncu(T elem) {
        if (pierwszy == null) {
            pierwszy = new Element<T>(elem);
        } else {
            Element<T> temp = pierwszy;
            while (temp.nastepny != null) {
                temp = temp.nastepny;
            }
            temp.nastepny = new Element<T>(elem);
        }
    }

    public void dodajPrzed(T elem, Predicate<T> warunek) {
        if (pierwszy == null)
            pierwszy = new Element<T>(elem);
        else if (warunek.test(pierwszy.wartosc))
            pierwszy = new Element<T>(elem, pierwszy);

        else {
            Element<T> temp = pierwszy;
            while (true) {
                if (temp.nastepny == null) {
                    dodajNaKoncu(elem);
                    break;
                }
                if (warunek.test(temp.nastepny.wartosc)) {
                    temp.nastepny = new Element<T>(elem, temp.nastepny);
                    break;
                }
                temp = temp.nastepny;
            }
        }
    }

    public Lista<T> szukaj(Predicate<T> warunek) {
        Lista<T> nowa = new Lista<T>();
        if (pierwszy == null)
            return nowa;
        Element<T> temp = pierwszy;
        while (temp != null) {
            if (warunek.test(temp.wartosc))
                nowa.dodajNaKoncu(temp.wartosc);
            temp = temp.nastepny;
        }
        return nowa;
    }

    public int liczbaElementow(Predicate<T> warunek) {
        int elementy = 0;
        Element<T> temp = pierwszy;
        while (temp != null) {
            if (warunek.test(temp.wartosc))
                elementy++;
            temp = temp.nastepny;
        }
        return elementy;
    }

    public int liczbaElementow() {
        return liczbaElementow(a -> true);
    }

    public Element<T> getPierwszy() {
        return pierwszy;
    }

    public T usunPierwszyElement() {
        if (pierwszy == null) return null;
        Element<T> elem = pierwszy;
        pierwszy = pierwszy.nastepny;
        return elem.wartosc;
    }

    public Element<T> usunPierwszeWystapienie(Predicate<T> warunek) {
        if (pierwszy == null) return null;
        else if (warunek.test(pierwszy.wartosc)) {
            Element<T> elem = pierwszy;
            pierwszy = pierwszy.nastepny;
            return elem;
        }
        else {
            Element<T> temp = pierwszy;
            while (temp.nastepny != null) {
                if (warunek.test(temp.nastepny.wartosc)) {
                    Element<T> elem = temp.nastepny;
                    temp.nastepny = temp.nastepny.nastepny;
                    return elem;
                }
                temp = temp.nastepny;
            }
        }
        return null;
    }

    public void usun(Predicate<T> warunek) {
        Element<T> temp = pierwszy;
        while (temp != null) {
            temp = usunPierwszeWystapienie(warunek);
        }
    }

    private final static class Element<T> {
        private T wartosc;
        private Element<T> nastepny;

        public Element(T wartosc) {
            this(wartosc, null);
        }

        public Element(T wartosc, Element<T> nastepny) {
            this.wartosc = wartosc;
            this.nastepny = nastepny;
        }
    }
}
