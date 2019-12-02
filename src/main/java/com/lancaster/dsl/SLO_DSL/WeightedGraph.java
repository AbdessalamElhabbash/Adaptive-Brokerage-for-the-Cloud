package com.lancaster.dsl.SLO_DSL;

import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class WeightedGraph {
    
	static class Edge {
		Service source;
		Service destination;
        double weight;

        public Edge(Service source, Service destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
    	ArrayList<Service> vertices;
        Map<String,LinkedList<Edge>> adjacencyMap;

        public ArrayList<Service> getVertices(){
        	return vertices;
        }
        
        public Service getVertex(String componentName){
        	for(Service vertex : vertices){
        		if(vertex.getComponentName().equals(componentName)){
        			return vertex;
        		}
        	}
        	return null;
        }
        
        Graph(ArrayList<Service> vertices) {
            this.vertices = vertices;
            adjacencyMap = new LinkedHashMap<>();
            //initialise adjacency maps for all the vertices
            for (Service vertex : vertices) {
                adjacencyMap.put(vertex.getName(), new LinkedList<>());
            }
        }
        
        public void addVertex(Service vertex) {
        	vertices.add(vertex);
        	adjacencyMap.put(vertex.getName(), new LinkedList<>()); 
        }

        public void addEgde(Service source, Service destination, double weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencyMap.get(source.getName()).addFirst(edge); //for directed graph
        }

        public double getWeightsSum(){
        	double sum= 0;
        	for (Service vertex : vertices) {
                LinkedList<Edge> list = adjacencyMap.get(vertex.getName());
                for (int j = 0; j <list.size() ; j++) {
                    sum += list.get(j).weight;
                }
            }
            return sum;
        }
        
        public void printGraph(){
        	for (Service vertex : vertices) {
                LinkedList<Edge> list = adjacencyMap.get(vertex.getName());
                for (int j = 0; j <list.size() ; j++) {
                    System.out.println("vertex-" + vertex.getName() + " is connected to " +
                            list.get(j).destination.getName() + " with weight " +  list.get(j).weight);
                }
            }
        }
    }
      public static void main(String[] args) {
            
            ArrayList<Service> vertices = new ArrayList<>();
            Service azureCompute = new Service();
            azureCompute.setName("azure_compute");
            Service awsCompute = new Service();
            awsCompute.setName("aws_compute");
            vertices.add(azureCompute);
            vertices.add(awsCompute);
            
            Graph graph = new Graph(vertices);
            graph.addEgde(azureCompute, awsCompute, 4);
            graph.addEgde(awsCompute, azureCompute, 3);
            
            graph.printGraph();
            System.out.println(graph.getWeightsSum());
        }
}

