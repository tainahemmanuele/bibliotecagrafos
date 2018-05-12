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
	private HashMap<Node, ArrayList<Aresta>> grafo; // Hashmap que representa o
													// grafo da seguinte forma:
													// o no é a chave e o valor
													// é a lista de arestas que
													// conectam aquele no a
													// outros nós
	private HashMap<Node, ArrayList<Aresta>> grafoComPesos; // Hashmap que
															// representa o
															// grafo da seguinte
															// forma: o no é a
															// chave e o valor é
															// a lista de
															// arestas que
															// conectam aquele
															// no a outros nós


	public static void main(String[] args) {
		Graph grafo = new Graph();
		try {
			grafo.readWeightedGraph(new File("C:\\Users\\Tainah\\Desktop\\q2_grafos.txt"));
			Node node = new Node(5);
			System.out.println(grafo.getArestas());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public Graph() {
		this.vertices = new ArrayList<Node>();
		this.arestas = new ArrayList<Aresta>();
		this.grafo = new HashMap<Node, ArrayList<Aresta>>();
		this.grafoComPesos = new HashMap<Node, ArrayList<Aresta>>();
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
		FileReader arq = new FileReader(path); // Ler o arquivo
		BufferedReader lerArq = new BufferedReader(arq); // Prepara um buffer
															// para a leitura de
															// arquivo
		String linha;
		linha = lerArq.readLine(); // Ler a primeira linha do arquivo
		int qtdVertices = Integer.parseInt(linha);
		ArrayList<Aresta> arestasGrafo; // ArrayList que define para cada
										// vertice do grafo, as arestas que o
										// conectam a outros vertices

		while ((linha = lerArq.readLine()) != null) {
			// primeiro elemento (Node no hasmap): vertice inicial
			// demais elementos(lista de Arestas no hasmap): elementos que se
			// conectam o vertice inicial
			String[] aux = linha.split(" "); // A partir de cada linha do
												// arquivo, cria um array coms
												// os dados da mesma
			Node node1 = new Node(Integer.parseInt(aux[0])); // Cria o primeiro
																// nó (Para
																// definição da
																// aresta)
			Node node2 = new Node(Integer.parseInt(aux[1])); // Cria o segundo
																// nó (Para
																// definição da
																// aresta)
			Aresta aresta = new Aresta(node1, node2); // Cria a aresta
			this.arestas.add(aresta); // Adiciona aresta no ArrayList de todas
										// as arestas existentes no grafo
			// Se o Hashmap grafo nao possui como chave o vertice(node1), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado e a adiciona na lista
			if (!grafo.containsKey(node1)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.grafo.put(node1, arestasGrafo);

			} else { // Caso contrário, apenas adiciona a aresta
				this.grafo.get(node1).add(aresta);
			}
			// Se o Hashmap grafo nao possui como chave o vertice2(node2), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado
			if (!grafo.containsKey(node2)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.grafo.put(node2, arestasGrafo);
			} else { // Caso contrário adiciona apenas a aresta
				this.grafo.get(node2).add(aresta);
			}
			// Verifica se o vertice já está adicionado na lista de vertices do
			// grafo e adiciona caso não esteja
			if (!getVertices().contains(node1)) {
				getVertices().add(node1);
			}
			if (!getVertices().contains(node2)) {
				getVertices().add(node2);
			}

		}
		arq.close();
		lerArq.close(); // Fim da leitura do arquivo

	}

	public void readWeightedGraph(File path) throws IOException {
		FileReader arq = new FileReader(path);//Prepara um buffer
		// para a leitura de
		// arquivo
		BufferedReader lerArq = new BufferedReader(arq); // Ler a primeira linha
															// do arquivo
		String linha;
		linha = lerArq.readLine();
		int qtdVertices = Integer.parseInt(linha);
		ArrayList<Aresta> arestasGrafo;// ArrayList que define para cada
		// vertice do grafo, as arestas que o
		// conectam a outros vertices

		while ((linha = lerArq.readLine()) != null) {
			// primeiro elemento (Node no hasmap): vertice inicial
			// demais elementos(lista de Arestas no hasmap): elementos que se
			// conectam o vertice inicial
			String[] aux = linha.split(" ");// A partir de cada linha do
			// arquivo, cria um array coms
			// os dados da mesma
			Node node1 = new Node(Integer.parseInt(aux[0]));// Cria o primeiro
			// nó (Para
			// definição da
			// aresta)
			Node node2 = new Node(Integer.parseInt(aux[1])); // Cria o segundo
			// nó (Para
			// definição da
			// aresta)
			Double valorAresta = Double.parseDouble(aux[2]); // Valor do peso da
																// aresta
			Aresta aresta = new Aresta(node1, node2, valorAresta); // Cria a
																	// aresta
			this.arestas.add(aresta); // Adiciona aresta no ArrayList de todas
			// as arestas existentes no grafo
			// Se o Hashmap grafo nao possui como chave o vertice(node1), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado e a adiciona na lista
			if (!grafo.containsKey(node1)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.grafo.put(node1, arestasGrafo);

			} else {// Caso contrário, apenas adiciona a aresta
				this.grafo.get(node1).add(aresta);
			}
			// Se o Hashmap grafo nao possui como chave o vertice2(node2), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado
			if (!grafo.containsKey(node2)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.grafo.put(node2, arestasGrafo);

			} else { // Caso contrário, apenas adiciona a aresta
				this.grafo.get(node2).add(aresta);
			}

			// Verifica se o vertice já está adicionado na lista de vertices do
			// grafo e adiciona caso não esteja
			if (!getVertices().contains(node1)) {
				getVertices().add(node1);
			}
			if (!getVertices().contains(node2)) {
				getVertices().add(node2);
			}

		}
		arq.close();
		lerArq.close(); //Fim da leitura do arquivo

	}
	
	// Retorna lista de vertices adjacentes ao vertice escolhido no parametro.
	public ArrayList<Node> getAdjacentes(Node no) {
		ArrayList<Node> adjacentes = new ArrayList<Node>();
    	for (Aresta aresta : this.arestas) {
			if (aresta.getNode1().equals(no)) {
				adjacentes.add(aresta.getNode2());
			} else if (aresta.getNode2().equals(no)) {
				adjacentes.add(aresta.getNode1());
			}
		}
		return adjacentes;
	}
	
	public boolean temPeso() {
		for (Aresta aresta : arestas) {
			if (aresta.getvalor() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public Double getPesoAresta(Node no1, Node no2) {
		for (Aresta aresta : arestas) {
			if ((aresta.getNode1().equals(no1) && aresta.getNode2().equals(no2)) || (aresta.getNode1().equals(no2) && aresta.getNode2().equals(no1))) {
				return aresta.getvalor();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Graph [grafo=" + grafo + "]";
	}
}
