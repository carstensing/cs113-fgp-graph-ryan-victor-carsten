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


    private void setUp()
    {

        //Making a directed Graph with five vertices (From the slides)
        graph = new MatrixGraph(5,true);
        graph.insert(new Edge(0,1,10));
        graph.insert(new Edge(0,3,30));
        graph.insert(new Edge(0,4,100));
        graph.insert(new Edge(1,2,50));
        graph.insert(new Edge(2,4,10));
        graph.insert(new Edge(3,2,20));
        graph.insert(new Edge(3,4,60));
    }

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
    @Test
    public void testingDijkstras()
    {
        setUp();
        int[] pred = new int[5];
        double[] dist = new double[5];

        //Starting point and p[v] and d[v]
        graph.dijkstrasAlgorithm(0,pred,dist);

        //Getting values from the slides
        double[] expectedDistanceValues = {0,10.0,50.0,30.0,60.0};
        int[] expectedPredecesors = {0,0,3,0,2};

        //Starting from one to ignore the starting position
        for(int i = 1; i < pred.length;i++ )
        {
            double actual = dist[i];
            double expected = expectedDistanceValues[i];

            //Comparing wieghts from starting vertex to all vertices
            assertTrue(actual==expected);

            int actualPred = pred[i];
            int expectedPred = expectedPredecesors[i];

            assertEquals("Predecessors do not match",expectedPred,actualPred);

        }

    }

    @Test
    public void testingGetPath()
    {
        setUp();
        int[] actual = graph.getPath(0,4);
        int[] expected = {3,2,4};

        for(int i = 1; i < expected.length;i++ )
        {

            int actualPath = actual[i];
            int expectedPath = expected[i];

            assertEquals("Predecessors do not match",expectedPath,actualPath);

        }


    }


}
