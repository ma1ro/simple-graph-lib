package graph.simple.lib;

public class EdgeDescriptor<Vertex> {
    private boolean isDirected;
    private VertexDescriptor<Vertex> source;
    private VertexDescriptor<Vertex> target;

    EdgeDescriptor(boolean isDirected, VertexDescriptor<Vertex> source, VertexDescriptor<Vertex> target) {
        this.isDirected = isDirected;
        this.source = source;
        this.target = target;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public VertexDescriptor<Vertex> getSource() {
        return source;
    }

    public VertexDescriptor<Vertex> getTarget() {
        return target;
    }
}
