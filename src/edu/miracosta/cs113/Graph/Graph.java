package edu.miracosta.cs113.Graph;

import java.util.Iterator;

public interface Graph {


    /**
     * Accessor for number of vertices
     *
     * @return int numV
     */
    int getNumV();

    /**
     * Accessor for directed
     *
     * @return true if directed, else false
     */
    boolean isDirected();

    /**
     * Inserts an edge object into the graph
     * @param edge edge to be inserted
     */
    void insert(Edge edge);

    /**
     * Checks to see if a specific edge exist in the graph
     * @param source    starting vertex
     * @param dest      ending vertex
     * @return          returns true if the edge exist
     */
    boolean isEdge(int source, int dest);

    /**
     * Returns the edge from a starting point and end point
     * @param source    starting point
     * @param dest      ending point
     * @return          Returns the edge
     */
    Edge getEdge(int source, int dest);

    /**
     * Returns an iterator over the graph
     * @param source    starting point of iterator
     * @return          returns an iterator
     */
    Iterator<Edge> edgeIterator(int source);

}
