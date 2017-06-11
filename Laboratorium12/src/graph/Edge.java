package graph;

import static java.lang.String.format;

@SuppressWarnings("unused")
public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {
    static final double DEFAULT_WEIGHT = 1.0;

    private Vertex<T> start;
    private Vertex<T> end;
    private double weight;

    public Edge(Vertex<T> start, Vertex<T> end) {
        this(start, end, DEFAULT_WEIGHT);
    }

    Edge(Vertex<T> start, Vertex<T> end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    boolean doesLinkOneDirection(T start, T end) {
        return this.start.getValue().equals(start) && this.end.getValue().equals(end);
    }


    boolean doesLink(T start, T end) {
        return doesLinkOneDirection(start, end) || doesLinkOneDirection(end, start);
    }

    Vertex<T> getStart() {
        return start;
    }

    void setStart(Vertex<T> start) {
        this.start = start;
    }

    Vertex<T> getEnd() {
        return end;
    }

    void setEnd(Vertex<T> end) {
        this.end = end;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return weight == DEFAULT_WEIGHT
                ? format("%s ----> %s", start, end)
                : format("%s --%.2f--> %s)", start, weight, end);
    }

    @Override
    public int compareTo(Edge<T> o) {
        return Double.compare(weight, o.getWeight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;
        return edge.start.equals(this.start) && edge.end.equals(this.end);
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
