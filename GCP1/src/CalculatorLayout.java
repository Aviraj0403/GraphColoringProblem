import javax.swing.*;
import java.awt.*;
public class CalculatorLayout extends JFrame {
    private JTextField display;
    private JPanel buttonPanel;

    public CalculatorLayout() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocation(200,300);
        setLayout(new BorderLayout());

        display = new JTextField(10);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorLayout calculator = new CalculatorLayout();
            calculator.setVisible(true);
        });
    }
}