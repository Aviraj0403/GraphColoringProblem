//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Graph Coloring");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            int vertexCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of vertices:"));
//            Graph graph = new Graph(vertexCount);
//
//            int edgeCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of edges:"));
//            for (int i = 0; i < edgeCount; i++) {
//                int v = Integer.parseInt(JOptionPane.showInputDialog("Enter edge source (0 to " + (vertexCount - 1) + "):"));
//                int w = Integer.parseInt(JOptionPane.showInputDialog("Enter edge target (0 to " + (vertexCount - 1) + "):"));
//                graph.addEdge(v, w);
//            }
//
//            int colorCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of colors:"));
//            Map<Integer, Integer> coloring = new HashMap<>();
//            for (int v = 0; v < vertexCount; v++) {
//                int color = Integer.parseInt(JOptionPane.showInputDialog("Enter color for vertex " + v + " (0 to " + (colorCount - 1) + "):"));
//                coloring.put(v, color);
//            }
//
//            GraphColoring graphPanel = new GraphColoring(graph, coloring);
//            frame.add(graphPanel);
//            frame.setSize(400, 300);
//            frame.setVisible(true);
//        });
//    }
//}
