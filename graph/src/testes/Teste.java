package testes;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import graph.Facade;
import graph.Graph;
import graph.Node;

public class Teste {
	Graph grafo1, grafo2, grafo3, grafo4;
	Facade facade;
	
	/**
	 * Metodo que inicia as variaveis
	 * */
	@Before
	public void voidsetUp() throws Exception {
		grafo1 = new Graph();
		grafo2 = new Graph();
		grafo3 = new Graph();
		grafo4 = new Graph();
		facade = new Facade();
		grafo1.readGraph(new File("../bibliotecagrafos/graph/src/q1_grafos.txt"));
		grafo2.readWeightedGraph(new File("../bibliotecagrafos/graph/src/q2_grafos.txt"));
		grafo3.readGraph(new File("../bibliotecagrafos/graph/src/q3_grafos.txt"));
		grafo4.readWeightedGraph(new File("../bibliotecagrafos/graph/src/q4_grafos.txt"));
	}
	
	
	/**
	 * Teste criado para testar o metodo  readGraph(File path) da classe Graph. 
	 * Esse teste também testa se a quantidade arestas e vertices do grafo corresponde ao que foi passado na entrada.
	 */
	@Test
	public void testreadGraph() {
		Graph grafo = new Graph();
		try {
			grafo.readGraph(new File("../bibliotecagrafos/graph/src/q1_grafos.txt"));
			Assert.assertEquals(5, grafo.getVertices().size()); //Testa quantidade de vertices do grafo (Se é igual ao esperado)
			Assert.assertNotEquals(4 , grafo.getVertices()); //Testa quantidade de vertices do grafo (Se é igual ao esperado)
			Assert.assertEquals(5, grafo.getArestas().size()); //Testa quantidade de arestas do grafo (Se é igual ao esperado)
			Assert.assertNotEquals(3, grafo.getArestas()); //Testa quantidade de arestas do grafo (Se é igual ao esperado)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Teste criado para testar o metodo  readWeightedGraph(File path) da classe Graph. 
	 * Esse teste também testa se a quantidade arestas e vertices do grafo corresponde ao que foi passado na entrada.
	 */
	@Test
	public void testreadWeightedGraph() {
		Graph grafo = new Graph();
		try {
			grafo.readWeightedGraph(new File("../bibliotecagrafos/graph/src/q2_grafos.txt"));
			Assert.assertEquals(5, grafo.getVertices().size());  //Testa quantidade de vertices do grafo (Se é igual ao esperado)
			Assert.assertNotEquals(4 , grafo.getVertices()); //Testa quantidade de vertices do grafo (Se é igual ao esperado)
			Assert.assertEquals(6, grafo.getArestas().size()); //Testa quantidade de arestas do grafo (Se é igual ao esperado)
			Assert.assertNotEquals(3, grafo.getArestas()); //Testa quantidade de arestas do grafo (Se é igual ao esperado)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Teste criado para testar o metodo testgetMeanEdge(Graph graph) da classe Facade
	 */
	@Test
	public void testgetMeanEdge(){
		Assert.assertEquals(2,2, facade.getMeanEdge(grafo1));
		Assert.assertEquals(2.4 , 2.4 ,facade.getMeanEdge(grafo2));
		
	}
	
	/**
	 * Testa o metodo connected(Graph graph) da classe Facade
	 * */
	@Test
	public void testConnected() {
		assertTrue(facade.connected(grafo1));
		assertTrue(facade.connected(grafo2));
		assertFalse(facade.connected(grafo3));
	}
	
	/**
	 * Testa o metodo mst(Graph graph) da classe Facade
	 * */
	@Test
	public void testMST() {
		assertEquals("1 - 0 -\n"
				   + "2 - 1 1\n"
				   + "3 - 2 5\n"
				   + "4 - 2 5\n"
				   + "5 - 1 1", facade.mst(grafo1));
		assertEquals("1 - 0 -\n"
				   + "2 - 1 1\n"
				   + "3 - 4 4\n"
				   + "4 - 3 5\n"
				   + "5 - 2 2", facade.mst(grafo2));
		assertEquals("0 - 0 -\n"
				   + "1 - 1 0\n"
				   + "2 - 1 0\n"
				   + "3 - 2 5\n"
				   + "4 - 2 7\n"
				   + "5 - 1 0\n"
				   + "6 - 1 0\n"
				   + "7 - 1 0", facade.mst(grafo4));
		assertEquals("O grafo nao e conectado!", facade.mst(grafo3));
	}
	
	/**
	 * Testa o metodo DFS(Graph graph, Node s) da classe Facade.
	 */
	@Test
	public void testDFS() {
		assertEquals("1 - 0 -\r\n" + 
					 "2 - 1 1\r\n" + 
					 "3 - 3 5\r\n" + 
					 "4 - 3 5\r\n" + 
					 "5 - 2 2", facade.DFS(grafo1, grafo1.getVertices().get(0))); //grafo1, Vertice inicial 1.
		assertEquals("1 - 0 -\r\n" + 
					 "2 - 1 1\r\n" + 
					 "3 - 3 5\r\n" + 
					 "4 - 4 3\r\n" + 
					 "5 - 2 2", facade.DFS(grafo2, grafo2.getVertices().get(0))); //grafo2, Vertice inicial 1.
	}
	
	/**
	 * Testa o metodo graphRepresentation(Graph graph, String type) da classe Facade.
	 */
	@Test
	public void testGraphRepresentation() {
		assertEquals("1 - 2 5\r\n" + 
					 "2 - 1 5\r\n" + 
					 "3 - 5\r\n" + 
					 "4 - 5\r\n" + 
					 "5 - 1 2 3 4", facade.graphRepresentation(grafo1, "AL")); // Lista de Adjacencia para o grafo1.
		assertEquals(" 1 2 3 4 5\r\n" + 
					 "1 0 1 0 0 1\r\n" + 
					 "2 1 0 0 0 1\r\n" + 
					 "3 0 0 0 0 1\r\n" + 
					 "4 0 0 0 0 1\r\n" + 
					 "5 1 1 1 1 0", facade.graphRepresentation(grafo1, "AM")); // Matriz de Adjacencia para o grafo1.
		assertEquals("1 - 2(0.1) 5(1)\r\n" + 
					 "2 - 1(0.1) 5(0.2)\r\n" + 
					 "3 - 4(-9.5) 5(5)\r\n" + 
					 "4 - 3(-9.5) 5(2.3)\r\n" + 
					 "5 - 1(1) 2(0.2) 3(5) 4(2.3)", facade.graphRepresentation(grafo2, "AL")); // Lista de Adjacencia para o grafo2.
		assertEquals(" 1 2 3 4 5\r\n" + 
					 "1 0 0.1 0 0 1\r\n" + 
					 "2 0.1 0 0 0 0.2\r\n" + 
					 "3 0 0 0 -9.5 5\r\n" + 
					 "4 0 0 -9.5 0 2.3\r\n" + 
					 "5 1 0.2 5 2.3 0", facade.graphRepresentation(grafo2, "AM")); // Matriz de Adjacencia para o grafo2.
	}
	
	/**
	 * Teste que testa o metodo BFS(Graph graph, Node s)
	 */
	@Test
    public void BFS() {

        String output1 = "1 - 0 -\r\n" + 
                "2 - 1 1\r\n" + 
                "3 - 2 5\r\n" + 
                "4 - 2 5\r\n" + 
                "5 - 1 1\r\n";
        
        String output2 = "1 - 1 5\r\n" + 
                "2 - 1 5\r\n" + 
                "3 - 1 5\r\n" + 
                "4 - 1 5\r\n" + 
                "5 - 0 -\r\n" ;
        
        String output3 = "1 - 2 5\r\n" + 
                "2 - 2 5\r\n" + 
                "3 - 0 -\r\n" + 
                "4 - 2 5\r\n" + 
                "5 - 1 3\r\n" ;
        

        Assert.assertEquals(output1, facade.BFS(grafo1, grafo1.getVertices().get(0)));
        Assert.assertEquals(output2, facade.BFS(grafo1, grafo1.getVertices().get(2)));
        Assert.assertEquals(output3, facade.BFS(grafo1, grafo1.getVertices().get(3)));
    }
	
	@Test
    public void testshortestPath() {
		Assert.assertEquals("1 2 5 ", grafo2.shortestPath(grafo2, new Node(1), new Node(5)));
		Assert.assertEquals("3 4 5 ", grafo2.shortestPath(grafo2, new Node(3), new Node(5)));
		Assert.assertEquals("0 7 4 ", grafo2.shortestPath(grafo4, new Node(0), new Node(4)));
	}

}
