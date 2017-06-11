package graph;

import java.util.*;

import static graph.Edge.DEFAULT_WEIGHT;
import static java.lang.Double.MAX_VALUE;
import static java.util.AbstractMap.Entry;
import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

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

    public void printEdges(T elem) {
        findEdges(elem).forEach(System.out::println);
    }

    public List<Edge<T>> findEdges(T value) {
        return findVertex(value).map(Vertex::getEdges).orElse(emptyList());
    }

    public double findShortestDistance(T start, T end) {
        return findShortestDistance(start).get(end);
    }

    public Map<T, Double> findShortestDistance(T start) {
        Map<Vertex<T>, Double> vertexToDistance = vertices.stream()
                .collect(toMap(identity(), v -> v.getValue().equals(start) ? 0 : MAX_VALUE));
        Map<Vertex<T>, Double> distances = new HashMap<>(vertices.size());

        while (!vertexToDistance.isEmpty()) {
            Vertex<T> shortest = findShortest(vertexToDistance);
            double currentWeight = vertexToDistance.get(shortest);
            vertexToDistance.remove(shortest);
            distances.put(shortest, currentWeight);

            calculateNeighbours(shortest, currentWeight, vertexToDistance, distances);
        }


        return distances.entrySet().stream()
                .collect(toMap(e -> e.getKey().getValue(), Entry::getValue));
    }

    private void calculateNeighbours(Vertex<T> shortest, double weight, Map<Vertex<T>, Double> vertexToDistance, Map<Vertex<T>, Double> distances) {
        for (Edge<T> edge : shortest.getEdges()) {
            Vertex<T> neighbour = edge.getEnd();
            if (vertexToDistance.containsKey(neighbour)) {
                double neighbourDistance = vertexToDistance.get(neighbour);
                if (neighbourDistance > weight + edge.getWeight()) {
                    vertexToDistance.put(neighbour, weight + edge.getWeight());
                }
            }
        }
    }

    private Vertex<T> findShortest(Map<Vertex<T>, Double> vertexToDistance) {
        double shortestDistance = MAX_VALUE;
        Vertex<T> shortest = null;
        for (Entry<Vertex<T>, Double> entry : vertexToDistance.entrySet()) {
            if (entry.getValue() <= shortestDistance) {
                shortestDistance = entry.getValue();
                shortest = entry.getKey();
            }
        }
        return shortest;
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


