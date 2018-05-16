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
	
    /**
     * Verifica se um grafo e conectado ou nao
     * @param graph grafo a ser verificado
     * @return retorna true se for conectado ou false caso contrario.
     * */
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
    
    /**
     * Metodo especifico para o metodo connected, dado um vertice, ele vai visitar seus adjacentes recursivamente
     * @param node vertice onde a partir de onde comecara a dfs
     * @param visitados conjunto de vertices que ja foram visitados
     * @param grafo o grafo
     * @return se o vertice ja foi vistado retorna, caso base da recursividade do metodo
     * */
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

	/**
	 * Metodo que dado um grafo conexo calcula a Minimum Spanning Tree (Arvore Geradora Minima)
	 *@param graph grafo que tera sua MST calculada
	 *@return retorna uma String com as informacoes da MST (Vertice - Nivel - Pai)
	 * */
    public String mst(Graph graph){
    	final int PAI_DA_RAIZ = -1;
    	final double DISTANCIA_INICIAL = 0.;
    	final double INFINITO = Double.MAX_VALUE;
    	final int NIVEL_INICIAL = 0;
    	
		List<Node> vertices = new ArrayList<Node>(graph.getVertices());
		Map<Node, Integer> niveis = new HashMap<Node, Integer>();
		Map<Node, Node> pais = new HashMap<Node, Node>();
		Map<Node, Double> distancias = new HashMap<Node, Double>();
		Set<Node> naoVisitados = new HashSet<Node>();
		
		//inicializa as distancias como infinito e adiciona todos os vertices ao conjunto de nao visitados
		for(int i = 0; i < vertices.size(); i++) {
			distancias.put(vertices.get(i), INFINITO);
			naoVisitados.add(vertices.get(i));
		}

		distancias.put(vertices.get(0), DISTANCIA_INICIAL); //vertice inicial
		pais.put(vertices.get(0), new Node(PAI_DA_RAIZ));
		niveis.put(vertices.get(0), NIVEL_INICIAL);
		
		//para todos os vertices, menos o ultimo
		for(int i = 0; i < (vertices.size() - 1);i++) {
			Node verticeAtual = getMinimo(naoVisitados, distancias);
			naoVisitados.remove(verticeAtual);
			
			//para cada adjacente do vertice atual
			for(Node adjacente : graph.getAdjacentes(verticeAtual)) {
				if(naoVisitados.contains(adjacente)) { //se o adjacente ainda nao foi visitado
					double pesoAresta = graph.getPesoAresta(verticeAtual, adjacente); //peso da aresta que liga o verticeAtual e seu adjacente
					if(pesoAresta < distancias.get(adjacente)) { //se a distancia do verticeAtual ate seu adjacente for menor q a distancia atual do adjacente
						pais.put(adjacente, verticeAtual); //coloca o vertice atual como novo pai do adjacente
						niveis.put(adjacente, niveis.get(verticeAtual) + 1); //o nivel do adjacente agora sera o nivel do seu pai + 1
						distancias.put(adjacente, pesoAresta); //e sua nova distancia sera o peso da aresta que liga ele a seu pai
					}
				}
			}
		}
		
		return mstFormatada(pais, niveis, vertices);
		
    }
    
    /**
     * Metodo que calcula o vertice nao visitado com a menor distancia
     * @param naoVisitados conjunto de vertices nao visitados
     * @param distancias distancias de cada vertice
     * @return retorna o vertice nao visitado com a menor distancia
     * */
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
    
    /**
     * Metodo que formata a String da mst
     * @param pais mapa que tem todos os vertices e seus respectivos pais
     * @param niveis mapa que tem todos os vertices e seus respectivos niveis
     * @param vertices lista com todos os vertices do grafo
     * @return String da mst formatada
     * */
    private String mstFormatada(Map<Node, Node> pais, Map<Node, Integer> niveis, List<Node> vertices) {
    	String saida = "";
    	final int PAI_DA_RAIZ = -1;
    	
    	Collections.sort(vertices, new ComparatorNode());

    	for(Node vertice : vertices) {
    		if(pais.get(vertice).getValor() == PAI_DA_RAIZ) {
    			saida += vertice.getValor() + " - " + niveis.get(vertice) +  " -\n";
    		} else if (vertice.equals(vertices.get(vertices.size() - 1))){
    			saida += vertice.getValor() + " - " + niveis.get(vertice) + " " + pais.get(vertice).getValor();
    		} else {
    			saida += vertice.getValor() + " - " + niveis.get(vertice) + " " + pais.get(vertice).getValor() + "\n";
    		}
    		
    	}
    	return saida;
    }
}
