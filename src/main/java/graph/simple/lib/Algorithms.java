package graph.simple.lib;

import java.util.*;

public abstract class Algorithms {
    private Algorithms() {
    }

    /**
     * Algorithm implements BFS and returns the shortest path between vFirst and vLast.
     * During BFS phase algorithm builds tree structure to track visited vertices and shortest paths to them.
     * If vFirst is equal to vLast than the path exists only if there is a loop edge from vFirst.
     *
     * Complexity: O(V+E). Memory: O(V+E).
     *
     * @param vFirst first vertex
     * @param vLast last vertex
     * @return Edges between two vertices.
     */
    public static <Vertex> List<EdgeDescriptor<Vertex>> getPath(final VertexDescriptor<Vertex> vFirst, final VertexDescriptor<Vertex> vLast) {
        Objects.requireNonNull(vFirst);
        Objects.requireNonNull(vLast);

        final Queue<VertexDescriptor<Vertex>> vertexQueue = new LinkedList<>();
        vertexQueue.add(vFirst);
        // Contains pairs of {Vertex, in-edge} which allows efficient traverse from leaf to root.
        final Map<VertexDescriptor<Vertex>, EdgeDescriptor<Vertex>> visitedTree = new HashMap<>();

        // Build visitedTree from vFirst.
        while (!vertexQueue.isEmpty()) {
            final VertexDescriptor<Vertex> vi = vertexQueue.poll();
            for (final EdgeDescriptor<Vertex> ei : vi.getOutEdges()) {
                final VertexDescriptor<Vertex> vTarget = ei.isDirected() || ei.getSource().equals(vi) ? ei.getTarget() : ei.getSource();
                if (!visitedTree.containsKey(vTarget)) {
                    visitedTree.put(vTarget, ei);
                    if (vTarget.equals(vLast)) {
                        vertexQueue.clear();
                        break;
                    } else {
                        vertexQueue.add(vTarget);
                    }
                }
            }
        }

        final LinkedList<EdgeDescriptor<Vertex>> result = new LinkedList<>();

        if (visitedTree.containsKey(vLast)) {
            // Path exists. Traverse back the visitedTree from vLast to vFirst .
            VertexDescriptor<Vertex> vi = vLast;
            do {
                final EdgeDescriptor<Vertex> ei = visitedTree.get(vi);
                result.addFirst(ei);
                vi = ei.isDirected() || ei.getTarget().equals(vi) ? ei.getSource() : ei.getTarget();
            } while (!vi.equals(vFirst));
        }

        return result;
    }
}
