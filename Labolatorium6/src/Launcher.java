public class Launcher {
    public static void main(String[] args) {

        Wielomian wielomian1 = new Wielomian();

        wielomian1.dodajElement( new Jednomian(1, 6, 1, 0));
        wielomian1.dodajElement(new Jednomian(8, 0, 1, 7));
        wielomian1.dodajElement(new Jednomian(-7, 0, 4, 1));

        Wielomian wielomian2 = new Wielomian();
        wielomian2.dodajElement(new Jednomian(3, 3, 3, 3));
        wielomian2.dodajElement(new Jednomian(-41, 6, 1, 7));

        Wielomian wielomian3 = wielomian1.plus(wielomian2);

        wielomian3.wyswietl();
    }
}
