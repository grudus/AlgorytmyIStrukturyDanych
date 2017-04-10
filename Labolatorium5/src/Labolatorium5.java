import java.util.Arrays;

public class Labolatorium5 {
    public static void main(String[] args) {
        InfixToPostfix converter = new InfixToPostfix();

        String odpowiedz;
        while (true) {
            odpowiedz = Klawiatura.wczytajLinie("Podaj wyrazenie lub nacisnij 'q' aby wyjsc");
            if (odpowiedz.equals("q"))
                return;

            String infixExpression = odpowiedz;
            converter.convert(infixExpression);
            String postfix = converter.prettyFormat();

            System.out.println("INFIX: " + infixExpression);
            System.out.println("POSTFIX: " + postfix);
            System.out.println("WYNIK: " + new RpnCalculator(postfix).calculate());

        }
    }
}
