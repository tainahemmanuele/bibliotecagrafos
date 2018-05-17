package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
	private Double correcao;
	private ArrayList<Node> vertices;
	private ArrayList<Aresta> arestas;
	private HashMap<Node, ArrayList<Aresta>> verticeArestas; // * Hashmap que
																// representa o
																// grafo da
																// seguinte
																// forma: o no é
																// a chave e o
																// valor é a
																// lista de
																// arestas que
																// conectam
																// aquele no a
																// outros nós
																// *\\

	public Graph() {
		this.vertices = new ArrayList<Node>();
		this.arestas = new ArrayList<Aresta>();
		this.verticeArestas = new HashMap<Node, ArrayList<Aresta>>();
		setCorrection(this);
	}

	/**
	 * @return the correcao
	 */
	public Double getCorrecao() {
		return correcao;
	}

	public ArrayList<Aresta> getArestas() {
		return this.arestas;
	}

	public ArrayList<Node> getVertices() {
		return this.vertices;
	}

	public HashMap<Node, ArrayList<Aresta>> getGrafo() {
		return verticeArestas;
	}

	public HashMap<Node, ArrayList<Aresta>> getVerticeArestas() {
		return verticeArestas;
	}

	/**
	 * Metódo criado para ler um arquivo texto e a partir dele gerar o grafo,
	 * que possui uma lista de arestas e uma lista de vertices e um hasmap que
	 * faz a associação: vertices e as arestas que conectam a cada vertice.
	 * 
	 * @param path
	 *            : Caminho do arquivo texto que possui os dados necessários
	 *            para criar um grafo
	 * @throws IOException
	 */
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
			if (!verticeArestas.containsKey(node1)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node1, arestasGrafo);

			} else { // Caso contrário, apenas adiciona a aresta
				this.verticeArestas.get(node1).add(aresta);
			}
			// Se o Hashmap grafo nao possui como chave o vertice2(node2), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado
			if (!verticeArestas.containsKey(node2)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node2, arestasGrafo);
			} else { // Caso contrário adiciona apenas a aresta
				this.verticeArestas.get(node2).add(aresta);
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

	/**
	 * Metódo criado para ler um arquivo texto e a partir dele gerar o grafo,
	 * que possui uma lista de arestas e uma lista de vertices e um hasmap que
	 * faz a associação: vertices e as arestas que conectam a cada vertice. Como
	 * este grafo possui pesos nas arestas, há a adição dessa informação na
	 * criação do objeto aresta.
	 * 
	 * @param path
	 *            :Caminho do arquivo texto que possui os dados necessários para
	 *            criar um grafo
	 * @throws IOException
	 */
	public void readWeightedGraph(File path) throws IOException {
		FileReader arq = new FileReader(path);// Prepara um buffer
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
			if (!verticeArestas.containsKey(node1)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node1, arestasGrafo);

			} else {// Caso contrário, apenas adiciona a aresta
				this.verticeArestas.get(node1).add(aresta);
			}
			// Se o Hashmap grafo nao possui como chave o vertice2(node2), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado
			if (!verticeArestas.containsKey(node2)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node2, arestasGrafo);

			} else { // Caso contrário, apenas adiciona a aresta
				this.verticeArestas.get(node2).add(aresta);
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
		setCorrection(this);

	}

	/**
	 * Este metodo ira pegar todos os vertices adjacentes ao Node passado como parametro.
	 * @param no :Node
	 * @return Retorna um ArrayList<Node> com todos os vertices adjacentes de um determinado Node.
	 */
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

	/**
	 * Metodo para verificar se o grafo tem peso.
	 * @return True caso o grafo tenha peso positivo ou negativo. False caso o grafo nao tenha peso. 
	 */
	public boolean temPeso() {
		for (Aresta aresta : arestas) {
			if (aresta.getvalor() > 0 || aresta.getvalor() < 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Este metodo pega o peso da aresta que liga dois vertices.
	 * @param no1 :Node
	 * @param no2 :Node
	 * @return O peso (double) de uma aresta que liga dois Nodes. Null se nao houver uma aresta que liga os Nodes.
	 */
	public Double getPesoAresta(Node no1, Node no2) {
		for (Aresta aresta : arestas) {
			if ((aresta.getNode1().equals(no1) && aresta.getNode2().equals(no2))
					|| (aresta.getNode1().equals(no2) && aresta.getNode2().equals(no1))) {
				return aresta.getvalor();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Graph [grafo=" + verticeArestas + "]";
	}
	
	/**
	 * Método que define um valor de correção.
	 * Necessário em shortestPath, é o módulo do valor da
	 * menor aresta no grafo, caso seja negativa. Caso não
	 * entre no caso anterior, será igual a zero.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param grafo
	 */
	private void setCorrection(Graph grafo) {
		if (grafo.getArestas().isEmpty() || getMinimumEdgeValue(grafo) >= 0) {
			this.correcao = (double) 0;
		}else {
			this.correcao = -1 * getMinimumEdgeValue(grafo);
			}
	}
	
	/**
	 * Retorna o valor da aresta de menor peso de um grafo.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo 
	 * @param graph - grafo a ser analizado
	 * @return menor peso em um grafo
	 */
	private Double getMinimumEdgeValue(Graph graph) {
		ArrayList<Aresta> edges = graph.getArestas();
		
		Double minimum = Double.POSITIVE_INFINITY;
		for (Aresta aresta : edges) {
			if (aresta.getvalor() < minimum) {
				minimum = aresta.getvalor();
			}
		}
		return minimum;
	}
	
	/**
	 * Retorna o peso de uma aresta acrecido de uma correção.
	 * A correção garante que todos os peso sejam positivos caso 
	 * haja alguma aresta com peso negativo e, portanto, adequados ao 
	 * algoritmo de Dijdstra. Se não houver pesos negativos, o próprio
	 * peso será retornado.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param grafo - grafo onde se encontra os referidos nós
	 * @param no1 - nó de origem
	 * @param no2 - nó de destino
	 * @return Double maior ou igual a zero
	 */
	private Double getPesoArestaValido(Graph grafo, Node no1, Node no2){
		return grafo.getPesoAresta(no1, no2) + correcao;
	}
	
	
	/*private boolean haveSomeNode(Graph grafo) {
		if (!grafo.getVertices().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}*/
	
	/*private boolean itAValidNode(Graph grafo, Node a) {
		if (haveSomeNode(grafo)) {
			for (Node node : grafo.getVertices()) {
				if (node.equals(a)) {
					return true;
				}
			}
			return false;
		}else {
			return false;
		}
	}*/
	/*public Node getNodeWithLeastDistance(Graph grafo, Node u) {
		if (itAValidNode(grafo, u)) {
			ArrayList<Node> list = grafo.getAdjacentes(u);
			
			Node leastDistance = list.get(0);
			for (Node node : list) {
				if (grafo.getPesoAresta(u, node) < grafo.getPesoAresta(u, leastDistance)) {
					leastDistance = node;
				}
			}
			return leastDistance;
		}else {
			return null;
		}
		
	}*/
	
	
	private boolean DontFindEnd(DijkstraArray da) {
		if (!da.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isATargetNode(DijksdraNode u, Node b) {
		if (u.getNode().compareTo(b) == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Define um valor de punição a ser acresentado na análise de um caminho quando
	 * houve ao menos 1 aresta com peso negativo. Caso contrário, nada será acrescentado.
	 * 
	 * Isso é necessário, pois, ao acrecentar uma mesma correção a todos os pesos das
	 * arestas de um grafo, caminhos com mais arestas receberam mais correções do que
	 * caminhos com menos, interferindo na escolha do menor caminho.
	 * 
	 * Assim, foi definido como padrão para comparações um caminho com 100.000 nós.
	 *  A partir dai, um acrésimo de correções é definido para que seja justo comparar 
	 * o nó passado como argumento com um nó desse tamanho.
	 * 
	 *  Essa punição também permite uma comparação justa entre caminhos com número de 
	 * arestas diferêntes e com menos de 100000 nós, bastando que acrescente a devida
	 * punição aos caminhos analisados.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param data - array onde está o grafo segundo o algoritmo de Dijksdra.
	 * @param no1 - último nó do caminho a ser analizado.
	 * @return Double - punição necessária para uma comparação justa com um caminho de
	 * 100.000 nós.
	 */
	private Double penalty (DijkstraArray data, Node no1) {
		Double worstCase = (double) (100000);
		return (worstCase - data.getNodesToOrigin(no1)) * correcao;
	}
	
	/**
	 * Retorna o peso de uma aresta corrigido para o algoritmo de Dijksdra acrescido 
	 * de uma devida punição. 
	 * 
	 * Tal resultado permite comparar caminhos mesmo que se tenha acrescido uma mesma 
	 * correção para todas as arestas.
	 * ATENÇÃO: A ALTERAÇÃO NA ORDEM DOS NÓS PASSADOS ALTERA O RESULTADO.
	 *  
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param data - array onde está o grafo segundo o algoritmo de Dijksdra.
	 * @param grafo - grafo onde se encontra os referidos nós.
	 * @param no1 - nó de origem
	 * @param no2 - nó de destino
	 * @return Double 
	 */
	public Double getPesoArestaSeguro(DijkstraArray data, Graph grafo, Node no1, Node no2) {
		return getPesoArestaValido(grafo, no1, no2) + penalty(data, no1);
	}
	
	/*private boolean isALeastDistance(Graph grafo, DijkstraArray data, DijksdraNode analized, Node neighbor) {
		Double worstNumberOfNodes = Double.MAX_VALUE;
		Double distance = data.getDistance(analized.getNode());
		Double pesoAresta =  getPesoArestaDijksdra(grafo, neighbor, analized.getNode());
		Double alt = distance + pesoAresta;
		
		if ((alt + (worstNumberOfNodes - data.))) {
			
		}
		
		return false;
	}*/
	
	private ArrayList<Node> getAdjacentesDikstra(Graph grafo, DijkstraArray data, DijksdraNode u){
		ArrayList<Node> dat = grafo.getAdjacentes(u.getNode());
		ArrayList<Node> end = new ArrayList<>();
		for (Node no : dat) {
			if (data.isInArray(no)) {
				end.add(no);
			}
		}
		return end;
	}
	
	/**
	 * Método que retorna o menor caminho entre 2 nós em um grafo. 
	 * A implementação do método é pelo algoritmo de Dijdstra melhorado, capaz de
	 * lidar com arestas de peso negativo.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param grafo - grafo objeto de analise
	 * @param a - nó de partida
	 * @param b - nó de destino
	 * @return String com os nós que formam o menor caminho; null, caso não seja
	 *  possível de encontrar.
	 */
	public String shortestPath(Graph grafo, Node a, Node b) {
		setCorrection(grafo);
		ArrayList<Node> n = grafo.getVertices();
		DijkstraArray r = new DijkstraArray(new ArrayList<>());
		
		for (Node node : n) {
			r.addInEnd(new DijksdraNode(null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, node));
		}
		
		r.setDistance(a, (double) 0);
		r.setNodesUntilOrigin(a, (double) 0);
		while (DontFindEnd(r)) {
			DijksdraNode u = r.getNodeWithLeastDistance();
			r.removeNode(u);
			
			if (isATargetNode(u, b)) {
				
				Stack pilha = stackingTheNodes(u);
				
				return generateResult(pilha);
			}
			for (Node neighbor : grafo.getAdjacentesDikstra(grafo, r, u)) { //grafo.getAdjacentes(u.getNode())
				Double alt = (r.getDistance(u.getNode()) + getPesoArestaSeguro(r, grafo, u.getNode(), neighbor));
				if (r.getDistance(neighbor) > alt) {
					r.setDistance(neighbor, r.getDistance(u.getNode()) + getPesoArestaValido(grafo, u.getNode(), neighbor));
					r.setPrevious(neighbor, u);
					r.setNodesUntilOrigin(neighbor, u.getNodesFronOrigin() + 1);
				}
			}
		}
		return null;	
	}
	
	private String generateResult(Stack pilha) {
		String result = "";
		while (!pilha.isEmpty()) {
			result += pilha.pop().toString() + " ";
		}
		
		return result;
	}
	private Stack stackingTheNodes(DijksdraNode u) {
		Stack pilha = new Stack<>();
		while (u.getPrevious() != null) {
			pilha.push(u.getPrevious().getNode().getValor());
		}
		return pilha;
	}
}
