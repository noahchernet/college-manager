package com.student_info_manager.models;

import java.sql.*;

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
        }
        return true;
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

    public String getSectionsTeaching() {
        return sectionsTeaching;
    }

    public int getBatchTeaching() {
        return batchTeaching;
    }
}
