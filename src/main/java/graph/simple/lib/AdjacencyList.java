package graph.simple.lib;

import java.util.List;


public interface AdjacencyList<Vertex> {
    VertexDescriptor<Vertex> addVertex(Vertex v);

    EdgeDescriptor<Vertex> addEdge(VertexDescriptor<Vertex> v1, VertexDescriptor<Vertex> v2);

    List<VertexDescriptor<Vertex>> getVertices();
}
