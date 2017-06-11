package graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static graph.Edge.DEFAULT_WEIGHT;
import static java.util.Comparator.comparing;

@SuppressWarnings({"unused", "WeakerAccess", "SameParameterValue"})
public class Graph<T extends Comparable<T>> {
    private Set<Vertex<T>> vertices;
    private Set<Edge<T>> edges;

    public Graph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public Vertex<T> addVertex(T elem) {
        Vertex<T> vertex = new Vertex<>(elem);
        if (vertices.contains(vertex))
            throw new IllegalStateException("Graph already contains " + elem);
        vertices.add(vertex);
        return vertex;
    }

    public void removeLinkOneDirection(T startValue, T endValue) {
        Vertex<T> start = findVertex(startValue)
                .orElseThrow(() -> new IllegalArgumentException("Graph doesn't contains " + startValue));
        Vertex<T> end = findVertex(endValue)
                .orElseThrow(() -> new IllegalArgumentException("Graph doesn't contains " + endValue));

        removeLink(startValue, endValue);
    }


    public void removeLink(T startValue, T endValue) {
        Vertex<T> start = findVertex(startValue)
                .orElseThrow(() -> new IllegalArgumentException("Graph doesn't contains " + startValue));
        Vertex<T> end = findVertex(endValue)
                .orElseThrow(() -> new IllegalArgumentException("Graph doesn't contains " + endValue));

        removeLink(start, end);
        removeLink(end, start);
    }

    public void removeVertex(T elem) {
        Vertex<T> vertex = findVertex(elem).orElse(null);

        if (vertex != null) {
            vertices.remove(vertex);
            edges.removeAll(vertex.getEdges());
        }
    }

    public void linkVertexOneDirection(T startValue, T endValue) {
        linkVertexOneDirection(startValue, endValue, DEFAULT_WEIGHT);
    }

    public void linkVertexOneDirection(T startValue, T endValue, double weight) {
        Vertex<T> start = findVertex(startValue).orElseGet(() -> addVertex(startValue));
        Vertex<T> end = findVertex(endValue).orElseGet(() -> addVertex(endValue));
        Edge<T> edge = new Edge<>(start, end, weight);

        start.addEdge(edge);
        edges.add(edge);
    }


    public void linkVertex(T startValue, T endValue) {
        linkVertex(startValue, endValue, DEFAULT_WEIGHT);
    }


    public void linkVertex(T startValue, T endValue, double weight) {
        Vertex<T> start = findVertex(startValue).orElseGet(() -> addVertex(startValue));
        Vertex<T> end = findVertex(endValue).orElseGet(() -> addVertex(endValue));
        Edge<T> edge1 = new Edge<>(start, end, weight);
        Edge<T> edge2 = new Edge<>(end, start, weight);

        start.addEdge(edge1);
        end.addEdge(edge2);
        edges.add(edge1);
        edges.add(edge2);
    }

    public void printEdges() {
        edges.stream().sorted(comparing(e -> e.getStart().getValue()))

                .forEach(System.out::println);
    }

    public void printVertices() {
        vertices.forEach(System.out::println);
    }


    private void removeLink(Vertex<T> start, Vertex<T> end) {
        Edge<T> edge = edges.stream()
                .filter(e -> e.doesLinkOneDirection(start.getValue(), end.getValue()))
                .findAny().orElse(null);

        if (edge != null) {
            edges.remove(edge);
            start.removeEdge(edge);
        }
    }

    private Optional<Vertex<T>> findVertex(T val) {
        return vertices.stream()
                .filter(v -> v.getValue().equals(val))
                .findFirst();
    }

}


