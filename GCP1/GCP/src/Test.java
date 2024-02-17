import java.util.*;

class G1 {
    private int V;
    private List<Integer>[] adjList;

    public G1(int V) {
        this.V = V;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v);
    }

    public List<Integer> getAdjacentVertices(int v) {
        return adjList[v];
    }

    public int getVertexCount() {
        return V;
    }
}

public class Test{
    private static void greedyColoring(Graph graph) {
        int V = graph.getVertexCount();
        int[] result = new int[V];

        // Initialize all vertices as uncolored
        Arrays.fill(result, -1);

        // Assign the first color to the first vertex
        result[0] = 0;

        // Available colors. Initially, all colors are available.
        boolean[] available = new boolean[V];
        Arrays.fill(available, true);

        // Assign colors to the remaining V-1 vertices
        for (int v = 1; v < V; v++) {
            // Mark the colors of adjacent vertices as unavailable
            for (int neighbor : graph.getAdjacentVertices(v)) {
                if (result[neighbor] != -1) {
                    available[result[neighbor]] = false;
                }
            }

            // Find the first available color
            int color;
            for (color = 0; color < V; color++) {
                if (available[color]) {
                    break;
                }
            }

            result[v] = color; // Assign the found color

            // Reset the availability of colors for the next vertex
            Arrays.fill(available, true);
        }

        // Print the results
        for (int v = 0; v < V; v++) {
            System.out.println("Vertex " + v + " is assigned color " + result[v]);
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        greedyColoring(graph);
    }
}
