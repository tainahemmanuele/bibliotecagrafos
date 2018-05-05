package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
	private ArrayList<Node> vertices;
	private ArrayList<Aresta> arestas;
	private HashMap<Node, ArrayList<Aresta>> grafo;

	public static void main(String[] args) {
		Graph grafo = new Graph();

	}

	public Graph() {
		this.vertices = new ArrayList<Node>();
		this.arestas = new ArrayList<Aresta>();
		this.grafo = new HashMap<Node, ArrayList<Aresta>>();
	}

	public ArrayList<Aresta> getArestas() {
		return this.arestas;
	}

	public ArrayList<Node> getVertices() {
		return this.vertices;
	}
	
	public HashMap<Node, ArrayList<Aresta>> getGrafo() {
		return grafo;
	}


	public void readGraph(File path) throws IOException {
		FileReader arq = new FileReader(path);
		BufferedReader lerArq = new BufferedReader(arq);
		String linha;
		linha = lerArq.readLine();
		int qtdVertices = Integer.parseInt(linha);
		while (linha != null) {
			// primeiro elemento (Node no hasmap): vertice inicial
			// demais elementos(lista de Arestas no hasmap): elementos que se
			// conectam o vertice inicial
			String[] aux = linha.split(" ");
			Node node1 = new Node(Integer.parseInt(aux[0]));
			Node node2 = new Node(Integer.parseInt(aux[1]));
			this.grafo.put(node1, getArestas()).add(new Aresta(node1, node2));
			if (!getVertices().contains(node1)) {
				getVertices().add(node1);
			} else if (!getVertices().contains(node2)) {
				getVertices().add(node2);
			}

		}
        arq.close();
        lerArq.close();
        

	}


	public void readWeightedGraph(File path) {
	}

}
