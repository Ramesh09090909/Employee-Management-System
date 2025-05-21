package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {

    JLabel heading;
    JButton clickhere;

    Splash() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1170, 650);
        setLocation(200, 50);
        setLayout(new BorderLayout());

        // Load and scale image to full frame
        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1170, 650, Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(i2);

        // Create JLabel with background image
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null); // to allow custom positioning
        add(background, BorderLayout.CENTER);

        // Heading
        heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(200, 30, 1000, 60);
        heading.setFont(new Font("Serif", Font.BOLD, 48));
        heading.setForeground(Color.RED);
        background.add(heading);

        // Button
        clickhere = new JButton("Click Here to Continue");
        clickhere.setBounds(400, 520, 300, 40);
        clickhere.setBackground(Color.BLACK);
        clickhere.setForeground(Color.WHITE);
        clickhere.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clickhere.addActionListener(this);
        background.add(clickhere);

        // Optional: blinking heading
        new Thread(() -> {
            try {
                while (true) {
                    heading.setVisible(false);
                    Thread.sleep(500);
                    heading.setVisible(true);
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Login(); // Assuming Login class exists
    }

    public static void main(String[] args) {
        new Splash();
    }
}
