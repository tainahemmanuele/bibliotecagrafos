package graph;

public class Aresta {

    private Node node1;
    private Node node2;
    private int peso;

    public Aresta(Node v1, Node v2) {
        this.node1 = v1;
        this.node2 = v2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getV2() {
        return node2;
    }

    public int getPeso() {
        return peso;
    }

    public boolean temAresta(Node v1, Node v2){
        return (this.node1 == v2 && this.node2 == v1) || (this.node1 == v1 && this.node2 == v2);
    }
    public boolean contemVertice(Node v){
        return node1.equals(v) || node2.equals(v);
    }
}
