package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.Edge;
import edu.miracosta.cs113.Graph.MatrixGraph;

public class Driver {

        public static void main(String[] args){
            MatrixGraph graph = new MatrixGraph(5,true);

            int[] pred = new int[5];
            double[] dist = new double[5];

            graph.insert(new Edge(0,1,10));
            graph.insert(new Edge(0,3,30));
            graph.insert(new Edge(0,4,100));
            graph.insert(new Edge(1,2,50));
            graph.insert(new Edge(2,4,10));
            graph.insert(new Edge(3,2,20));
            graph.insert(new Edge(3,4,60));


            graph.dijkstrasAlgorith(0,pred,dist);


            for(int i = 0; i <pred.length; i++)
            {
                System.out.println(""+(i) +" " + pred[i]);
            }

        }
}
