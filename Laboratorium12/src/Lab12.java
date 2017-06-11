import graph.Graph;

import static java.util.stream.IntStream.range;

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

    private static void init(Graph<Integer> graph) {
        range(0, 7).forEach(graph::addVertex);
        graph.linkVertex(1, 2);
        graph.linkVertex(3, 2);
        graph.linkVertex(7, 2);
        graph.linkVertex(5, 3);
        graph.linkVertex(5, 6);
        graph.linkVertex(5, 4);
        graph.linkVertex(3, 6);
        graph.linkVertex(3, 4);
        graph.linkVertex(6, 4);
        graph.linkVertex(1, 3);
        graph.linkVertex(1, 7);
        graph.linkVertex(7, 6);
    }
}
