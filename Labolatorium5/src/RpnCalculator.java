import java.util.regex.Pattern;

public class RpnCalculator {
    private final String postfix;
    public static final Pattern VALID_FORMAT = Pattern.compile("^[\\d\\+\\/\\*\\.\\- ]*$");

    public RpnCalculator(String expression) {
        if (!VALID_FORMAT.matcher(expression).matches())
            throw new IllegalArgumentException("Expression " + expression + " didn't matches pattern " + VALID_FORMAT);
        this.postfix = expression;
    }


    public Double calculate() {
        Stos<Double> stosWynikow = new Stos<>();
        String[] expression = postfix.split("\\s+");

        for (String elem : expression) {
            switch (elem) {
                case "+":
                    stosWynikow.push(stosWynikow.pop() + stosWynikow.pop());
                    break;
                case "-":
                    stosWynikow.push(-stosWynikow.pop() + stosWynikow.pop());
                    break;
                case "*":
                    stosWynikow.push(stosWynikow.pop() * stosWynikow.pop());
                    break;
                case "/":
                    double dzielnik = stosWynikow.pop();
                    if (dzielnik == 0)
                        throw new IllegalArgumentException("Nie mozna dzielic przez 0!");
                    stosWynikow.push(stosWynikow.pop() / dzielnik);
                    break;
                default:
                    stosWynikow.push(Double.parseDouble(elem));
                    break;
            }
        }
        return stosWynikow.pop();
    }
}
