package graph;

import java.util.Objects;

public class Node {


	int valor;

    public Node(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return valor == node.valor;
    }

    @Override
    public int hashCode() {

        return Objects.hash(valor);
    }
    
    @Override
	public String toString() {
		return ""+valor;
	}

}
