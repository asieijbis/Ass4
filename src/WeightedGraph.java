import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<T, Vertex<T>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }
    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(T data) {
        if (hasVertex(data))
            return;

        map.put(data, new Vertex<>(data));
    }
    public void addEdge(T source, T dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return;

        Vertex<T> sourceVertex = map.get(source);
        Vertex<T> destVertex = map.get(dest);

        sourceVertex.add(destVertex, weight);

        if (undirected)
            destVertex.add(sourceVertex, weight);
    }
    public int getVerticesCount() {
        return map.size();
    }
    public int getEdgesCount() {
        int count = 0;
        for (Vertex<T> v : map.values()) {
            count += v.getAdjacentVertices().size();
        }
        if (undirected) count /= 2;
        return count;
    }
    public boolean hasVertex(T v) {
        return map.containsKey(v);
    }
    public boolean hasEdge(T source, T dest) {
        if (!hasVertex(source) || !hasVertex(dest)) return false;

        Vertex<T> sourceVertex = map.get(source);
        Vertex<T> destVertex = map.get(dest);
        return sourceVertex.getAdjacentVertices().containsKey(destVertex);
    }
    public List<T> adjacencyList(T data) {
        if (!hasVertex(data)) return null;

        List<T> vertices = new LinkedList<>();
        for (Vertex<T> neighbor : map.get(data).getAdjacentVertices().keySet()) {
            vertices.add(neighbor.getData());
        }

        return vertices;
    }
    public double getWeight(T source, T dest) {
        if (!hasVertex(source) || !hasVertex(dest)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        Vertex<T> sourceVertex = map.get(source);
        Vertex<T> destVertex = map.get(dest);

        if (!sourceVertex.getAdjacentVertices().containsKey(destVertex)) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        return sourceVertex.getAdjacentVertices().get(destVertex);
    }
    public Vertex<T> getVertex(T data) {
        return map.get(data);
    }
    public Set<T> getVertices() {
        return map.keySet();
    }
    public Map<Vertex<T>, Double> getAdjacentVertices(T data) {
        if (!hasVertex(data)) {
            return null;
        }

        return map.get(data).getAdjacentVertices();
    }
    public Iterable<Map.Entry<Vertex<T>, Double>> getEdges(T v) {
        if (!hasVertex(v)) return null;

        return map.get(v).getAdjacentVertices().entrySet();
    }
}