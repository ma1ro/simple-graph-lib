package graph.simple.lib;

class UndirectedAdjacencyList<Vertex> extends DirectedAdjacencyList<Vertex> {
    @Override
    public EdgeDescriptor<Vertex> addEdge(VertexDescriptor<Vertex> v1, VertexDescriptor<Vertex> v2) {
        EdgeDescriptor<Vertex> eDesc = new EdgeDescriptor<>(false, v1, v2);
        v1.addOutEdge(eDesc);
        v2.addOutEdge(eDesc);
        return eDesc;
    }
}
