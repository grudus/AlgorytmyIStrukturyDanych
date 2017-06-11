import graph.Graph;

import static java.util.stream.IntStream.range;

public class Lab12 {
    private static final String[] CITIES = {
            "Warszawa", "Wroclaw", "Lodz", "Krakow", "Gdynia", "Zdunska Wola"
    };
    public static void main(String[] args) {
//        Graph<String> graph = new Graph<>();
//        Stream.of(CITIES).forEach(graph::addVertex);
//
//        graph.linkVertex(CITIES[0], CITIES[2], 136);
//        graph.linkVertex(CITIES[0], CITIES[1], 354);
//        graph.linkVertex(CITIES[0], CITIES[3], 295);
//        graph.linkVertex(CITIES[1], CITIES[3], 272);
//
//        graph.printEdges();
//
//        System.out.println("----------------");
//
//        graph.printEdges(CITIES[1]);

        Graph<Integer> graph = new Graph<>();

        graph.linkVertexOneDirection(0, 4, 3);
        graph.linkVertexOneDirection(0, 1, 3);
        graph.linkVertexOneDirection(1, 2, 1);
        graph.linkVertexOneDirection(2, 5, 1);
        graph.linkVertexOneDirection(2, 3, 3);
        graph.linkVertexOneDirection(3, 1, 3);
        graph.linkVertexOneDirection(4, 5, 2);
        graph.linkVertexOneDirection(5, 0, 6);
        graph.linkVertexOneDirection(5, 3, 1);

        graph.printEdges();

        range(0, 6).forEach(i ->
                System.out.printf("Shortest path between 0 and %d: %.2f\n", i, graph.findShortestDistance(0, i)));



    }

}
