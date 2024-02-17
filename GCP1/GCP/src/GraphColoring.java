import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import javax.swing.*;

public class GraphColoring extends JPanel {
    private Graph graph;
    private Map<Integer, Integer> coloring;
    private int vertexCount; // Number of vertices

    public GraphColoring(Graph graph, Map<Integer, Integer> coloring, int vertexCount) {
        this.graph = graph;
        this.coloring = coloring;
        this.vertexCount = vertexCount;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        if (vertexCount <= 0) {
            // Handle the case where the vertex count is invalid
            String errorMessage = "Invalid vertex count. Please enter a valid number of vertices.";
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(errorMessage);
            int x = (getWidth() - textWidth) / 2;
            int y = getHeight() / 2;
            g.drawString(errorMessage, x, y);
            return;
        }



        // Calculate node positions for visualization based on vertexCount
        int[] nodeX = new int[vertexCount];
        int[] nodeY = new int[vertexCount];
        int radius = 150;
        int centerX = 250;
        int centerY = 200;

        for (int v = 0; v < vertexCount; v++) {
            double angle = 2 * Math.PI * v / vertexCount;
            nodeX[v] = (int) (centerX + radius * Math.cos(angle));
            nodeY[v] = (int) (centerY + radius * Math.sin(angle));
        }

        // Draw edges
        for (int v = 0; v < vertexCount; v++) {
            for (int w : graph.getAdjacentVertices(v)) {
                g.setColor(Color.BLACK);
                g.drawLine(nodeX[v], nodeY[v], nodeX[w], nodeY[w]);
            }
        }

        // Define node colors
        Color[] colors = new Color[vertexCount]; // Create an array to hold colors

        // Draw nodes and color them
        for (int v = 0; v < vertexCount; v++) {
            int colorIndex = coloring.get(v);
            if (colors[colorIndex] == null) {
                // If the color is not assigned yet, assign it
                colors[colorIndex] = getRandomColor();
            }
            g.setColor(colors[colorIndex]);

            g.fillOval(nodeX[v] - 15, nodeY[v] - 15, 30, 30);

            g.setColor(Color.BLACK);
            g.drawOval(nodeX[v] - 15, nodeY[v] - 15, 30, 30);
        }
    }

    private Color getRandomColor() {
        // Generate a random color
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}
