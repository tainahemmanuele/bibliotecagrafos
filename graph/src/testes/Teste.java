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
	}

}
