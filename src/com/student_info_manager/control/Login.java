package com.student_info_manager.control;


import com.student_info_manager.models.*;
import com.student_info_manager.models.Student;

import java.sql.*;

public class Login {
    Connection c = null;

    public Login() {

        try {
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection("jdbc:sqlite:../databases/users.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void connectStudent(String ID, String password) {
        try {
            boolean userFound = false;
            Statement s = this.c.createStatement();
            ResultSet students= s.executeQuery("SELECT ID, password FROM students");

            while (students.next()) {
                String id = students.getString("ID");
                String pswd = students.getString("password");

                if (id.equals(ID) && pswd.equals(password)) {

                }
            }

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
