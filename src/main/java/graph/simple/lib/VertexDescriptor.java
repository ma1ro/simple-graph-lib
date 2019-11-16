package graph.simple.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VertexDescriptor<Vertex> {
    private Vertex v;
    private List<EdgeDescriptor<Vertex>> outEdges = new ArrayList<>();

    VertexDescriptor(Vertex v) {
        this.v = v;
    }

    public Vertex getVertex() {
        return v;
    }

    void addOutEdge(EdgeDescriptor<Vertex> edge) {
        this.outEdges.add(edge);
    }

    public List<EdgeDescriptor<Vertex>> getOutEdges() {
        return Collections.unmodifiableList(outEdges);
    }
}
