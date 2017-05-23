public class Lab9 {
    public static void main(String[] args) {
//        String expression = "12 / (3 + (3 * 1))";
        String expression = "2 v 44";
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
