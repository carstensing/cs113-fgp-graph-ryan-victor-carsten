package edu.miracosta.cs113.Graph;

import java.util.Iterator;

public class MatrixGraph extends AbstractGraph{
    double[][] edges;

    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        if (numV > 0) {
            edges = new double[numV][numV];
        }
        for (int row = 0; row < numV; row ++) {
            for (int column = 0; column < numV; column ++) {
                edges[row][column] = Double.POSITIVE_INFINITY;
            }
        }
    }

    @Override
    public void insert(Edge edge) {
        if (isDirected()) {
            insertDirected(edge);
        } else {
            insertUndirected(edge);
        }
    }

    private void insertDirected(Edge edge) {
        if (isEdgeInBounds(edge)) {
            edges[edge.getSource()][edge.getDest()] = edge.getWeight();
        }
    }

    private void insertUndirected(Edge edge) {
        if (isEdgeInBounds(edge)) {
            edges[edge.getSource()][edge.getDest()] = edge.getWeight();
            edges[edge.getDest()][edge.getSource()] = edge.getWeight();
        }
    }

    private boolean isEdgeInBounds(Edge edge) {
        return edge.getSource() < getNumV() && edge.getDest() < getNumV();
    }

    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source][dest] != Double.POSITIVE_INFINITY;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        if (source < getNumV() && dest < getNumV()) {
            if (edges[source][dest] != Double.POSITIVE_INFINITY) {
                return new Edge(source, dest, edges[source][dest]);
            }
        }
        return null;

    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return null;
    }
}
