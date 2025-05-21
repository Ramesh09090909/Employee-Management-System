CREATE DATABASE employeemanagementsystem;
SHOW DATABASES;
USE employeemanagementsystem;
CREATE TABLE login (
    username VARCHAR(30),
    password VARCHAR(30)
);
INSERT INTO login VALUES ('admin', 'admin');
SELECT * FROM login;
USE employeemanagementsystem;
DROP TABLE employee;
CREATE TABLE employee (
    Name VARCHAR(50),
    Father_name VARCHAR(50),
    DOB VARCHAR(40),
    Salary VARCHAR(10),
    Adress VARCHAR(50),
    Phone_no VARCHAR(100),
    Email VARCHAR(50),
    Education VARCHAR(40),
    Aadhar VARCHAR(50),
    Employee_id VARCHAR(20)
);