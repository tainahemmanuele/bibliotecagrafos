package graph;

import java.io.File;
import java.io.IOException;

public class main {

	public static void main(String[] args) {
		Graph grafo1 = new Graph();
		File f = new File("C:\\Users\\Pichau\\Desktop\\Arquivos\\UFCG\\ATG\\Pratica 1\\q2_grafos.txt");
		Facade facade = new Facade();
		try {
			grafo1.readWeightedGraph(f);
			//grafo1.readGraph(f);
			//System.out.println(grafo1.getVertices().get(0));
			//System.out.println(facade.DFS(grafo1, grafo1.getVertices().get(0)));
			System.out.println(facade.graphRepresentation(grafo1, "AM"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
