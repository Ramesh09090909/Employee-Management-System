package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField searchField, nameField, dobField, addressField, emailField, aadharField, fatherField, salaryField, phoneField;
    JComboBox<String> educationCombo;
    JLabel empIdLabel;
    JButton updateButton, backButton, searchButton;
    String empId = "";

    UpdateEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(300, 20, 500, 40);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        Font labelFont = new Font("SAN_SERIF", Font.PLAIN, 18);

        JLabel searchLabel = new JLabel("Enter Employee ID:");
        searchLabel.setBounds(50, 80, 180, 30);
        searchLabel.setFont(labelFont);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(220, 80, 150, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(390, 80, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 130, 150, 30);
        nameLabel.setFont(labelFont);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 130, 200, 30);
        add(nameField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 180, 150, 30);
        dobLabel.setFont(labelFont);
        add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(200, 180, 200, 30);
        add(dobField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 230, 150, 30);
        addressLabel.setFont(labelFont);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(200, 230, 200, 30);
        add(addressField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 280, 150, 30);
        emailLabel.setFont(labelFont);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 280, 200, 30);
        add(emailField);

        JLabel aadharLabel = new JLabel("Aadhar No:");
        aadharLabel.setBounds(50, 330, 150, 30);
        aadharLabel.setFont(labelFont);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(200, 330, 200, 30);
        add(aadharField);

        JLabel fatherLabel = new JLabel("Father's Name:");
        fatherLabel.setBounds(450, 130, 150, 30);
        fatherLabel.setFont(labelFont);
        add(fatherLabel);

        fatherField = new JTextField();
        fatherField.setBounds(600, 130, 200, 30);
        add(fatherField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(450, 180, 150, 30);
        salaryLabel.setFont(labelFont);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(600, 180, 200, 30);
        add(salaryField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(450, 230, 150, 30);
        phoneLabel.setFont(labelFont);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(600, 230, 200, 30);
        add(phoneField);

        JLabel educationLabel = new JLabel("Highest Education:");
        educationLabel.setBounds(450, 280, 180, 30);
        educationLabel.setFont(labelFont);
        add(educationLabel);

        String[] educationOptions = {"High School", "Graduate", "Post Graduate", "PhD"};
        educationCombo = new JComboBox<>(educationOptions);
        educationCombo.setBounds(600, 280, 200, 30);
        add(educationCombo);

        JLabel empIdStaticLabel = new JLabel("Employee ID:");
        empIdStaticLabel.setBounds(450, 330, 150, 30);
        empIdStaticLabel.setFont(labelFont);
        add(empIdStaticLabel);

        empIdLabel = new JLabel("");
        empIdLabel.setBounds(600, 330, 200, 30);
        empIdLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        add(empIdLabel);

        updateButton = new JButton("Update Employee");
        updateButton.setBounds(250, 400, 150, 40);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(450, 400, 150, 40);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        setSize(900, 520);
        setLocation(300, 100);
        setVisible(true);
    }

    void loadEmployeeData(String empIdToSearch) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM employee WHERE Employee_id = '" + empIdToSearch + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                empId = empIdToSearch;
                empIdLabel.setText(empId);
                nameField.setText(rs.getString("name"));
                fatherField.setText(rs.getString("Father_name"));
                dobField.setText(rs.getString("dob"));
                salaryField.setText(rs.getString("Salary"));
                addressField.setText(rs.getString("Adress"));
                phoneField.setText(rs.getString("phone"));
                emailField.setText(rs.getString("email"));
                educationCombo.setSelectedItem(rs.getString("Education"));
                aadharField.setText(rs.getString("Aadhar"));
            } else {
                JOptionPane.showMessageDialog(null, "Employee ID not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String id = searchField.getText().trim();
            if (!id.equals("")) {
                loadEmployeeData(id);
            }
        } else if (ae.getSource() == updateButton) {
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

                Conn conn = new Conn();
                String query = "UPDATE employee SET name = '" + name + "', Father_name = '" + father +
                        "', dob = '" + dob + "', Salary = '" + salary + "', Adress = '" + address +
                        "', phone_no = '" + phone + "', email = '" + email + "', Education = '" + education +
                        "', Aadhar = '" + aadhar + "' WHERE Employee_id = '" + empId + "'";

                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
                setVisible(false);
                new Home();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee();
    }
}