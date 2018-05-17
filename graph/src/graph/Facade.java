package graph;

import java.util.*;

public class Facade {

    private ExibicaoDoGrafo exibicao;

    public Facade(){
        this.exibicao = new MatrizAdjacencia();
    }
/**
 * Este metodo ira aplicar o algoritmo de busca em largura e retornar a representacao do grafo resultante.
 * @param graph :Grafo
 * @param s :Node (vertice) inicial
 * @return Uma string representando o grafo resultante apos ser percorrido por uma BFS.
 */
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

    public String DFS(Graph graph, Node s){
        return null;
    }

    public int getVertexNumber(Graph graph){return 0;}

    public int getEdgeNumber(Graph graph){return 0;}

    public float getMeanEdge(Graph graph){return 0;}

    public String graphRepresentation(Graph graph, String type){return "";}

    public String SCC(Graph graph){return "";}

    public String shortestPath(Node v1, Node v2){return "";}

    public String mst(Graph graph){return "";}


}
