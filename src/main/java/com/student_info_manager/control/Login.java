package com.student_info_manager.control;


import com.student_info_manager.models.*;

import java.sql.*;

public class Login {
    Connection c;

    /**
     * Initialize the connection to the database that will later on be used to log in an Admin, Teacher or Student
     */
    public Login() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases/users.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(1);
        }
    }

    /**
     * Establishes a connection to the database and returns a student if the @ID and @password have a match
     * @param ID identifying string of the student, a primary key in the 'students' table
     * @param password password to access the student
     * @return a new instance of Student if successful, null otherwise
     */
    public Student connectStudent(String ID, String password) {
        Student retrievedStudent = null;
        try {
            Statement stmt = c.createStatement();
            ResultSet result = stmt.executeQuery("SELECT ID, password FROM students");
            String password_hash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Iterate through the 'students' table to find a match
            while (result.next()) {
                String id_from_db = result.getString("ID");
                String password_from_db = result.getString("password");

                /*
                 * If the ID passed and the SHA-256 hash of @password match those in the database, return a new
                 * instance of Student based on the row selected
                 */
                if (id_from_db.equals(ID) &&
                        password_from_db.equals(password_hash)) {
                    result = stmt.executeQuery("SELECT * FROM students WHERE ID = '" + ID + "'");
                    retrievedStudent = new Student(result.getString("first_name"), result.getString("middle_name"),
                             result.getString("last_name"), result.getString("section") , result.getInt("batch")
                             , result.getString("department"), result.getString("ID"));
                     break;
                }
            }
            result.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(1);
        }
        // If no user is found, return null
        return retrievedStudent;
    }

    /**
     * Establishes a connection to the database and returns a Teacher if the @username and @password have a match
     * @param username identifying string of the teacher, a primary key in the 'teachers' table
     * @param password password to access the teacher
     * @return a new instance of Student if successful, null otherwise
     */
    public Teacher connectTeacher(String username, String password) {
        Teacher retrievedTeacher = null;
        try {
            Statement stmt = c.createStatement();
            ResultSet result = stmt.executeQuery("SELECT username, password FROM teachers");
            String password_hash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Iterate through the 'students' table to find a match
            while (result.next()) {
                String uname_from_db = result.getString("username");
                String password_from_db = result.getString("password");

                /*
                 * If the username passed and the SHA-256 hash of @password match those in the database, return a new
                 * instance of Student based on the row selected
                 */
                if (uname_from_db.equals(username) &&
                        password_from_db.equals(password_hash)) {
                    result = stmt.executeQuery("SELECT * FROM teachers WHERE username = '" + username + "'");
                    retrievedTeacher = new Teacher(result.getString("first_name"), result.getString("middle_name"),
                            result.getString("last_name"), result.getString("course_teaching"),
                            result.getString("sections_teaching"), result.getInt("batch_teaching"),
                            result.getString("department"));
                    break;
                }
            }
            result.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
        // If a teacher is not found or username and passwords don't match, return null
        return retrievedTeacher;
    }

    /**
     * Establishes a connection to the database and returns an Admin if the @username and @password have a match
     * @param username identifying string of the admin, a primary key in the 'teachers' table
     * @param password password to access the teacher
     * @return a new instance of Student if successful, null otherwise
     */
    public Admin connectAdmin(String username, String password) {
        Admin retrievedAdmin = null;
        try {
            Statement stmt = c.createStatement();
            ResultSet result = stmt.executeQuery("SELECT username, password FROM admins");
            String password_hash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Iterate through the 'students' table to find a match
            while (result.next()) {
                String uname_from_db = result.getString("username");
                String password_from_db = result.getString("password");

                /*
                 * If the username passed and the SHA-256 hash of @password match those in the database, return a new
                 * instance of Student based on the row selected
                 */
                if (uname_from_db.equals(username) &&
                        password_from_db.equals(password_hash)) {
                    result = stmt.executeQuery("SELECT * FROM admins WHERE username = '" + username + "'");
                    retrievedAdmin = new Admin(result.getString("first_name"), result.getString("middle_name"),
                            result.getString("last_name"), result.getString("department"));
                    break;
                }
            }
            result.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
        // If a teacher is not found or username and passwords don't match, return null
        return retrievedAdmin;
    }
}
