package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.Edge;
import edu.miracosta.cs113.Graph.MatrixGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class GraphTest {

    /**Generating Adjacency matrix representation of graph */
    private MatrixGraph graph;


    @Test
    public void testInsertionDirected()
    {
        //Creating directed graph with three vertices
        graph = new MatrixGraph(3,true);

        Edge expected = new Edge(2,2,120);
        graph.insert(expected);

        Edge actual = graph.getEdge(2,2);

        assertEquals("Expected Edge does not match actual edge",actual,expected);


    }
    @Test
    public void tesInsertionUndirected()
    {
        graph = new MatrixGraph(5,false);

        Edge original = new Edge(2,4,120);
        graph.insert(original);

        //There should be an edge here because of Graph symmetry
        Edge actual = graph.getEdge(4,2);

        //Creating the expected edge
        Edge expected = new Edge(4,2,120);
        assertEquals("Expected Edge does not match actual edge",actual,expected);

    }

}
