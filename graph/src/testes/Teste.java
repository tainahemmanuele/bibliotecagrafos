package testes;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import graph.Facade;
import graph.Graph;
import graph.Node;

public class Teste {
	
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
		Graph grafo = new Graph();
		Graph grafo2 = new Graph();
		Facade facade = new Facade();
		try {
			grafo.readGraph(new File("../bibliotecagrafos/graph/src/q1_grafos.txt"));
			grafo2.readWeightedGraph(new File("../bibliotecagrafos/graph/src/q2_grafos.txt"));
			Assert.assertEquals(2,2, facade.getMeanEdge(grafo));
			Assert.assertEquals(2.4 , 2.4 ,facade.getMeanEdge(grafo2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
