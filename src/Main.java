import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.Timer;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Study Timer");

        window.setSize(500, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        panel2.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("STUDY TIMER");
        title.setFont(new Font("Verdana",Font.BOLD,35));
        panel1.add(title);
        JLabel head = new JLabel("Welcome");
        panel1.add(head);

        JLabel question = new JLabel("How many minutes do you want to study?");
        question.setFont(new Font("Times New Roman",Font.ITALIC,25));
        panel1.add(question);

        JTextField timeInput = new JTextField();
        timeInput.setMaximumSize(new Dimension(200,30));
        panel1.add(timeInput);

        JLabel timer = new JLabel("00:00");
        timer.setFont(new Font("Arial", Font.BOLD, 40));
        panel1.add(timer);

        final Timer[] countdown = {null};

        JButton startButton = new JButton("START");
        panel2.add(startButton);

        startButton.addActionListener(e -> {

            if (countdown[0] != null && countdown[0].isRunning()) {
                return;
            }
            try {

                int time = Integer.parseInt(timeInput.getText());

                if (time <= 0) {
                    timer.setText("Enter a valid time!");
                    return;
                }

                int[] seconds = {time * 60};

                countdown[0] = new Timer(1000, event -> {

                    seconds[0]--;

                    if (seconds[0] <= 0) {
                        seconds[0] = 0;
                        timer.setText("HURRAAYYY!!! Study session complete!");
                        ((Timer) event.getSource()).stop();
                        return;
                    }

                    int minutes = seconds[0] / 60;
                    int remainingSeconds = seconds[0] % 60;

                    timer.setText(minutes + ":" + remainingSeconds);

                });

                countdown[0].start();

            } catch (NumberFormatException ex) {

                timer.setText("oh! Enter numbers only!");

            }
        });


        JButton resetButton = new JButton("RESET");
        panel2.add(resetButton);

        resetButton.addActionListener(e -> {

            if (countdown[0] != null) {
                countdown[0].stop();
            }

            timer.setText("00:00");
            timeInput.setText("");

        });

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
        mainpanel.add(panel1);
        mainpanel.add(panel2);


        window.add(mainpanel);

        window.setVisible(true);
    }
}