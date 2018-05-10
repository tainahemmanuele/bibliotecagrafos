package graph;

import java.util.HashMap;
import java.util.ArrayList;

public class ListaAdjacencia {
	
	private HashMap<Node, ArrayList<Node>> listaAdj;

	public ListaAdjacencia(Graph grafo) {
		this.listaAdj = constroiLista(grafo);
	}
	
    private HashMap<Node, ArrayList<Node>> constroiLista(Graph grafo) {
    	HashMap<Node, ArrayList<Node>> listaAdj = new HashMap<>();
    	for (Node node : grafo.getVertices()) {
			listaAdj.put(node, grafo.getAdjacentes(node));
		}
		return listaAdj;
	}

	@Override
    public String toString() {
        return listaAdj.toString();
    }
}
