package edu.miracosta.cs113.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class MatrixGraph extends AbstractGraph{
    double[][] edges;

    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        if (numV > 0) {
            edges = new double[numV][numV];
        }
        /**
         * Filling in array with Positive Infinitive
         */
        for(int i = 0; i < numV; i++)
        {
            for(int j = 0;j < numV; j++)
            {
                edges[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    /**
     * Created to see a visual representation of the matrix
     * Weights are casted to int
     */
    public void drawGraph()
    {

        System.out.println("Zeros represent Positive Infinity");
        for(int i = 0; i < edges.length;i++)
        {

            for(int j = 0; j < edges.length; j++)
            {
                if(edges[i][j] == Double.POSITIVE_INFINITY)
                {
                    System.out.print("0\t");
                }
                else
                {
                    System.out.print((int)edges[i][j] + "\t");
                }

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
        return new Edge(source,dest,edges[source][dest]);
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
                if(edges[i][j] > 0)
                {
                    array.add(new Edge(i,j,edges[i][j]));
                }
            }
        }
        return array;
    }
    /**
     * Reads the relationships between vertices from a undirected graph
     * But only reads half of the matrix and creates an array of all the edges
     * @return  half of the connections
     */
    private ArrayList<Edge> readUndirectedConnections()
    {
        ArrayList<Edge> array = new ArrayList<Edge>();
        for(int i = 0; i < edges.length;i++)
        {
            for(int j = 0; j < i;j++)
            {
                if(edges[i][j] > 0)
                {
                    array.add(new Edge(i,j,edges[i][j]));
                }
            }
        }
        return array;
    }

    /**
     * This runs dijkstras algorithm
     * @param start     Staring vertex where all paths will be based from
     * @param pred      An array that stores int references to a vertex from another
     * @param dist      The Total distance from a vertex to the starting vertex
     */
    public void dijkstrasAlgorith(int start,int[] pred,double[] dist)
    {
        int numV = getNumV();
        HashSet<Integer> vMinuesS = new HashSet<Integer>(numV);
        //Initialize V-S
        for(int i = 0;i<numV;i++ )
        {
            if(i != start)
            {
                vMinuesS.add(i);
            }
        }
        //Initialize pred and dist
        for(int vertices:vMinuesS)
        {
            pred[vertices] = start;

            //Not sure if this line of code should be here
            //Because it is assuming the start is connected to every vertex
            dist[vertices] = getEdge(start,vertices).getWeight();
        }

        //Main loop
        while(vMinuesS.size() != 0)
        {
            //find value u in V-S with the smallest dist[u]
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;



            for(int vertex : vMinuesS)
            {

                if(dist[vertex] < minDist)
                {
                    minDist = dist[vertex];
                    u = vertex;

                }
            }
            //Remove u from v minus
            vMinuesS.remove(u);
            //update the distance
            for(int vertex: vMinuesS)
            {
                if(isEdge(u,vertex))
                {
                    double weight = getEdge(u,vertex).getWeight();
                    if(dist[u] + weight < dist[vertex])
                    {


                        dist[vertex] = dist[u] + weight;
                        pred[vertex] = u;
                    }
                }
            }
        }
    }

    /**
     * In order to run this method Dikstras must be ran first to obtain all paths fom a vertex
     * What this method does is simply backtrack dijkstra from the end vertex you want to reach to your current location
     * @param start     Staring point or vertex
     * @param end       The target location where you want to be
     * @param pred      The array that holds the way to get to start to end
     * @return          an array that has the path to get to your single end point
     */
    public int[] getSingleShortestPath(int start, int end,int[] pred)
    {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(end);
        while(end != start)
        {
            //Pushing te predecessor of the end where you want to go
            //And works backwards
            stack.push(end);
            end = pred[end];
        }
        int[] array = new int[stack.size()-1];
        for(int i = 0; i < stack.size()+1;i++)
        {
            array[i] = stack.pop();
        }
        return array;
    }

}
