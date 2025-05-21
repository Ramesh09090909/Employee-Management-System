package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ViewEmployee extends JFrame implements ActionListener {
    JTable table;
    JTextField empIdField;
    JButton searchBtn, printBtn, updateBtn, backBtn;
    DefaultTableModel model;

    ViewEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);

        JLabel searchLabel = new JLabel("Search by Employee ID:");
        topPanel.add(searchLabel);

        empIdField = new JTextField();
        empIdField.setPreferredSize(new Dimension(150, 25));
        topPanel.add(empIdField);

        searchBtn = new JButton("Search");
        searchBtn.addActionListener(this);
        topPanel.add(searchBtn);

        printBtn = new JButton("Print");
        printBtn.addActionListener(this);
        topPanel.add(printBtn);

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        topPanel.add(backBtn);

        add(topPanel, BorderLayout.NORTH);

        // Table setup
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Initial load
        loadAllEmployees();

        setSize(900, 500);
        setLocation(300, 100);
        setVisible(true);
    }

    void loadAllEmployees() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM employee");

            String[] columnNames = {
                "name", "Father_name", "dob", "Salary", "Adress",
                "phone", "email", "Education", "Aadhar", "Employee_id"
            };

            model = new DefaultTableModel(columnNames, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("name"),
                    rs.getString("Father_name"),
                    rs.getString("dob"),
                    rs.getString("Salary"),
                    rs.getString("Adress"),
                    rs.getString("phone_no"), 
                    rs.getString("email"),
                    rs.getString("Education"),
                    rs.getString("Aadhar"),
                    rs.getString("Employee_id")
                });
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadEmployeeById(String empId) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM employee WHERE Employee_id = '" + empId + "'");

            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("name"),
                    rs.getString("Father_name"),
                    rs.getString("dob"),
                    rs.getString("Salary"),
                    rs.getString("Adress"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("Education"),
                    rs.getString("Aadhar"),
                    rs.getString("Employee_id")
                });
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchBtn) {
            String empId = empIdField.getText().trim();
            if (!empId.equals("")) {
                loadEmployeeById(empId);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter an Employee ID to search.");
            }
        } else if (ae.getSource() == printBtn) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
