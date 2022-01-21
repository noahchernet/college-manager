package com.student_info_manager.models;

import java.sql.*;

public class Admin extends BasePerson{
    public Admin(String firstName, String middleName, String lastName, String department) {
        super(firstName, middleName, lastName, department);
    }

    /**
     * Adds a Teacher to the database users.db
     * @param teacher new Teacher to be added.
     * @param username username of the new Teacher
     * @param password password of the new Teacher
     * @return true if teacher is added successfully
     *         false if there's a teacher with the same username already in the database
     */
    public boolean addTeacher(Teacher teacher, String username, String password) {
        try {
            // Establish connection to users.db database
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases" +
                    "/users.db");

            Statement stmt = c.createStatement();
            String password_hash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Check if there's a teacher with the same username
            ResultSet identical_usernames =
                    stmt.executeQuery("SELECT * FROM teachers WHERE username = '" + username + "'");
            if (!identical_usernames.isClosed())
                return false;

            stmt.executeUpdate(String.format("INSERT INTO teachers (username, first_name, middle_name, last_name, " +
                    "course_teaching, sections_teaching, department, password) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                    username, teacher.getName()[0], teacher.getName()[1], teacher.getName()[2],
                    teacher.getCourseTeaching(), teacher.getSectionsTeaching(), teacher.getDepartment(), password_hash));
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(1);
        }
        return true;
    }

    /**
     * Removes a teacher from the users.db database
     * @param username username of the teacher to be removed
     * @return true if the Teacher is removed successfully
     *         false if the Teacher was not found in the database
     */
    public boolean removeTeacher(String username) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases" +
                    "/users.db");

            Statement stmt = c.createStatement();

            // Check if there's a teacher with the same username
            ResultSet identical_username =
                    stmt.executeQuery("SELECT * FROM teachers WHERE username = '" + username + "'");
            if (identical_username.isClosed())
                return false;

            stmt.executeUpdate("DELETE FROM teachers WHERE username = '" + username + "'");

        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
        return true;
    }

    /**
     * Adds a Student to the database users.db
     * @param student new Student to be added.
     * @param ID DI of the new Teacher
     * @param password password of the new Student
     * @return true if the student is added successfully
     *         false if there's a student with the same ID already in the database
     */
    public boolean addStudent(Student student, String ID, String password) {
        try {
            // Establish connection to users.db database
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases" +
                    "/users.db");

            Statement stmt = c.createStatement();
            String password_hash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Check if there's a teacher with the same username
            ResultSet identical_usernames =
                    stmt.executeQuery("SELECT * FROM students WHERE ID = '" + ID + "'");
            if (!identical_usernames.isClosed())
                return false;

            stmt.executeUpdate("INSERT INTO students (ID, first_name, middle_name, last_name, section, batch, " +
                    "department, password) VALUES ('" + ID + "', '" + student.getName()[0] + "', '" + student.getName()[1] + "', '" + student.getName()[2] + "', '" +
                    student.getSection() + "', '" + student.getBatch() + "', '" + student.getDepartment() + "', '" + password_hash + "')");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(1);
        }
        return true;
    }

    /**
     * Removes a Student from the users.db database
     * @param ID ID of the Student to be removed
     * @return true if the Student is removed successfully
     *         false if the Student was not found in the database
     */
    public boolean removeStudent(String ID) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases" +
                    "/users.db");

            Statement stmt = c.createStatement();

            // Check if there's a teacher with the same username
            ResultSet identical_username =
                    stmt.executeQuery("SELECT * FROM students WHERE ID = '" + ID + "'");
            if (identical_username.isClosed())
                return false;

            stmt.executeUpdate("DELETE FROM students WHERE ID = '" + ID + "'");

        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
        return true;
    }

    public boolean addCourse(Course course) {
        // TODO: add a new course to the database
        return false;
    }

    public boolean removeCourse(Course course) {
        // TODO: removes course from the database
        return false;
    }

    public void updateResult(Student student, Result result) {
        // TODO: when database usage is implemented, make this method update the result of @student
    }

}
