package graph;

import java.io.File;
import java.util.*;
public class Graph{
    private ArrayList<Node> vertices;
    private ArrayList<Aresta> arestas;
    private  Map<int, ArrayList<int>>  grafo  = new HashMap<int, ArrayList<int>>();

    public static void main(String[] args) {
        Graph grafo = new Graph();

    }

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

    public void readGraph(File path){
        FileReader arq = new FileReader(path);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha;
        linha = lerArq.readLine();
        int indice = Integer.parseInt(linha);
        while (linha != null){
            //primeiro elemento (int no hasmap): vertice inicial
            //demais elementos(lista de ints no hasmap): elementos que se conectam o vertice inicial
        }



    }

    public void readWeightedGraph(File path){ }




}

