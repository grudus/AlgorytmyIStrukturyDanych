import java.util.Arrays;
import java.util.List;

public class RpnExpression {
    private final String expression;

    public RpnExpression(String expression) {
        this.expression = expression;
    }

    public String asString() {
        return expression;
    }

    public List<String> asList() {return Arrays.asList(expression.split("\\s+"));}
}
