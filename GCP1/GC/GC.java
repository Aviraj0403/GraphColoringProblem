import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class GraphNode {
    private int x, y;
    private Color color;
    private List<GraphNode> connectedNodes;

    public GraphNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.WHITE;
        this.connectedNodes = new ArrayList<>();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void addConnectedNode(GraphNode node) {
        connectedNodes.add(node);
    }

    public List<GraphNode> getConnectedNodes() {
        return connectedNodes;
    }
}

/*class GraphPanel extends JPanel {
    private List<GraphNode> nodes;

    private boolean connectMode;
    private GraphNode firstSelectedNode;

    public GraphPanel() {
        nodes = new ArrayList<>();
        connectMode = false;
        firstSelectedNode = null;
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (connectMode) {
                    GraphNode clickedNode = findNodeAtPoint(e.getX(), e.getY());
                    if (clickedNode != null) {
                        if (firstSelectedNode == null) {
                            firstSelectedNode = clickedNode;
                        } else {
                            firstSelectedNode.addConnectedNode(clickedNode);
                            clickedNode.addConnectedNode(firstSelectedNode);
                            firstSelectedNode = null;
                            connectMode = false;
                        }
                        repaint();
                    }
                } else {
                    nodes.add(new GraphNode(e.getX(), e.getY()));
                    repaint();
                }
            }
        });
    }
*/
class GraphPanel extends JPanel {
    private List<GraphNode> nodes;
    private boolean connectMode;
    private GraphNode firstSelectedNode;

    public GraphPanel() {
        nodes = new ArrayList<>();
        connectMode = false;
        firstSelectedNode = null;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (connectMode) {
                    GraphNode clickedNode = findNodeAtPoint(e.getX(), e.getY());
                    if (clickedNode != null) {
                        if (firstSelectedNode == null) {
                            firstSelectedNode = clickedNode;
                        } else {
                            firstSelectedNode.addConnectedNode(clickedNode);
                            clickedNode.addConnectedNode(firstSelectedNode);
                            firstSelectedNode = null;
                            connectMode = false;
                        }
                        repaint();
                    }
                } else {
                    nodes.add(new GraphNode(e.getX(), e.getY()));
                    repaint();
                }
            }
        });
    }

    // other methods remain the same


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GraphNode node : nodes) {
            g.setColor(node.getColor());
            g.fillOval(node.getX() - 15, node.getY() - 15, 30, 30);
            g.setColor(Color.BLACK);
            g.drawOval(node.getX() - 15, node.getY() - 15, 30, 30);
        }
        g.setColor(Color.BLACK);
        for (GraphNode node : nodes) {
            int x1 = node.getX();
            int y1 = node.getY();
            for (GraphNode connectedNode : node.getConnectedNodes()) {
                int x2 = connectedNode.getX();
                int y2 = connectedNode.getY();

                double angle = Math.atan2(y2 - y1, x2 - x1);
                int x1Outline = (int) (x1 + 15 * Math.cos(angle));
                int y1Outline = (int) (y1 + 15 * Math.sin(angle));
                int x2Outline = (int) (x2 - 15 * Math.cos(angle));
                int y2Outline = (int) (y2 - 15 * Math.sin(angle));
                g.drawLine(x1Outline, y1Outline, x2Outline, y2Outline);
            }
        }
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public void clearColors() {
        for (GraphNode node : nodes) {
            node.setColor(Color.WHITE);
        }
        repaint();
    }

    public void enableConnectMode() {
        connectMode = true;
        firstSelectedNode = null;
    }

    private GraphNode findNodeAtPoint(int x, int y) {
        for (GraphNode node : nodes) {
            int nodeX = node.getX();
            int nodeY = node.getY();
            if (x >= nodeX - 15 && x <= nodeX + 15 && y >= nodeY - 15 && y <= nodeY + 15) {
                return node;
            }
        }
        return null;
    }
}

public class GC extends JFrame {
    private GraphPanel graphPanel;

    public GC() {
        graphPanel = new GraphPanel();
        JButton colorButton = new JButton("Color Graph");
        JButton connectButton = new JButton("Connect Nodes");
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        colorButton.setFont(buttonFont);
        connectButton.setFont(buttonFont);
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<GraphNode> nodes = graphPanel.getNodes();
                colorGraph(nodes);
                graphPanel.repaint();
            }
        });
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphPanel.enableConnectMode();
            }
        });
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(colorButton);
        buttonPanel.add(connectButton);
        add(graphPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setTitle("Graph Coloring Problem");
        JLabel headingLabel = new JLabel("<html><font color='red'>Graph</font> <font color='blue'>Coloring</font> <font color='green'>Problem</font></html>");
        headingLabel.setFont(new Font("Calibre", Font.BOLD, 36));
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        add(headingLabel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    private void colorGraph(List<GraphNode> nodes) {
        List<Color> availableColors = new ArrayList<>();
        availableColors.add(Color.RED);
        availableColors.add(Color.GREEN);
        availableColors.add(Color.BLUE);
        availableColors.add(Color.YELLOW);
        availableColors.add(Color.ORANGE);
        availableColors.add(Color.PINK);
        availableColors.add(Color.CYAN);
        availableColors.add(Color.MAGENTA);
        availableColors.add(Color.LIGHT_GRAY);
        availableColors.add(Color.DARK_GRAY);
        availableColors.add(Color.WHITE);
        availableColors.add(Color.BLACK);

        for (GraphNode node : nodes) {
            List<Color> neighborColors = new ArrayList<>();
            for (GraphNode neighbor : node.getConnectedNodes()) {
                neighborColors.add(neighbor.getColor());
            }
            for (Color color : availableColors) {
                if (!neighborColors.contains(color)) {
                    node.setColor(color);
                    break;
                }
            }
        }
        graphPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GC());
    }
}
