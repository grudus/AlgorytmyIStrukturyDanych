import java.util.Arrays;

public class Labolatorium5 {
    public static void main(String[] args) {
        Stos<Integer> stos = new Stos<>();

        stos.push(4);
        stos.push(5);
        stos.push(6);


        System.out.println(new InfixToPostfix().convert("12 + 44 - 2 * 5 / 2 + 5 * 2"));
        System.out.println(new InfixToPostfix().convert("55 + 45 / 12 - 2"));
    }
}