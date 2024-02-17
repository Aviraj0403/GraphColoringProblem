//package GraphColoringProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GCP extends JFrame implements ActionListener {

    JLabel mainL;
    JLabel verL;
    JTextField vertCF;
    JLabel edgeL;
    JTextField edgeCF;
    JLabel colorL;
    JLabel titleV;
    JTextField colorCF;
    JTextArea resultTA;
    JButton generateBtn,userBtn;
    JButton solveDecisionBtn;
    JButton solveOptimizationBtn;
    JPanel graphFrame;
    Graph graph;
    Map<Integer, Integer> coloring;

    public GCP() {
        setTitle("Graph Coloring Project");
        mainL = new JLabel("Graph Coloring Problem");
        mainL.setFont(new Font("Osward", Font.BOLD, 35));
        mainL.setForeground(Color.darkGray);

        mainL.setBounds(450, 25, 450, 45);
        add(mainL);



        verL = new JLabel("Numbers of Vertices: ");
        verL.setFont(new Font("Osward", Font.BOLD, 15));
        verL.setForeground(Color.BLACK);
        verL.setBounds(20, 90, 200, 30);
        add(verL);

        vertCF = new JTextField();
        vertCF.setBounds(180, 90, 150, 30);
        add(vertCF);

        edgeL = new JLabel("Number of Edges: ");
        edgeL.setFont(new Font("Osward", Font.BOLD, 15));
        edgeL.setForeground(Color.BLACK);
        edgeL.setBounds(20, 130, 200, 30);
        add(edgeL);

        edgeCF = new JTextField();
        edgeCF.setBounds(180, 130, 150, 30);
        add(edgeCF);

        colorL = new JLabel("Number of Colors:");
        colorL.setFont(new Font("Osward", Font.BOLD, 15));
        colorL.setForeground(Color.BLACK);
        colorL.setBounds(20, 170, 200, 30);
        add(colorL);

        colorCF = new JTextField();
        colorCF.setBounds(180, 170, 150, 30);
        add(colorCF);

        resultTA = new JTextArea();
        resultTA.setFont(new Font("Raleway", Font.BOLD, 14));
        resultTA.setBounds(20, 245, 350, 300);
        add(resultTA);

        generateBtn = new JButton("Generate Graph");
        generateBtn.setBackground(Color.BLACK);
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFont(new Font("Arial", Font.BOLD, 14));
        generateBtn.setBounds(180, 210, 150, 30);
        generateBtn.addActionListener(this);
        add(generateBtn);

        userBtn = new JButton("User Click");
        userBtn.setBackground(Color.BLACK);
        userBtn.setForeground(Color.WHITE);
        userBtn.setFont(new Font("Arial", Font.BOLD, 14));
        userBtn.setBounds(25, 210, 150, 30);
        userBtn.addActionListener(this);
        add(userBtn);


        solveDecisionBtn = new JButton("Solve M-Coloring Decision Problem");
        solveDecisionBtn.setBackground(Color.BLACK);
        solveDecisionBtn.setForeground(Color.WHITE);
        solveDecisionBtn.setFont(new Font("Arial", Font.BOLD, 14));
        solveDecisionBtn.setBounds(400, 550, 300, 30);
        
        solveDecisionBtn.addActionListener(this);
        add(solveDecisionBtn);

        solveOptimizationBtn = new JButton("Solve M-Coloring Optimization Problem");
        solveOptimizationBtn.setBackground(Color.BLACK);
        solveOptimizationBtn.setForeground(Color.WHITE);
        solveOptimizationBtn.setFont(new Font("Arial", Font.BOLD, 14));
        solveOptimizationBtn.setBounds(700, 550, 330, 30);
        solveOptimizationBtn.addActionListener(this);
        add(solveOptimizationBtn);


        titleV = new JLabel("Graph Visualization");
        titleV.setFont(new Font("Osward", Font.HANGING_BASELINE, 15));
        titleV.setForeground(Color.BLACK);
        titleV.setBounds(660, 97, 450, 25);
        add(titleV);

        graphFrame = new JPanel();
        graphFrame.setBounds(400, 95, 630, 450);
        add(graphFrame);


        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(1200, 650);
        setLocation(100, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateBtn) {
            generateGraph();
        } else if (e.getSource() == solveDecisionBtn) {
            solveMColoringDecision();
        } else if (e.getSource() == solveOptimizationBtn) {
            solveMColoringOptimization();
        }
        else if (e.getSource() == userBtn) {
            this.setVisible(false);
            new GC();
        }
    }

    private void generateGraph() {
        try {
            int vertexCount = Integer.parseInt(vertCF.getText());
            int edgeCount = Integer.parseInt(edgeCF.getText());
            int colorCount = Integer.parseInt(colorCF.getText());

            graph = new Graph(vertexCount);

            // Automatically add edges based on user input
//            if (edgeCount < vertexCount - 1) {
//                // If the number of edges is less than what's needed for a fully connected graph,
//                // randomly generate additional edges to make the graph connected.
//                Random random = new Random();
//                while (edgeCount < vertexCount - 1) {
//                    int v = random.nextInt(vertexCount);
//                    int w = random.nextInt(vertexCount);
//                    if (v != w && !graph.hasEdge(v, w)) {
//                        graph.addEdge(v, w);
//                        edgeCount++;}
//
//                Random random = new Random();
//                Set<String> addedEdges = new HashSet<>();
//
//                while (edgeCount < vertexCount - 1) {
//                    int v = random.nextInt(vertexCount);
//                    int w = random.nextInt(vertexCount);
//
//                    if (v != w) {
//                        // Ensure that (v, w) is unique and not already added in either direction
//                        String edge1 = v + "-" + w;
//                        String edge2 = w + "-" + v;
//
//                        if (!addedEdges.contains(edge1) && !addedEdges.contains(edge2)) {
//                            graph.addEdge(v, w);
//                            edgeCount++;
//                            // Add the edges to the set to mark them as added
//                            addedEdges.add(edge1);
//                            addedEdges.add(edge2);
//                        }
//                    }

//                }
//
//
//            } else {
//                for (int i = 0; i < edgeCount; i++) {
//                    int v = i % vertexCount;
//                    int w = (i + 1) % vertexCount;
//                    graph.addEdge(v, w);
//                }
//            }
//
//            coloring = greedyColoring(graph, colorCount);
                for (int i = 0; i < edgeCount; i++) {
                    int v = i % vertexCount;
                    int w = (i + 1) % vertexCount;
                    graph.addEdge(v, w);
                }
            coloring = greedyColoring(graph, colorCount);

                GraphColoring graphPanel = new GraphColoring(graph, coloring, vertexCount);
            graphPanel.setPreferredSize(new Dimension(500, 450));
            graphFrame.removeAll();
            graphFrame.add(graphPanel);
            graphFrame.revalidate();
            graphFrame.repaint();
            graphFrame.setVisible(true);

            boolean isMColorable = isProperMColoring(graph, coloring, colorCount);

            if (isMColorable) {
                resultTA.setText("Found an optimal " + colorCount + "-coloring for the graph.");
            } else {
                resultTA.setText("No optimal " + colorCount + "-coloring found for the graph.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(graphFrame, "Invalid input. Please enter valid numbers.");
        }
    }

    private Map<Integer, Integer> greedyColoring(Graph graph, int numColors) {
        Map<Integer, Integer> coloring = new HashMap<>();
        List<Integer> availableColors = new ArrayList<>();

        for (int v = 0; v < graph.getVertexCount(); v++) {
            availableColors.clear();

            for (int c = 0; c < numColors; c++) {
                availableColors.add(c);
            }

            for (int neighbor : graph.getAdjacentVertices(v)) {
                if (coloring.containsKey(neighbor)) {
                    int neighborColor = coloring.get(neighbor);
                    availableColors.remove(Integer.valueOf(neighborColor));
                }
            }

            int chosenColor = availableColors.get(0);
            coloring.put(v, chosenColor);
        }

        return coloring;
    }

    private boolean isProperMColoring(Graph graph, Map<Integer, Integer> coloring, int numColors) {
        for (int v = 0; v < graph.getVertexCount(); v++) {
            int vertexColor = coloring.get(v);
            if (vertexColor < 0 || vertexColor >= numColors) {
                return false;
            }
            List<Integer> adjacentVertices = graph.getAdjacentVertices(v);
            for (int adjacentVertex : adjacentVertices) {
                int adjacentColor = coloring.get(adjacentVertex);
                if (adjacentColor == vertexColor) {
                    return false;
                }
            }
        }
        return true;
    }

    private void solveMColoringDecision() {
        int numColors = Integer.parseInt(colorCF.getText());
        boolean isMColorable = solveMColoringDecision(graph, numColors);

        if (isMColorable) {
            resultTA.setText("The graph is " + numColors + "-colorable.");
        } else {
            resultTA.setText("The graph is not " + numColors + "-colorable.");
        }
    }

    private boolean solveMColoringDecision(Graph graph, int numColors) {
        int[] coloring = new int[graph.getVertexCount()];
        return solveMColoringUtil(graph, coloring, numColors, 0);
    }

    private boolean solveMColoringUtil(Graph graph, int[] coloring, int numColors, int vertex) {
        if (vertex == graph.getVertexCount()) {
            return true;
        }

        for (int color = 0; color < numColors; color++) {
            if (isSafe(graph, vertex, coloring, color)) {
                coloring[vertex] = color;
                if (solveMColoringUtil(graph, coloring, numColors, vertex + 1)) {
                    return true;
                }
                coloring[vertex] = -1;
            }
        }

        return false;
    }

    private boolean isSafe(Graph graph, int vertex, int[] coloring, int color) {
        List<Integer> adjacentVertices = graph.getAdjacentVertices(vertex);
        for (int adjacentVertex : adjacentVertices) {
            if (coloring[adjacentVertex] == color) {
                return false;
            }
        }
        return true;
    }

    private void solveMColoringOptimization() {
        int numColors = Integer.parseInt(colorCF.getText());
        coloring = new HashMap<>();
        boolean isOptimal = solveMColoring(graph, coloring, numColors);

        if (isOptimal) {
            resultTA.setText("Found an optimal " + numColors + "-coloring for the graph.");
        } else {
            resultTA.setText("No optimal " + numColors + "-coloring found for the graph.");
        }

        GraphColoring graphPanel = new GraphColoring(graph, coloring, graph.getVertexCount());
        graphPanel.setPreferredSize(new Dimension(500, 450));
        graphFrame.removeAll();
        graphFrame.add(graphPanel);
        graphFrame.revalidate();
        graphFrame.repaint();
        graphFrame.setVisible(true);
    }

    private boolean solveMColoring(Graph graph, Map<Integer, Integer> coloring, int numColors) {
        int[] coloringArray = new int[graph.getVertexCount()];
        boolean isOptimal = solveMColoring(graph, coloringArray, numColors, 0);
        for (int v = 0; v < graph.getVertexCount(); v++) {
            coloring.put(v, coloringArray[v]);
        }
        return isOptimal;
    }

    private boolean solveMColoring(Graph graph, int[] coloring, int numColors, int vertex) {
        if (vertex == graph.getVertexCount()) {
            return true;
        }

        for (int color = 0; color < numColors; color++) {
            if (isSafe(graph, vertex, coloring, color)) {
                coloring[vertex] = color;
                if (solveMColoring(graph, coloring, numColors, vertex + 1)) {
                    return true;
                }
                coloring[vertex] = -1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        new GCP();
    }
}
