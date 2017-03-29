package lab3;


import java.util.Random;

public class RandomKartaGenerator {

    private static final Random RANDOM = new Random();

    public static Lista<Karta> generuj() {
        Lista<Karta> lista = new Lista<>();

        while (true) {
            Karta karta = Karta.random(RANDOM);
            if (karta.wartosc == 0)
                break;
            dodajNaOdpowiedniaPozycje(lista, karta);
        }
        return lista;
    }

    private static void dodajNaOdpowiedniaPozycje(Lista<Karta> lista, Karta karta) {
        lista.dodajPrzed(karta, kartaZListy ->
                karta.wartosc < kartaZListy.wartosc
                        || (karta.wartosc == kartaZListy.wartosc && karta.kolor < kartaZListy.kolor));
    }

}