package graph.simple.lib;

public abstract class GraphFactory {
    private GraphFactory() {
    }

    public static <Vertex> AdjacencyList<Vertex> createDirectedAdjacencyGraph() {
        return new DirectedAdjacencyList<>();
    }

    public static <Vertex> AdjacencyList<Vertex> createUndirectedAdjacencyGraph() {
        return new UndirectedAdjacencyList<Vertex>();
    }
}
