package graph;

import java.util.*;
public class Graph{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists


    public Graph(int v){

    }

    public void addEdge(int v,int w) {
        adj[v].add(w);
    }
}

