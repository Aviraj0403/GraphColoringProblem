import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GraphColoringApp extends JFrame implements ActionListener {

    private JLabel mainLabel;
    private JLabel vertexLabel;
    private JTextField vertexCountField;
    private JLabel edgeLabel;
    private JTextField edgeCountField;
    private JLabel colorLabel;
    private JTextField colorCountField;
    private JTextArea resultTextArea;
    private JButton generateButton;
    private JPanel graphPanel;

    private Graph graph;
    private Map<Integer, Integer> coloring;

    public GraphColoringApp() {
        setTitle("Graph Coloring Project");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        mainLabel = new JLabel("Graph Coloring Problem");
        mainLabel.setFont(new Font("Osward", Font.BOLD, 35));
        mainLabel.setForeground(Color.BLACK);
        mainLabel.setBounds(220, 30, 450, 45);
        add(mainLabel);

        vertexLabel = new JLabel("Number of Vertices:");
        vertexLabel.setFont(new Font("Osward", Font.BOLD, 15));
        vertexLabel.setForeground(Color.BLACK);
        vertexLabel.setBounds(20, 90, 200, 30);
        add(vertexLabel);

        vertexCountField = new JTextField();
        vertexCountField.setBounds(180, 90, 150, 30);
        add(vertexCountField);

        edgeLabel = new JLabel("Number of Edges:");
        edgeLabel.setFont(new Font("Osward", Font.BOLD, 15));
        edgeLabel.setForeground(Color.BLACK);
        edgeLabel.setBounds(20, 130, 200, 30);
        add(edgeLabel);

        edgeCountField = new JTextField();
        edgeCountField.setBounds(180, 130, 150, 30);
        add(edgeCountField);

        colorLabel = new JLabel("Number of Colors:");
        colorLabel.setFont(new Font("Osward", Font.BOLD, 15));
        colorLabel.setForeground(Color.BLACK);
        colorLabel.setBounds(20, 170, 200, 30);
        add(colorLabel);

        colorCountField = new JTextField();
        colorCountField.setBounds(180, 170, 150, 30);
        add(colorCountField);

        resultTextArea = new JTextArea();
        resultTextArea.setFont(new Font("Raleway", Font.BOLD, 14));
        resultTextArea.setBounds(345, 95, 450, 300);
        add(resultTextArea);

        generateButton = new JButton("Generate Graph");
        generateButton.setBackground(Color.BLACK);
        generateButton.setForeground(Color.WHITE);
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.setBounds(180, 220, 150, 30);
        generateButton.addActionListener(this);
        add(generateButton);

        graphPanel = new JPanel();
        graphPanel.setBounds(345, 95, 450, 300);
        add(graphPanel);

        graph = new Graph(0);
        coloring = new HashMap<>();

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setLocation(550, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        resultTextArea.setText(""); // Clear previous results
        graphPanel.removeAll();
        graphPanel.repaint();

        try {
            int vertexCount = Integer.parseInt(vertexCountField.getText());
            int edgeCount = Integer.parseInt(edgeCountField.getText());
            int colorCount = Integer.parseInt(colorCountField.getText());

            graph = new Graph(vertexCount);

            // Add edges based on user input
            for (int i = 0; i < edgeCount; i++) {
                int v = Integer.parseInt(JOptionPane.showInputDialog("Enter edge source (" + (i + 1) + " of " + edgeCount + "):"));
                resultTextArea.append(v + "-> ");
                int w = Integer.parseInt(JOptionPane.showInputDialog("Enter edge target (" + (i + 1) + " of " + edgeCount + "):"));
                graph.addEdge(v, w);
                resultTextArea.append(w + "_");
            }

            // Assign colors based on user input
            for (int v = 0; v < vertexCount; v++) {
                int color = Integer.parseInt(JOptionPane.showInputDialog("Enter color for vertex " + v + " (" + (v) + " to " + (colorCount - 1) + "):"));
                coloring.put(v, color);
            }

            // Display graph visualization
            GraphColoring graphColoring = new GraphColoring(graph, coloring);
            graphColoring.setPreferredSize(new Dimension(450, 300));
            graphPanel.add(graphColoring);
            graphPanel.revalidate();
            graphPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GraphColoringApp();
        });
    }
}

class GraphColoring extends JPanel {
    private Graph graph;
    private Map<Integer, Integer> coloring;

    public GraphColoring(Graph graph, Map<Integer, Integer> coloring) {
        this.graph = graph;
        this.coloring = coloring;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define node positions for visualization (you can customize these)
        int[] nodeX = {50, 150, 250, 100, 200};
        int[] nodeY = {100, 50, 100, 200, 200};

        // Define node colors
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

        // Draw edges
        for (int v = 0; v < graph.getVertexCount(); v++) {
            for (int w : graph.getAdjacentVertices(v)) {
                g.setColor(Color.BLACK);
                g.drawLine(nodeX[v], nodeY[v], nodeX[w], nodeY[w]);
            }
        }

        // Draw nodes and color them
        for (int v = 0; v < graph.getVertexCount(); v++) {
            g.setColor(colors[coloring.get(v)]);
            g.fillOval(nodeX[v] - 15, nodeY[v] - 15, 30, 30);

            g.setColor(Color.BLACK);
            g.drawOval(nodeX[v] - 15, nodeY[v] - 15, 30, 30);
        }
    }
}

class Graph {
    private int V; // Number of vertices
    private List<Integer>[] adjList;

    public Graph(int V) {
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
