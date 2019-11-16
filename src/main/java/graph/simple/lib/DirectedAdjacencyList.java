package graph.simple.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple graph implementation which uses adjacency lists. Allows loops and parallel edges.
 *
 * @param <Vertex>
 */
class DirectedAdjacencyList<Vertex> implements AdjacencyList<Vertex> {
    private List<VertexDescriptor<Vertex>> vertices = new ArrayList<>();

    @Override
    public VertexDescriptor<Vertex> addVertex(Vertex v) {
        VertexDescriptor<Vertex> vDesc = new VertexDescriptor<>(v);
        vertices.add(vDesc);
        return vDesc;
    }

    @Override
    public EdgeDescriptor<Vertex> addEdge(VertexDescriptor<Vertex> v1, VertexDescriptor<Vertex> v2) {
        EdgeDescriptor<Vertex> eDesc = new EdgeDescriptor<>(true, v1, v2);
        v1.addOutEdge(eDesc);
        return eDesc;
    }

    @Override
    public List<VertexDescriptor<Vertex>> getVertices() {
        return Collections.unmodifiableList(vertices);
    }
}
