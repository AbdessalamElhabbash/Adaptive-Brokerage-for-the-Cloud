package com.lancaster.dsl.SLO_DSL;

import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class AbstractWeightedGraph {
    
	static class Edge {
		Component source;
		Component destination;
        double weight;

        public Edge(Component source, Component destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class AbstractGraph {
    	ArrayList<Component> vertices;
        Map<String,LinkedList<Edge>> adjacencyMap;

        AbstractGraph(ArrayList<Component> vertices) {
            this.vertices = vertices;
            adjacencyMap = new LinkedHashMap<>();
            //initialise adjacency maps for all the vertices
            for (Component vertics : vertices) {
                adjacencyMap.put(vertics.getName(), new LinkedList<>());
            }
        }

        public void addEgde(Component source, Component destination, double weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencyMap.get(source.getName()).addFirst(edge); //for directed graph
        }

        public double getWeightsSum(){
        	double sum= 0;
        	for (Component vertex : vertices) {
                LinkedList<Edge> list = adjacencyMap.get(vertex.getName());
                for (int j = 0; j <list.size() ; j++) {
                    sum += list.get(j).weight;
                }
            }
            return sum;
        }
        
        public void printGraph(){
        	for (Component vertex : vertices) {
                LinkedList<Edge> list = adjacencyMap.get(vertex.getName());
                for (int j = 0; j <list.size() ; j++) {
                    System.out.println("vertex-" + vertex.getName() + " is connected to " +
                            list.get(j).destination.getName() + " with weight " +  list.get(j).weight);
                }
            }
        }
    }
      public static void main(String[] args) {
            
            ArrayList<Component> vertices = new ArrayList<>();
            Component azureCompute = new Component();
            azureCompute.setName("azure_compute");
            Component awsCompute = new Component();
            awsCompute.setName("aws_compute");
            vertices.add(azureCompute);
            vertices.add(awsCompute);
            
            AbstractGraph graph = new AbstractGraph(vertices);
            graph.addEgde(azureCompute, awsCompute, 4);
            graph.addEgde(awsCompute, azureCompute, 3);
            
            graph.printGraph();
            System.out.println(graph.getWeightsSum());
        }
}

