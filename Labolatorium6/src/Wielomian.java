//Of course this should be more generic, but I haven't got enough time xD
public class Wielomian {
    private static final Element<Jednomian> WARTOWNIK = new Element<>(new Jednomian(0, -1, -1, -1));
    private Element<Jednomian> pierwszy;

    public Wielomian() {
        pierwszy = WARTOWNIK;
        WARTOWNIK.setNastepny(WARTOWNIK);
    }

    private void dodajNaKoncu(Jednomian element) {
        if (pierwszy == WARTOWNIK) {
            pierwszy = new Element<>(element, pierwszy);
            WARTOWNIK.setNastepny(pierwszy);
        } else {
            Element temp = pierwszy;
            while (temp.nastepny != WARTOWNIK) {
                temp = temp.nastepny;
            }
            temp.nastepny = new Element<>(element, WARTOWNIK);
        }
    }

    public void dodajElement(Jednomian element) {
        if (pierwszy == WARTOWNIK) {
            pierwszy = new Element<>(element, pierwszy);
            WARTOWNIK.setNastepny(pierwszy);
        } else if (pierwszy.wartosc.compareTo(element) < 0)
            pierwszy = new Element<>(element, pierwszy);

        else {
            Element<Jednomian> temp = pierwszy;
            while (true) {
                if (temp.nastepny == WARTOWNIK) {
                    dodajNaKoncu(element);
                    break;
                }
                if (temp.nastepny.wartosc.compareTo(element) < 0) {
                    temp.nastepny = new Element<>(element, temp.nastepny);
                    break;
                }
                temp = temp.nastepny;
            }
        }
    }

    public Wielomian plus(Wielomian wielomian) {
        Wielomian nowy = new Wielomian();

        Element<Jednomian> temp = pierwszy;
        while (temp != WARTOWNIK) {
            nowy.dodajElement(temp.wartosc);
            temp = temp.nastepny;
        }

        temp = wielomian.pierwszy;
        while (temp != WARTOWNIK) {
            nowy.dodajElement(temp.wartosc);
            temp = temp.nastepny;
        }

        return nowy;
    }

    public void wyswietl() {
        if (pierwszy == WARTOWNIK) {
            System.out.println("_PUSTY_");
            return;
        }
        StringBuilder sb = new StringBuilder();

        Element<Jednomian> temp = pierwszy;
        while (temp != WARTOWNIK) {
            sb.append(temp.wartosc);
            temp = temp.nastepny;
        }
        System.out.println(sb.toString());
    }




    public static class Element<T> {
        private final T wartosc;
        private Element<T> nastepny;

        Element(T wartosc) {
            this.wartosc = wartosc;
        }

        public Element(T wartosc, Element<T> nastepny) {
            this.wartosc = wartosc;
            this.nastepny = nastepny;
        }

        public Element<T> getNastepny() {
            return nastepny;
        }

        public void setNastepny(Element<T> nastepny) {
            this.nastepny = nastepny;
        }

        public T getWartosc() {
            return wartosc;
        }
    }
}
