package lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorderLayoutExample extends JFrame {
    private Timer timer;

    public BorderLayoutExample() {
        setTitle("Пример BorderLayout с обработкой событий мыши");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel centerLabel = new JLabel("ЦАО", JLabel.CENTER);
        JLabel westLabel = new JLabel("ЗАО", JLabel.CENTER);
        JLabel southLabel = new JLabel("ЮАО", JLabel.CENTER);
        JLabel northLabel = new JLabel("САО", JLabel.CENTER);
        JLabel eastLabel = new JLabel("ВАО", JLabel.CENTER);

        add(centerLabel, BorderLayout.CENTER);
        add(westLabel, BorderLayout.WEST);
        add(southLabel, BorderLayout.SOUTH);
        add(northLabel, BorderLayout.NORTH);
        add(eastLabel, BorderLayout.EAST);

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                Point mouseLocation = getMousePosition();
                if (mouseLocation != null) {
                    String region = getRegion(mouseLocation);
                    JOptionPane.showMessageDialog(null, "Добро пожаловать в " + region);
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                timer.restart();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String getRegion(Point point) {
        int width = getWidth() / 3;
        int height = getHeight() / 3;

        if (point.y < height) {
            return "САО";
        } else if (point.y > 2 * height) {
            return "ЮАО";
        } else {
            if (point.x < width) {
                return "ЗАО";
            } else if (point.x > 2 * width) {
                return "ВАО";
            } else {
                return "ЦАО";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BorderLayoutExample::new);
    }
}
