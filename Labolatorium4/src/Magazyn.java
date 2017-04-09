import java.util.Map;

import static java.lang.String.format;

public class Magazyn {
    private Queue<Klient> klienci = new Kolejka<>();

    public void dodajKlienta(Klient klient) {
        klienci.insert(klient);
    }

    public void zrealizujZlecenia() {
        while (!klienci.isEmpty()) {
            Klient klient = klienci.remove();
            System.out.println(format("Klient %s: zlecenia zrealizowane:", klient));
            klient.wyswietlZamowienia();
        }
    }

}
