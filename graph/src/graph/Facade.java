package graph;

import java.util.*;

public class Facade {

	public Facade() {
	}

	public String BFS(Graph graph, Node s) {
		Queue<Node> q = new LinkedList<Node>(); // Fila para realizar bfs
		List<Aresta> arestas = graph.getArestas(); // Arestas do grafo
		Map<Node, Integer> distancias = new HashMap<Node, Integer>(); // Mapa de
																		// distancias
																		// de
																		// cada
																		// vertice
		Map<Node, Integer> parentes = new HashMap<Node, Integer>(); // Mapa de
																	// parentes
																	// de cada
																	// vertice
		q.add(s); // colocando s na fila para iniciar bfs
		distancias.put(s, 0); // definindo distancias
		while (!q.isEmpty()) {
			Node atual = q.remove();
			int minhaDistancia = distancias.get(atual);
			for (Aresta aresta : arestas) { // verificar todas as arestas para
											// encontrar adjacencias
				if (aresta.contemVertice(atual)) { // verificar se eh aresta do
													// vertice
					Node destinoAresta = aresta.getNode1(); // descobrir a
															// direcao da aresta
					if (destinoAresta.equals(atual)) // caso tenha pego a
														// direcao errado
						destinoAresta = aresta.getNode2(); // muda direcao
					if (!distancias.containsKey(destinoAresta)) { // verificando
																	// se nao
																	// tem a
																	// distancia
																	// setada
						distancias.put(destinoAresta, minhaDistancia + 1); // coloca
																			// a
																			// distancia
						parentes.put(destinoAresta, atual.getValor()); // coloca
																		// o pai
						q.add(destinoAresta); // adiciona o novo vertice a fila
					}
				}
			}
		}
		String resultado = ""; // string do resultado
		int qtdVertices = getVertexNumber(graph);
		for (int i = 1; i <= qtdVertices; i++) {
			String linhai = i + " - ";
			Node noAtual = new Node(i);
			linhai += distancias.get(noAtual)+" ";
			if (!noAtual.equals(s)) // verificando se eh diferente do no inicial
									// para pegar o parente
				linhai += parentes.get(noAtual);
			else
				linhai += "-";

			linhai += System.lineSeparator();
			resultado += linhai;
		}
		return resultado;
	}

	public String DFS(Graph graph, Node s) {
		// DFS
		boolean[] visitados = new boolean[graph.getVertices().size()]; // false = nao visitado, true = visitado
		Node[] pais = new Node[graph.getVertices().size()];
		int[] niveis = new int[graph.getVertices().size()];
		niveis[graph.getVertices().indexOf(s)] = 0;
		DFSRec(graph, s, visitados, pais, niveis);

		// Formatacao de saida
		String[] resultado = new String[graph.getVertices().size()];
		for (int i = 0; i < graph.getVertices().size(); i++) {
			String aux = "";
			Node no = graph.getVertices().get(i);
			aux += no.getValor() + " - " + niveis[graph.getVertices().indexOf(no)] + " "; // padrao 'vertice - nivel '
			if (pais[graph.getVertices().indexOf(no)] == null) { // Se o pai for null
				aux += "-";
			} else {
				aux += pais[graph.getVertices().indexOf(no)].getValor(); // Concatena o pai
			}
			resultado[i] = aux;
		}
		Arrays.sort(resultado);
		return String.join(System.lineSeparator(), resultado);
	}

	private void DFSRec(Graph grafo, Node no, boolean[] visitados, Node[] pais, int[] niveis) {
		visitados[grafo.getVertices().indexOf(no)] = true;

		for (Node node : grafo.getAdjacentes(no)) {
			if (visitados[grafo.getVertices().indexOf(node)] == false) {
				pais[grafo.getVertices().indexOf(node)] = no;
				niveis[grafo.getVertices().indexOf(node)] = niveis[grafo.getVertices().indexOf(no)] + 1;
				DFSRec(grafo, node, visitados, pais, niveis);
			}
		}
	}

	/**
	 * Retorna o número de vertices do grafo
	 * 
	 * @param graph
	 *            : Grafo a qual se deseja saber o número de vértices
	 * @return : número de vértices de um grafo
	 */
	public int getVertexNumber(Graph graph) {
		return graph.getVertices().size();
	}

	/**
	 * Retorna o número de arestas de um grafo
	 * 
	 * @param graph
	 *            : Grafo a qual se deseja saber o número de arestas
	 * @return : número de arestas de um grafo
	 */
	public int getEdgeNumber(Graph graph) {
		return graph.getArestas().size();
	}

	/**
	 * Retorna o grau medio de um grafo. Para isso, a partir
	 * 
	 * @param graph
	 *            : Grafo a qual se deseja saber o grau médio
	 * @return: grau médio de um grafo
	 */
	public float getMeanEdge(Graph graph) {
		int soma = 0;
		float media = 0;
		for (int i = 0; i < graph.getVertices().size(); i++) {
			Node node = graph.getVertices().get(i);
			soma += graph.getVerticeArestas().get(node).size(); // No hashmap da
																// classe grafo,
																// a partir de
																// um vertice,
																// consulta a
																// lista de
																// arestas que
																// se conecta a
																// ele e pega a
																// quantidade
																// das mesmas e
																// soma a cada
																// iteração

		}
		media = soma / (graph.getVertices().size()); // Divide a soma anterior pela quantidade de vertices total do grafo
		return media;
	}

	public String graphRepresentation(Graph graph, String type) {
		if (type.equals("AL")) {
			ListaAdjacencia exibicaoAL = new ListaAdjacencia(graph);
			return exibicaoAL.toString();
		} else if (type.equals("AM")) {
			MatrizAdjacencia exibicaoMA = new MatrizAdjacencia(graph);
			return exibicaoMA.toString();
		}
		return "";
	}
    public boolean connected(Graph graph){
    	Set<Node> visitados = new HashSet<Node>();
    	dfs(graph.getVertices().get(0), visitados, graph);
    	
    	for(Node vertice : graph.getVertices()) {
    		if(!visitados.contains(vertice)) {
    			return false;
    		}
    	}
    	
    	return true;
    }

    private void dfs(Node node, Set<Node> visitados, Graph grafo) {
		if(visitados.contains(node)) {
			return;
		}
		
		visitados.add(node);
		
		for(Node adjacente : grafo.getAdjacentes(node)) {
			if(!visitados.contains(adjacente)) {
				dfs(adjacente, visitados, grafo);
			}
		}
		
	}

	public String shortestPath(Node v1, Node v2){return "";}

    public String mst(Graph graph){
		List<Node> vertices = new ArrayList<Node>(graph.getVertices());
		Map<Node, Integer> niveis = new HashMap<Node, Integer>();
		Map<Node, Node> predecessores = new HashMap<Node, Node>();
		Map<Node, Double> distancias = new HashMap<Node, Double>();
		Set<Node> naoVisitados = new HashSet<Node>();


		for(int i = 0; i < vertices.size(); i++) {
			distancias.put(vertices.get(i), Double.MAX_VALUE);
			naoVisitados.add(vertices.get(i));
		}

		distancias.put(vertices.get(0), 0.); //vertice inicial
		predecessores.put(vertices.get(0), new Node(-1)); //pai do vertice inicial é -1

		for(int i = 0; i < (vertices.size() - 1);i++) {
			Node auxNode = getMinimo(naoVisitados, distancias);
			naoVisitados.remove(auxNode);

			for(Node adjacente : graph.getAdjacentes(auxNode)) {
				if(naoVisitados.contains(adjacente)) {
					double pesoAresta = graph.getPesoAresta(auxNode, adjacente);
					if(pesoAresta < distancias.get(adjacente)) {
						predecessores.put(adjacente, auxNode);
						distancias.put(adjacente, pesoAresta);
					}
				}
			}
		}
		
		for(Node vertice : vertices) {
			if(vertice.equals(vertices.get(0))) {
				niveis.put(vertice, 0);
			} else {
				niveis.put(vertice, vertices.indexOf(predecessores.get(vertice)) + 1);
			}
		}
		
		return printMST(predecessores, niveis, vertices);
		
    }
    
    //retorna o vertice nao visitado com a menor distancia
    private Node getMinimo(Set<Node> naoVisitados, Map<Node, Double> distancias) {
    	Node minimo = null;

    	for(Node vertice : naoVisitados) {
    		if(minimo == null) {
    			minimo = vertice;
    		} else {
    			if (distancias.get(vertice) < distancias.get(minimo)) {
    				minimo = vertice;
    			}
    		}
    	}
    	return minimo;
    }
    
    private String printMST(Map<Node, Node> predecessores, Map<Node, Integer> niveis, List<Node> vertices) {
    	String saida = "";
    	Collections.sort(vertices, new ComparatorNode());

    	for(Node vertice : vertices) {
    		if(predecessores.get(vertice).getValor() == -1) {
    			saida += vertice.getValor() + " - " + niveis.get(vertice) +  " -\n";
    		} else {
    			saida += vertice.getValor() + " - " + niveis.get(vertice) + " - " + predecessores.get(vertice).getValor() + "\n";
    		}
    		
    	}
    	return saida;
    }
}
