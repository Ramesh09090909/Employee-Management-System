package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    JTextField searchField, nameField, dobField, addressField, emailField, aadharField, fatherField, salaryField, phoneField, educationField, empIdField;
    JButton removeButton, backButton, searchButton;
    String empId = "";

    RemoveEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Remove Employee");
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

        int x1 = 50, x2 = 200, x3 = 450, x4 = 600, y = 130, h = 30;

        addLabelAndField("Name:", x1, y, x2, nameField = new JTextField(), labelFont);
        addLabelAndField("Date of Birth", x1, y += 40, x2, dobField = new JTextField(), labelFont);
        addLabelAndField("Address:", x1, y += 40, x2, addressField = new JTextField(), labelFont);
        addLabelAndField("Email:", x1, y += 40, x2, emailField = new JTextField(), labelFont);
        addLabelAndField("Aadhar No:", x1, y += 40, x2, aadharField = new JTextField(), labelFont);

        y = 130;
        addLabelAndField("Father's Name:", x3, y, x4, fatherField = new JTextField(), labelFont);
        addLabelAndField("Salary:", x3, y += 40, x4, salaryField = new JTextField(), labelFont);
        addLabelAndField("Phone:", x3, y += 40, x4, phoneField = new JTextField(), labelFont);
        addLabelAndField("Education:", x3, y += 40, x4, educationField = new JTextField(), labelFont);
        addLabelAndField("Employee ID:", x3, y += 40, x4, empIdField = new JTextField(), labelFont);
        empIdField.setEditable(false);

        removeButton = new JButton("Remove");
        removeButton.setBounds(250, 400, 150, 40);
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(this);
        add(removeButton);

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

    void addLabelAndField(String label, int lx, int ly, int fx, JTextField field, Font font) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(lx, ly, 150, 30);
        jLabel.setFont(font);
        add(jLabel);

        field.setBounds(fx, ly, 200, 30);
        add(field);
    }

    void loadEmployeeData(String empIdToSearch) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM employee WHERE Employee_id = '" + empIdToSearch + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                empId = empIdToSearch;
                empIdField.setText(empId);
                nameField.setText(rs.getString("name"));
                fatherField.setText(rs.getString("Father_name"));
                dobField.setText(rs.getString("dob"));
                salaryField.setText(rs.getString("Salary"));
                addressField.setText(rs.getString("Adress"));
                phoneField.setText(rs.getString("phone_no"));
                emailField.setText(rs.getString("email"));
                educationField.setText(rs.getString("Education"));
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
        } else if (ae.getSource() == removeButton) {
            if (empId.equals("")) {
                JOptionPane.showMessageDialog(null, "Please search for an employee first.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Employee ID " + empId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Conn conn = new Conn();
                    String query = "DELETE FROM employee WHERE Employee_id = '" + empId + "'";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Employee Removed Successfully");
                    setVisible(false);
                    new Home();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
