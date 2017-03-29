package lab3;

import java.util.Random;

public class Karta {
    public final int kolor;
    public final int wartosc;
    public final boolean znacznik;

    public Karta(int wartosc, int kolor) {
        this.kolor = kolor;
        this.wartosc = wartosc;
        this.znacznik = wartosc != 14;
    }

    public static Karta random(Random random) {
        return new Karta(random.nextInt(14), random.nextInt(4));
    }

    public boolean jestZakryta() {
        return !znacznik;
    }

    @Override
    public String toString() {
        return jestZakryta()
                ? "{}"
                : "{" +
                "wartosc: " + wartosc +
                ", kolor: " + kolor +
                '}';
    }
}