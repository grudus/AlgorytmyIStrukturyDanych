import java.util.Scanner;

public class Klawiatura {
    private final static Scanner scanner = new Scanner(System.in);

    public static String wczytajLinie(String messageBefore) {
        System.out.println(messageBefore);
        return scanner.nextLine();
    }
}
