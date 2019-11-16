package graph.simple.lib;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AdjacencyListTest {
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
    public void testAddVertex(AdjacencyList<Integer> graph, boolean directed) {
        VertexDescriptor<Integer> vDescriptor = graph.addVertex(1);

        assertEquals(vDescriptor.getVertex(), Integer.valueOf(1));
        assertEquals(graph.getVertices().size(), 1);
        assertEquals(graph.getVertices().get(0).getVertex(), Integer.valueOf(1));
    }

    @Test(dataProvider = "integerGraphProvider")
    public void testAddEdge(AdjacencyList<Integer> graph, boolean directed) {
        VertexDescriptor<Integer> vDesc1 = graph.addVertex(1);
        VertexDescriptor<Integer> vDesc2 = graph.addVertex(2);

        assertEquals(graph.getVertices().size(), 2);

        EdgeDescriptor<Integer> eDesc = graph.addEdge(vDesc1, vDesc2);

        assertEquals(eDesc.isDirected(), directed);
        assertEquals(eDesc.getSource().getVertex(), Integer.valueOf(1));
        assertEquals(eDesc.getTarget().getVertex(), Integer.valueOf(2));

        assertEquals(vDesc1.getOutEdges().size(), 1);
        assertTrue(eDesc == vDesc1.getOutEdges().get(0));

        if (directed) {
            assertEquals(vDesc2.getOutEdges().size(), 0);
        } else {
            assertEquals(vDesc2.getOutEdges().size(), 1);
            assertTrue(eDesc == vDesc2.getOutEdges().get(0));
        }
    }
}