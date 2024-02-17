import java.util.*;

public class Graph {
    private int V; // Number of vertices
    private List<Integer>[] adjList;

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++)
        {
            adjList[i] = new ArrayList<>();
//            System.out.println(adjList[i]);
        }
    }

    public void addEdge(int v, int w)
    {
        adjList[v].add(w);
        System.out.println(adjList[w]);
        adjList[w].add(v);
        System.out.println(adjList[v]);
    }

    public List<Integer> getAdjacentVertices(int v)
    {
        return adjList[v];
    }
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) {
            throw new IllegalArgumentException("Invalid vertex indices");
        }

        // Check if w is in the adjacency list of vertex v
        return adjList[v].contains(w);
    }
    public int getVertexCount()

    {
        return V;
    }
}
