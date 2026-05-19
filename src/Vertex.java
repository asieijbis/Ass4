import java.util.*;

public class Vertex<T> {
    private T data;
    private Map<Vertex<T>, Double> adjacentVertices;
    public Vertex(T data){
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }
    public Map<Vertex<T>, Double> getAdjacentVertices(){
        return adjacentVertices;
    }
    public T getData(){
        return data;
    }
    public double getWeight(Vertex<T> vertex){
        if(!adjacentVertices.containsKey(vertex)){
            throw new IllegalArgumentException("Adjacent vertex does not exist");
        }
        return adjacentVertices.get(vertex);
    }
    public void add(Vertex<T> dest, double weight) {
        adjacentVertices.put(dest, weight);
    }
    public void remove(Vertex<T> dest){
        adjacentVertices.remove(dest);
    }

    public List<T> adjacencyList(){
        List<T> list = new LinkedList<>();

        for(Vertex<T> item : adjacentVertices.keySet()){
            list.add(item.data);
        }
        return list;
    }

    public boolean contains(Vertex<T> vertex){
        return adjacentVertices.containsKey(vertex);
    }
    public int size() {
        return adjacentVertices.size();
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Vertex<?> vertex)) return false;
        return Objects.equals(data, vertex.data);
    }
    @Override
    public int hashCode(){
        return Objects.hash(data);
    }
}