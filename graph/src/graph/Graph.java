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
		try {
			grafo.readGraph(new File("C:\\Users\\Tainah\\Desktop\\q1_grafos.txt"));
			Node node = new Node(5);
			System.out.println(grafo.getGrafo().get(node));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		ArrayList<Aresta> arestasGrafo;

		while ((linha = lerArq.readLine()) != null) {
			// primeiro elemento (Node no hasmap): vertice inicial
			// demais elementos(lista de Arestas no hasmap): elementos que se
			// conectam o vertice inicial
			String[] aux = linha.split(" ");
			System.out.println(Arrays.toString(aux));
			Node node1 = new Node(Integer.parseInt(aux[0]));
			Node node2 = new Node(Integer.parseInt(aux[1]));
			Aresta aresta = new Aresta(node1, node2);
			this.arestas.add(aresta);
			if (!grafo.containsKey(node1)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.grafo.put(node1, arestasGrafo);

			} else {
				this.grafo.get(node1).add(aresta);
			}
			if (!grafo.containsKey(node2)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.grafo.put(node2, arestasGrafo);

			} else {
				this.grafo.get(node2).add(aresta);
			}

			if (!getVertices().contains(node1)) {
				getVertices().add(node1);
			}
			if (!getVertices().contains(node2)) {
				getVertices().add(node2);
			}

		}
		arq.close();
		lerArq.close();

	}

	public void readWeightedGraph(File path) {
	}

	@Override
	public String toString() {
		return "Graph [grafo=" + grafo + "]";
	}
}
