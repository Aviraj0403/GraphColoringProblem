import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphColoringApp {
    private int vertexCount;
    private List<Integer>[] adjacencyList;
    private Map<Integer, Integer> coloring;
    private int width;
    private int height;

    private GraphColoringApp() {
        vertexCount = 0;
        adjacencyList = new ArrayList[]{new ArrayList<>()};
        coloring = new HashMap<>();

        JFrame frame = new JFrame("Graph Coloring");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JTextField vertexCountField = new JTextField();
        JTextField edgeCountField = new JTextField();
        JTextField colorCountField = new JTextField();
        JButton generateButton = new JButton("Generate Graph");

        inputPanel.add(new JLabel("Number of Vertices:"));
        inputPanel.add(vertexCountField);
        inputPanel.add(new JLabel("Number of Edges:"));
        inputPanel.add(edgeCountField);
        inputPanel.add(new JLabel("Number of Colors:"));
        inputPanel.add(colorCountField);
        inputPanel.add(new JLabel("")); // Empty label for spacing
        inputPanel.add(generateButton);

        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);
            }
        };

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(graphPanel, BorderLayout.CENTER);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vertexCount = Integer.parseInt(vertexCountField.getText());
                    int edgeCount = Integer.parseInt(edgeCountField.getText());
                    int colorCount = Integer.parseInt(colorCountField.getText());

                    // Initialize adjacency list
                    adjacencyList = new ArrayList[vertexCount];
                    for (int i = 0; i < vertexCount; i++) {
                        adjacencyList[i] = new ArrayList<>();
                    }

                    // Create the graph by adding edges
                    for (int i = 0; i < edgeCount; i++) {
                        int v = (int) (Math.random() * vertexCount);
                        int w = (int) (Math.random() * vertexCount);
                        adjacencyList[v].add(w);
                        adjacencyList[w].add(v);
                    }

                    // Assign colors to vertices
                    coloring = graphColoring(vertexCount, adjacencyList, colorCount);

                    // Repaint the graph panel
                    graphPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.");
                }
            }
        });

        frame.setVisible(true);
    }

    private void drawGraph(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int radius = Math.min(panelWidth, panelHeight) / (2 * vertexCount);

        for (int v = 0; v < vertexCount; v++) {
            int x = (int) (panelWidth / 2 + radius * Math.cos(2 * Math.PI * v / vertexCount));
            int y = (int) (panelHeight / 2 + radius * Math.sin(2 * Math.PI * v / vertexCount));

            // Get the color for the vertex
            int color = coloring.getOrDefault(v, 0);

            // Set the color for drawing
            switch (color) {
                case 0:
                    g.setColor(Color.RED);
                    break;
                case 1:
                    g.setColor(Color.GREEN);
                    break;
                case 2:
                    g.setColor(Color.BLUE);
                    break;
                default:
                    g.setColor(Color.BLACK);
                    break;
            }

            // Draw the vertex
            g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);

            // Draw the vertex label
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(v), x, y);
        }
    }

    private Map<Integer, Integer> graphColoring(int V, List<Integer>[] adj, int colors) {
        Map<Integer, Integer> colorMap = new HashMap<>();
        int[] result = new int[V];
        boolean[] available = new boolean[V];

        for (int i = 0; i < V; i++) {
            result[i] = -1;
            available[i] = true;
        }

        result[0] = 0;

        for (int v = 1; v < V; v++) {
            for (int neighbor : adj[v]) {
                if (result[neighbor] != -1) {
                    available[result[neighbor]] = false;
                }
            }

            int color;
            for (color = 0; color < colors; color++) {
                if (available[color]) {
                    break;
                }
            }

            result[v] = color;

            for (int neighbor : adj[v]) {
                if (result[neighbor] != -1) {
                    available[result[neighbor]] = true;
                }
            }
        }

        for (int v = 0; v < V; v++) {
            colorMap.put(v, result[v]);
        }

        return colorMap;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GraphColoringApp();
        });
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
