import graph.Graph;

import java.util.stream.Stream;

public class Lab12 {
    private static final String[] CITIES = {
            "Warszawa", "Wroclaw", "Lodz", "Krakow", "Gdynia", "Zdunska Wola"
    };
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        Stream.of(CITIES).forEach(graph::addVertex);

        graph.linkVertexOneDirection(CITIES[0], CITIES[2], 136);
        graph.linkVertexOneDirection(CITIES[0], CITIES[1], 354);
        graph.linkVertexOneDirection(CITIES[0], CITIES[3], 295);
        graph.linkVertexOneDirection(CITIES[3], CITIES[0], 295);
        graph.linkVertexOneDirection(CITIES[1], CITIES[3], 272);
        graph.linkVertex(CITIES[4], CITIES[3], 111);

        graph.printEdges();

        System.out.println("----------------");

        graph.printEdges(CITIES[1]);

        System.out.println(graph.findShortestDistance(CITIES[1]));
    }

}
