import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private final Set<T> unsettledNodes;
    private final Map<T, Double> distances;
    private final WeightedGraph<T> graph;

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        super(source);
        this.unsettledNodes = new HashSet<>();
        this.distances = new HashMap<>();
        this.graph = graph;

        dijkstra();
    }
    public void dijkstra() {
        distances.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            T currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (T neighbor : graph.adjacencyList(currentNode)) {
                double newDistance = getShortestDistance(currentNode) + getDistance(currentNode, neighbor);

                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode);
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }
    private double getDistance(T node, T target) {
        return graph.getWeight(node, target);
    }
    private T getVertexWithMinimumWeight(Set<T> vertices) {
        T minimum = null;
        for (T vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;

                continue;
            }
            if (getShortestDistance(vertex) < getShortestDistance(minimum))
                minimum = vertex;
        }
        return minimum;
    }
    private double getShortestDistance(T destination) {
        Double d = distances.get(destination);

        return (d == null ? Double.MAX_VALUE : d);
    }
}