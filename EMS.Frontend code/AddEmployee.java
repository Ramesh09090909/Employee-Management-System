package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField nameField, dobField, addressField, emailField, aadharField, fatherField, salaryField, phoneField, empIdField;
    JComboBox<String> educationCombo;
    JButton addButton, backButton;

    AddEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Employee Details");
        heading.setBounds(300, 20, 500, 40);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        Font labelFont = new Font("SAN_SERIF", Font.PLAIN, 18);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 150, 30);
        nameLabel.setFont(labelFont);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 80, 200, 30);
        add(nameField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 130, 150, 30);
        dobLabel.setFont(labelFont);
        add(dobLabel);

        dobField = new JTextField("dd-mm-yyyy");
        dobField.setBounds(200, 130, 200, 30);
        add(dobField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 180, 150, 30);
        addressLabel.setFont(labelFont);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(200, 180, 200, 30);
        add(addressField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 230, 150, 30);
        emailLabel.setFont(labelFont);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 230, 200, 30);
        add(emailField);

        JLabel aadharLabel = new JLabel("Aadhar No:");
        aadharLabel.setBounds(50, 280, 150, 30);
        aadharLabel.setFont(labelFont);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(200, 280, 200, 30);
        add(aadharField);

        JLabel fatherLabel = new JLabel("Father's Name:");
        fatherLabel.setBounds(450, 80, 150, 30);
        fatherLabel.setFont(labelFont);
        add(fatherLabel);

        fatherField = new JTextField();
        fatherField.setBounds(600, 80, 200, 30);
        add(fatherField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(450, 130, 150, 30);
        salaryLabel.setFont(labelFont);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(600, 130, 200, 30);
        add(salaryField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(450, 180, 150, 30);
        phoneLabel.setFont(labelFont);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(600, 180, 200, 30);
        add(phoneField);

        JLabel educationLabel = new JLabel("Highest Education:");
        educationLabel.setBounds(450, 230, 180, 30);
        educationLabel.setFont(labelFont);
        add(educationLabel);

        String[] educationOptions = {"High School", "Graduate", "Post Graduate", "PhD"};
        educationCombo = new JComboBox<>(educationOptions);
        educationCombo.setBounds(600, 230, 200, 30);
        add(educationCombo);

        JLabel empIdLabel = new JLabel("Employee ID:");
        empIdLabel.setBounds(450, 280, 150, 30);
        empIdLabel.setFont(labelFont);
        add(empIdLabel);

        empIdField = new JTextField();
        empIdField.setBounds(600, 280, 200, 30);
        add(empIdField);

        addButton = new JButton("Add Employee");
        addButton.setBounds(250, 350, 150, 40);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(450, 350, 150, 40);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        setSize(900, 500);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            try {
                String name = nameField.getText().trim();
                String dob = dobField.getText().trim();
                String address = addressField.getText().trim();
                String email = emailField.getText().trim();
                String aadhar = aadharField.getText().trim();
                String father = fatherField.getText().trim();
                String salary = salaryField.getText().trim();
                String phone = phoneField.getText().trim();
                String education = (String) educationCombo.getSelectedItem();
                String empId = empIdField.getText().trim();
                if (name.isEmpty() || dob.isEmpty() || address.isEmpty() || email.isEmpty() || aadhar.isEmpty() ||
                    father.isEmpty() || salary.isEmpty() || phone.isEmpty() || empId.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
    }

                Conn conn = new Conn();
                String query = "INSERT INTO employee VALUES('" + name + "','" + father + "','" + dob + "','" + salary + "','" + address + "','" + phone + "','" + email + "','" + education + "','" + aadhar + "','" + empId + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Employee Added Successfully");

                setVisible(false);
                new Home(); // Only if you have a Home class

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Home(); // Only if you have a Home class
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
