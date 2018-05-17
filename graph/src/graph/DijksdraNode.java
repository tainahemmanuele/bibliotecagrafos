package graph;

import java.util.LinkedList;

public class DijksdraNode {
	private DijksdraNode previous;
	private Double distanceFronOrigin;
	private Node node;
	
	public DijksdraNode(DijksdraNode previous, Double distanceFronOrigin, Node node) {
		this.previous = previous;
		this.distanceFronOrigin = distanceFronOrigin;
		this.node = node;
	}

	/**
	 * 
	 * @return the previous
	 */
	public DijksdraNode getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(DijksdraNode previous) {
		this.previous = previous;
	}

	/**
	 * @return the distanceFronOrigin
	 */
	public Double getDistanceFronOrigin() {
		return distanceFronOrigin;
	}

	/**
	 * @param distanceFronOrigin the distanceFronOrigin to set
	 */
	public void setDistanceFronOrigin(Double distanceFronOrigin) {
		this.distanceFronOrigin = distanceFronOrigin;
	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

}
