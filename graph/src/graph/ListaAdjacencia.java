package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class ListaAdjacencia implements IGraph {
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists


    public ListaAdjacencia(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public String BFS(int s) {
        return "";
    }
}
