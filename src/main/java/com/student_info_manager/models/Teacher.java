package com.student_info_manager.models;

import java.sql.*;
import java.util.ArrayList;

public class Teacher extends BasePerson{
    private final String courseTeaching;
    private final String sectionsTeaching;
    private final int batchTeaching;

    public Teacher(String firstName, String middleName, String lastName, String courseTeaching,
                   String sectionsTeaching, int batchTeaching, String department) {
        super(firstName, middleName, lastName, department);
        this.courseTeaching = courseTeaching;
        this.sectionsTeaching = sectionsTeaching;
        this.batchTeaching = batchTeaching;
    }

    public boolean updateResult(Student student, Result newResult) {
        if (!newResult.getStudent().equals(student)) {
            return false;
        }
        String studentTableName = student.getID().replace('/', '_');
        try {
            Connection results_db = DriverManager.getConnection(
                    "jdbc:sqlite:src/main/java/com/student_info_manager/databases/results.db");
            Statement resultsStmt = results_db.createStatement();
            ResultSet resultSet = resultsStmt.executeQuery("SELECT * FROM " +
                    studentTableName + " WHERE course='" + newResult.getCourse().getTitle() + "'");

            if (resultSet.isClosed()) {
                resultsStmt.close();
                results_db.close();
                return false;
            }

            resultsStmt.executeUpdate("UPDATE " + studentTableName + " SET (quiz, attendance, test_1, test_2, " +
                    "assignment_1, assignment_2, final_exam) = (" +
                    newResult.getQuiz() + ", " +
                    newResult.getAttendance() + ", " +
                    newResult.getTest_1() + ", " +
                    newResult.getTest_2() + ", " +
                    newResult.getAssignment_1() + ", " +
                    newResult.getAssignment_2() + ", " +
                    newResult.getFinalExam() + ") WHERE course='" + newResult.getCourse().getTitle() + "'");
            resultsStmt.close();
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            sqlException.printStackTrace();
            System.exit(1);
            return false;
        }
        return true;
    }

    public ArrayList<Student> getMyStudents() {
        try {
            ArrayList<Student> students = new ArrayList<>();
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases" +
                    "/users.db");
            Statement stmt = c.createStatement();
            ResultSet result =
                    stmt.executeQuery("SELECT * FROM students WHERE department='" + this.getDepartment() + "'" );

            while (result.next()) {
                students.add(new Student(result.getString("first_name"), result.getString("middle_name"),
                        result.getString("last_name"), result.getString("section") , result.getInt("batch")
                        , result.getString("department"), result.getString("ID")));
            }
            return students;
        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public void processFeedback() {
        // TODO: when database usage is implemented, make this method go through all messages sent from and to each
        //  student and admin
    }

    public void replyFeedback(Student student) {
        // TODO: when database usage is implemented, make this method reply to the feedback of a Student
    }

    public void replyFeedback(Admin admin) {
        // TODO: when database usage is implemented, make this method reply to the feedback of an Admin
    }

    public String getCourseTeaching() {
        return courseTeaching;
    }


    /**
     * Finds a Course in courses.db
     * @param title Title of the Course, case-sensitive
     * @return a Course if a matching title is found, null otherwise
     */
    public Course getCourse(String title) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection courses_db = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager" +
                    "/databases" +
                    "/courses.db");
            Statement stmt = courses_db.createStatement();
            ResultSet found_course =
                    stmt.executeQuery("SELECT * FROM courses WHERE title = '" + title + "'");

            // If a matching course title is found, make a Course object from the row  the title was found and return it
            if (!found_course.isClosed()) {
                 Course course = new Course(found_course.getString("title"), found_course.getInt("credit_hr"),
                        found_course.getInt("semester_given"), found_course.getString("prerequisite"),
                        found_course.getString("department"));
                 found_course.close();
                 stmt.close();
                 courses_db.close();
                 return course;
            }

        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        return null;
    }

    public String getSectionsTeaching() {
        return sectionsTeaching;
    }

    public int getBatchTeaching() {
        return batchTeaching;
    }
}
