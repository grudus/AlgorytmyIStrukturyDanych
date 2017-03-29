public class Zamowienie {
    public final String nazwa;
    public final int ilosc;

    public Zamowienie(String nazwa, int ilosc) {
        this.nazwa = nazwa;
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "Zamowienie{" +
                "nazwa='" + nazwa + '\'' +
                ", ilosc=" + ilosc +
                '}';
    }
}
