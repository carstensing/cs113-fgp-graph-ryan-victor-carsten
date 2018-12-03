package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.Edge;
import edu.miracosta.cs113.Graph.MatrixGraph;

public class Driver {

        public static void main(String[] args){
            MatrixGraph graph = new MatrixGraph(4,true);

            graph.insert(new Edge(0,1));
            graph.drawGraph();
        }
}
