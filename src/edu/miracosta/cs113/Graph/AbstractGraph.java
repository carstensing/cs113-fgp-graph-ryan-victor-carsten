package edu.miracosta.cs113.Graph;

import java.util.Iterator;

public abstract class AbstractGraph implements Graph {

    private boolean directed;
    private int numV;

    /**
     * Default Constructor
     */
    public AbstractGraph() {
        this.directed = false;
        this.numV = 0;
    }

    /**
     * Full Constructor
     *
     * @param numV Number of vertices contained within this Graph
     * @param directed boolean if Graph is directed or not
     */
    public AbstractGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public int getNumV() {
        return numV;
    }

    @Override
    public abstract void insert(Edge edge);

    @Override
    public abstract boolean isEdge(int source, int dest);

    @Override
    public abstract Edge getEdge(int source, int dest);

    @Override
    public abstract Iterator<Edge> edgeIterator(int source);
}
