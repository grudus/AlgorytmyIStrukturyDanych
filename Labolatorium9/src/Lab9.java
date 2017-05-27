import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab9 {
    public static void main(String[] args) {
        String expression = "- 2 * (3 + 5) / (2 / 1) * 1 + 3 - 4";

        Pattern p = Pattern.compile("\\s*[-+\\(\\)*/]\\s*|(-?\\d+\\.?\\d*)\\s*");
        Matcher m = p.matcher(expression);

//        while (m.find()) {
//            System.out.println(m.group());
//        }

        //\s*(\d+\.?[\d+]?)\s*|[-+\(\)*/]\s*


        RpnExpression rpn = new RpnConverter().convert(expression);

        RpnBinaryTree tree = new RpnToBinaryTreeConverter(rpn).convert();

        tree.printAsTree();

        System.out.println("Infix: \n" + rpn.asString());
        System.out.println("InOrder: ");
        tree.printInOrder();
        System.out.println("\nPostOrder: ");
        tree.printPostOrder();

        System.out.println("\nleafs: " + tree.leafs());
        System.out.println("Nodes: " + tree.nodes());
        System.out.println("Height: " + tree.height());

        System.out.println("Result: " + tree.solve());
    }
}
