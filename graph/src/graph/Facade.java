package graph;

public class Facade {

    private ExibicaoDoGrafo exibicao;

    public Facade(){
        this.exibicao = new MatrizAdjacencia();
    }

    public String BFS(Graph graph, Node s){
        return null;
    }

    public String DFS(Graph graph, Node s){
        return null;
    }

    public int getVertexNumber(Graph graph){
    	return graph.getVertices().size();}

    public int getEdgeNumber(Graph graph){
    	return graph.getArestas().size();}

    public float getMeanEdge(Graph graph){return 0;}

    public String graphRepresentation(Graph graph, String type){return "";}

    public String SCC(Graph graph){return "";}

    public String shortestPath(Node v1, Node v2){return "";}

    public String mst(Graph graph){return "";}


}
