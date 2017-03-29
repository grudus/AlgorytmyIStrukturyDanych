package lab3;

import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Predicate;

import static java.lang.String.format;

public class Main {
    static Lista<Karta> karty = new Lista<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int odp = -1;

        while (odp != 8) {
            System.out.println("Witaj. Co chcesz zrobic?");
            System.out.println("1) Utwórz listę");
            System.out.println("2) Wyświetl listę");
            System.out.println("3) Wyświetl liczbę elementów listy");
            System.out.println("4) Wyświetl karty o podanej wartości");
            System.out.println("5) Wyświetl karty po podanym kolorze");
            System.out.println("6) Usuń pierwsze wystąpienie konkretnej karty");
            System.out.println("7) Usuń wszystkie karty o podanej wartosci");
            System.out.println("8) Wyjdź");

            odp = Integer.parseInt(scanner.nextLine());

            reakcjaZUzytkownikiem(odp);
        }
    }

    private static void reakcjaZUzytkownikiem(int odp) {
        switch (odp) {
            case 1:
                karty = RandomKartaGenerator.generuj();
                break;
            case 2:
                karty.wyswietl();
                break;
            case 3:
                System.out.println(karty.liczbaElementow());
                break;
            case 4:
                int wartosc = wczytaj("Podaj wartosc (1 - 13): ");
                karty.szukaj(karta -> karta.wartosc == wartosc).wyswietl();
                break;
            case 5:
                int kolor = wczytaj("Podaj kolor (0 - 3): ");
                karty.szukaj(karta -> karta.kolor == kolor).wyswietl();
                break;
            case 6:
                int _wartosc = wczytaj("Podaj wartosc (1 - 13): ");
                int _kolor = wczytaj("Podaj kolor (0 - 3): ");
                karty.usunPierwszeWystapienie(karta -> karta.kolor == _kolor && karta.wartosc == _wartosc);
                break;
            case 7:
                int doUsunieciaWartosc = wczytaj("Podaj wartosc (1 - 13): ");
                karty.usun(karta -> karta.wartosc == doUsunieciaWartosc);
                break;
        }
    }

    private static int wczytaj(String text) {
        System.out.println(text);
        return Integer.parseInt(scanner.nextLine());
    }

}