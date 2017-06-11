package graph;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class Vertex<T extends Comparable<T>> {
    private T value;
    private List<Edge<T>> edges;

    public Vertex(T value) {
        this.value = value;
        this.edges = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void removeEdge(Edge<T> edge) {
        edges.remove(edge);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }

    public boolean containsEdge(Edge<T> edge) {
        return edges.contains(edge);
    }

    public void addEdge(Edge<T> edge) {
        edges.add(edge);
    }

    @Override
    public String toString() {
        return format("{%s)", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        return value != null ? value.equals(vertex.value) : vertex.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
