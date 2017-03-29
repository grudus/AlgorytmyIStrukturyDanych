public class Klient {
    private final String nazwa;
    private Queue<Zamowienie> zamowienia = new Kolejka<>();

    public Klient(String nazwa) {
        this.nazwa = nazwa;
    }

    public void dodajZamowienie(Zamowienie zamowienie) {
        zamowienia.insert(zamowienie);
    }

    public void wyswietlZamowienia() {
        Queue<Zamowienie> temp = zamowienia;
        while (!temp.isEmpty())
            System.out.println(temp.remove());
    }

    @Override
    public String toString() {
        return nazwa;
    }
}
