import graph.Graph;

public class Lab12 {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.linkVertex(0, 1);
        graph.linkVertexOneDirection(0, 2);
        graph.linkVertex(1, 2);

        graph.printEdges();
        graph.printVertices();

        graph.removeLink(0, 2);
        System.out.println("------------------");

        graph.printEdges();
        graph.printVertices();
    }

}
