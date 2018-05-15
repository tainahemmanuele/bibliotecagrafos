package graph;

import java.util.ArrayList;
import java.util.Stack;

import graph.Graph;
import graph.Node;

public class PathsTools {
	private Double correcao;
	
	private void setCorrection(Graph grafo) {
		if (getMinimumEdgeValue(grafo) >= 0) {
			this.correcao = (double) 0;
		}else {
			this.correcao = -1 * getMinimumEdgeValue(grafo);
		}
	}
	// Realocar para Graph
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
	
	
	private Double getPesoArestaDijksdra(Graph grafo, Node no1, Node no2){
		return grafo.getPesoAresta(no1, no2) + correcao; // O segredo do algoritmo está aqui.
		                                                 // A correção garante que todos os pesos 
		                                                 // sejam positivos e, portanto, adequados ao
		                                                 // algoritmo de Dijdstra.
	}
	
	
	private boolean haveSomeNode(Graph grafo) {
		if (!grafo.getVertices().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean itAValidNode(Graph grafo, Node a) {
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
	}
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
	
	
	public String shortestPath(Graph grafo, Node a, Node b) {
		setCorrection(grafo);
		ArrayList<Node> n = grafo.getVertices();
		DijkstraArray r = new DijkstraArray(new ArrayList<>());
		
		for (Node node : n) {
			r.addInEnd(new DijksdraNode(null, Double.POSITIVE_INFINITY, node));
		}
		
		r.setDistance(a, (double) 0);
		while (DontFindEnd(r)) {
			DijksdraNode u = r.getNodeWithLeastDistance();
			r.removeNode(u);
			
			if (isATargetNode(u, b)) {
				
				Stack pilha = stackingTheNodes(u);
				
				return generateResult(pilha);
			}
			for (Node neighbor : grafo.getAdjacentes(u.getNode())) {
				Double alt = (r.getDistance(u.getNode()) + getPesoArestaDijksdra(grafo, neighbor, u.getNode()));
				if (r.getDistance(neighbor) > alt) {
					r.setDistance(neighbor, alt);
					r.setPrevious(neighbor, u);
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
