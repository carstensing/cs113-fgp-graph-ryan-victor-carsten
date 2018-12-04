package edu.miracosta.cs113.Graph;

import java.util.ArrayList;
import java.util.Iterator;

public class MatrixGraph extends AbstractGraph{
    double[][] edges;

    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        if (numV > 0) {
            edges = new double[numV][numV];
        }
    }

    /**
     * Created to see a visual representation of the matrix
     * Weights are casted to int
     */
    public void drawGraph()
    {
        for(int i = 0; i < edges.length;i++)
        {
            for(int j = 0; j < edges.length; j++)
            {
                System.out.print((int)edges[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void insert(Edge edge) {


        int dest = edge.getDest();
        int source = edge.getSource();

        //if dest or source int values go past array bounds
        if(dest >= edges.length || source >= edges.length) {
            //Throw an error
        }
        edges[source][dest] = edge.getWeight();

        //Code below makes the matrix symmetrical if undirected graph
        if(!isDirected()) {
            edges[dest][source] = edge.getWeight();
        }

    }

    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source][dest] != Double.POSITIVE_INFINITY;
    }

    @Override
    public Edge getEdge(int source, int dest) {

        //if dest or source int values go past array bounds
        if(dest >= edges.length || source >= edges.length) {
            //Throw an error
        }
        return new Edge(source,dest);
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return new MatrixIterator();
    }

    /**
     * Inner class Iterator
     *
     */
    private class MatrixIterator implements Iterator{

        public MatrixIterator(){

        }
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }

    /**
     * Reads the relationships between vertices from a graph
     * @return  All the connections
     */
    public ArrayList<Edge> readConnections()
    {
        if(isDirected())
        {
            return readDirectedConnections();
        }
        else
        {
            return readUndirectedConnections();
        }

    }

    /**
     * Reads the relationships between vertices from a directed graph
     * @return  All the connections
     */
    private ArrayList<Edge> readDirectedConnections()
    {
        ArrayList<Edge> array = new ArrayList<Edge>();
        for(int i = 0; i < edges.length;i++)
        {
            for(int j = 0; j < edges.length;j++)
            {
                if((int)edges[i][j] == 1)
                {
                    array.add(new Edge(i,j));
                }
            }
        }
        return array;
    }
    /**
     * Reads the relationships between vertices from a undirected graph
     * But only reads half of the matrix
     * @return  half of the connections
     */
    private ArrayList<Edge> readUndirectedConnections()
    {
        ArrayList<Edge> array = new ArrayList<Edge>();
        for(int i = 0; i < edges.length;i++)
        {
            for(int j = 0; j < i;j++)
            {
                if((int)edges[i][j] == 1)
                {
                    array.add(new Edge(i,j));
                }
            }
        }
        return array;
    }

}
