package graph;

import java.util.*;

public class Facade {


    public Facade(){
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
			Node noAtual = new Node(i);
			String linhai = i + " - "+distancias.get(noAtual)+" ";
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

	public String DFS(Graph graph, Node s){
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
			aux += no.getValor() + " - " + niveis[graph.getVertices().indexOf(no)] + " ";  // padrao 'vertice - nivel '
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
	 * @param graph : Grafo a qual se deseja saber o número de vértices
	 * @return
	 */
    public int getVertexNumber(Graph graph){
    	return graph.getVertices().size();}

    /**
     * Retorna o número de arestas de um grafo
     * @param graph : Grafo a qual se deseja saber o número de arestas
     * @return
     */
    public int getEdgeNumber(Graph graph){
    	return graph.getArestas().size();}

    public float getMeanEdge(Graph graph){return 0;}

    public String graphRepresentation(Graph graph, String type){
    	if (type.equals("AL")) {
    		ListaAdjacencia exibicaoAL = new ListaAdjacencia(graph);
    		return exibicaoAL.toString();
    	} else if (type.equals("AM")) {
    		MatrizAdjacencia exibicaoMA = new MatrizAdjacencia(graph);
    		return exibicaoMA.toString();
    	}
    	return "";
    }

    public String SCC(Graph graph){return "";}

    public String shortestPath(Node v1, Node v2){return "";}

    public String mst(Graph graph){return "";}


}
