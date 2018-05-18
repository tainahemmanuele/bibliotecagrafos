package graph;

import java.util.ArrayList;

public class DijkstraArray {
	private ArrayList<DijksdraNode> Data;

	public DijkstraArray(ArrayList<DijksdraNode> data) {
		Data = data;
	}
	
	/**
	 * Adiciona o n� no final do Array
	 * @param node - n� a ser guardado
	 */
	public void addInEnd(DijksdraNode node) {
		Data.add(node);
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public boolean isInArray(Node node) {
		int index = getIndex(node);
		if (index == -1) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	public Double getNodesToOrigin(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node)).getNodesFronOrigin();
		}else {
			return null;
		}
	}
	
	
	public boolean setCompDistanceUntilOrigin(Node node, Double value) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setComparableDistanceUntilOrigin(value);
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	public Double getCompDistanceUntilOrigin(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node)).getComparableDistanceUntilOrigin();
		}else {
			return null;
		}
	}
	
	/**
	 * Retona DijksdraNode, que representa um n� 
	 * segundo a vis�o do algoritmo de Dijksdra .
	 * @param node
	 * @return DijksdraNode do Node passado
	 */
	public DijksdraNode getNode(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node));
		}else {
			return null;
		}
	}
	
	/**
	 * Retona o n� com a menor dist�ncia.
	 * @param node
	 * @return DijksdraNode do Node passado ou null, 
	 * caso DijkstraArray esteja vazia
	 */
	public DijksdraNode getNodeWithLeastDistance() {
		if (!isEmpty()) {
			DijksdraNode leastDistance = Data.get(0);
			for (DijksdraNode search : Data) {
				if (search.getDistanceFronOrigin() < leastDistance.getDistanceFronOrigin()) {
					leastDistance = search;
				}
			}
			return leastDistance;
		}else {
			return null;
		}
	}
	
	/**
	 * Retorna o indice do vertice armazenado
	 * @param node
	 * @return indice ou -1, caso o n� n�o seja encontrado
	 */
	public int getIndex(Node node) {
		int index = 0;
		for (DijksdraNode dinode : Data) {
			if (dinode.getNode().equals(node)) {
				return index;
			}else {
				++index;
			}
		}
		return -1;
		
	}
	
	/**
	 * Define um novo predecessor do n�. 
	 * @param node - n� que receber� a atualiza��o na predecessor
	 * @param previous - nova predecessor do n�
	 * @return boolean - Se a opera��o foi implementada com sucesso.
	 */
	public boolean setPrevious(Node node, DijksdraNode previous) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setPrevious(previous);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean setNodesUntilOrigin(Node node, Double quant) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setNodesFronOrigin(quant);
			return true;
		}
		return false;
	}
	
	/**
	 * Define uma nova dist�ncia do n� de origem para o respectivo n�. 
	 * @param node - n� que receber� a atualiza��o na dist�ncia
	 * @param distance - nova dist�ncia at� o n� de origem
	 * @return boolean - Se a opera��o foi implementada.
	 */
	public boolean setDistance(Node node, Double distance) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setDistanceFronOrigin(distance);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Recupera a dist�ncia do n� de origem para o respectivo n�. 
	 * @param node - n� do qual a dist�ncia ser� recuperada
	 * @return Double - Caso o n� exista; null, caso n�o exista.
	 */
	public Double getDistance(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node)).getDistanceFronOrigin();
		}else {
			return null;
		}
	}
	
	/**
	 * Remove um n� do Array de n�s, retornando um boolean 
	 * indicando o sucesso da opera��o.
	 * @param node - n� a ser removido
	 * @return boolean - True se a opera��o foi bem sucedida. 
	 * Caso o n� n�o esteja no Array, Falso ser� retornado
	 */
	public boolean removeNode(DijksdraNode node) {
		if (isInArray(node.getNode())) {
			Data.remove(getIndex(node.getNode()));
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		return Data.isEmpty();
	}
}
