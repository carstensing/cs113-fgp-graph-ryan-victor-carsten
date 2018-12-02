package edu.miracosta.cs113.Graph;

import java.util.Iterator;

public class MatrixGraph extends AbstractGraph{
    double[][] edges;

    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        if (numV > 0) {
            edges = new double[numV][numV];
        }
    }

    @Override
    public void insert(Edge edge) {

    }

    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source][dest] != Double.POSITIVE_INFINITY;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        return null;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return null;
    }
}
