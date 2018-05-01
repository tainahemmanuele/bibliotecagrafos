package graph;

import java.io.File;
import java.util.*;
public class Graph{
    private ArrayList<Node> vertices;
    private ArrayList<Aresta> arestas;

    public Graph(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public ArrayList<Aresta> getArestas(){
        return this.arestas;
    }

    public ArrayList<Node> getVertices(){
        return this.vertices;
    }

    public void readGraph(File path){ }

    public void readWeightedGraph(File path){ }




}

