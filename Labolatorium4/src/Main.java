public class Main {
    public static void main(String[] args) {
        Klient klient = new Klient("Marcin");

        klient.dodajZamowienie(new Zamowienie("dupa", 2));
        klient.dodajZamowienie( new Zamowienie("hehe", 131));
        klient.dodajZamowienie( new Zamowienie("ffaafafafaf", 131));

        Klient klient2 = new Klient("Kuba");

        klient2.dodajZamowienie(new Zamowienie("sesese", 123));

        Klient klient3 = new Klient("Wokulski");

        klient3.dodajZamowienie(new Zamowienie("Lalka", 1));
        klient3.dodajZamowienie( new Zamowienie("Złoto", 1_000_000));

        Magazyn magazyn = new Magazyn();
        magazyn.dodajKlienta(klient);
        magazyn.dodajKlienta(klient2);
        magazyn.dodajKlienta(klient3);

        magazyn.zrealizujZlecenia();
    }
}
