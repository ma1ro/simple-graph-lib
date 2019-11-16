package graph.simple.lib;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class AlgorithmsTest {
    /**
     * @return pairs {Graph instance, boolean directed}
     */
    @DataProvider
    public Object[][] integerGraphProvider() {
        return new Object[][]{
                {new DirectedAdjacencyList<Integer>(), true},
                {new UndirectedAdjacencyList<Integer>(), false}
        };
    }

    @Test(dataProvider = "integerGraphProvider")
    public void testGetPath_singleVertex_noEdge(AdjacencyList<Integer> graph, boolean directed) {
        VertexDescriptor<Integer> vDesc = graph.addVertex(1);

        List<EdgeDescriptor<Integer>> path = Algorithms.getPath(vDesc, vDesc);
        assertEquals(path.size(), 0);
    }

    @Test(dataProvider = "integerGraphProvider")
    public void testGetPath_singleVertex_loop(AdjacencyList<Integer> graph, boolean directed) {
        VertexDescriptor<Integer> vDesc = graph.addVertex(1);
        EdgeDescriptor<Integer> eDesc = graph.addEdge(vDesc, vDesc);

        List<EdgeDescriptor<Integer>> path = Algorithms.getPath(vDesc, vDesc);
        assertEquals(path.size(), 1);
        assertEquals(path.get(0), eDesc);
    }

    @Test(dataProvider = "integerGraphProvider")
    public void testGetPath_singleVertex_twoLoops(AdjacencyList<Integer> graph, boolean directed) {
        VertexDescriptor<Integer> vDesc = graph.addVertex(1);
        graph.addEdge(vDesc, vDesc);
        graph.addEdge(vDesc, vDesc);

        List<EdgeDescriptor<Integer>> path = Algorithms.getPath(vDesc, vDesc);
        assertEquals(path.size(), 1);
    }

    @Test(dataProvider = "integerGraphProvider")
    public void testGetPath_twoVertices_noEdges(AdjacencyList<Integer> graph, boolean directed) {
        VertexDescriptor<Integer> vDesc1 = graph.addVertex(1);
        VertexDescriptor<Integer> vDesc2 = graph.addVertex(2);

        List<EdgeDescriptor<Integer>> path = Algorithms.getPath(vDesc1, vDesc2);
        assertEquals(path.size(), 0);
    }

    @Test(dataProvider = "integerGraphProvider")
    public void testGetPath_manyVertices(AdjacencyList<Integer> graph, boolean directed) {
        /*
         _
        |/
        1 -> 2 -> 3 -> 4
         \-> 5 ------->/
         */

        VertexDescriptor<Integer> vDesc1 = graph.addVertex(1);
        VertexDescriptor<Integer> vDesc2 = graph.addVertex(2);
        VertexDescriptor<Integer> vDesc3 = graph.addVertex(3);
        VertexDescriptor<Integer> vDesc4 = graph.addVertex(4);
        VertexDescriptor<Integer> vDesc5 = graph.addVertex(5);

        graph.addEdge(vDesc1, vDesc1);
        graph.addEdge(vDesc1, vDesc2);
        graph.addEdge(vDesc2, vDesc3);
        graph.addEdge(vDesc3, vDesc4);
        EdgeDescriptor<Integer> edge_1_5 = graph.addEdge(vDesc1, vDesc5);
        EdgeDescriptor<Integer> edge_5_4 = graph.addEdge(vDesc5, vDesc4);

        List<EdgeDescriptor<Integer>> path = Algorithms.getPath(vDesc1, vDesc4);
        assertEquals(path.size(), 2);
        assertEquals(path.get(0), edge_1_5);
        assertEquals(path.get(1), edge_5_4);
    }

    @Test(dataProvider = "integerGraphProvider")
    public void testGetPath_manyVertices_inverseShortest(AdjacencyList<Integer> graph, boolean directed) {
        /*
         _
        |/
        1 -> 2 -> 3 -> 4
         \-> 5 <-------/
         */

        VertexDescriptor<Integer> vDesc1 = graph.addVertex(1);
        VertexDescriptor<Integer> vDesc2 = graph.addVertex(2);
        VertexDescriptor<Integer> vDesc3 = graph.addVertex(3);
        VertexDescriptor<Integer> vDesc4 = graph.addVertex(4);
        VertexDescriptor<Integer> vDesc5 = graph.addVertex(5);

        graph.addEdge(vDesc1, vDesc1);
        EdgeDescriptor<Integer> edge_1_2 = graph.addEdge(vDesc1, vDesc2);
        EdgeDescriptor<Integer> edge_2_3 = graph.addEdge(vDesc2, vDesc3);
        EdgeDescriptor<Integer> edge_3_4 = graph.addEdge(vDesc3, vDesc4);
        EdgeDescriptor<Integer> edge_1_5 = graph.addEdge(vDesc1, vDesc5);
        EdgeDescriptor<Integer> edge_4_5 = graph.addEdge(vDesc4, vDesc5);

        List<EdgeDescriptor<Integer>> path = Algorithms.getPath(vDesc1, vDesc4);

        if (directed) {
            assertEquals(path.size(), 3);
            assertEquals(path.get(0), edge_1_2);
            assertEquals(path.get(1), edge_2_3);
            assertEquals(path.get(2), edge_3_4);
        } else {
            assertEquals(path.size(), 2);
            assertEquals(path.get(0), edge_1_5);
            assertEquals(path.get(1), edge_4_5);
        }
    }
}