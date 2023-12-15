package lab;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessNumberGame extends JFrame {
    private int secretNumber;
    private int attemptsLeft = 3;

    private JTextField guessTextField;
    private JButton guessButton;

    public GuessNumberGame() {
        secretNumber = generateRandomNumber();

        setTitle("Игра в угадывание числа");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        initListeners();

        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Угадайте число от 0 до 20. У вас есть 3 попытки.");
        guessTextField = new JTextField();
        guessButton = new JButton("Угадать");

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.add(instructionLabel);
        centerPanel.add(guessTextField);
        centerPanel.add(guessButton);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void initListeners() {
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 21);
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());

            if (userGuess == secretNumber) {
                showResultMessage("Поздравляю! Вы угадали число.");
                System.exit(0);
            } else {
                attemptsLeft--;

                if (attemptsLeft > 0) {
                    String message = (userGuess < secretNumber) ? "Число больше." : "Число меньше.";
                    showResultMessage("Неверно. " + message + " У вас осталось попыток: " + attemptsLeft);
                } else {
                    showResultMessage("Вы исчерпали все попытки. Загаданное число: " + secretNumber);
                    System.exit(0);
                }
            }
        } catch (NumberFormatException ex) {
            showResultMessage("Введите числовое значение.");
        }
    }

    private void showResultMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Результат", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessNumberGame();
            }
        });
    }
}
