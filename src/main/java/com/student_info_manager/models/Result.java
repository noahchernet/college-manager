package com.student_info_manager.models;

import java.sql.*;

/**
 * Holds the result of a course for a student
 * Will be managed by a Teacher and a Department
 */
public class Result {
    private final Course course;
    private final Student student;
    private final String courseTitle;

    private Double quiz = null;
    private Double attendance = null;
    private Double test_1 = null;
    private Double test_2 = null;
    private Double assignment_1 = null;
    private Double assignment_2 = null;
    private Double finalExam = null;

    private String grade;


    /**
     * Initializes a Result object with a Course
     * @param course What subject(course) to hold results of
     * @param student Which student to carry the results of the course of
     */
    public Result(Course course, Student student) {
        this.course = course;
        this.student = student;

        try {
            Connection results_db = DriverManager.getConnection(
                    "jdbc:sqlite:src/main/java/com/student_info_manager/databases/results.db");
            Statement resultsStmt = results_db.createStatement();
            ResultSet scores = resultsStmt.executeQuery("SELECT * FROM " +
                    student.getID().replace('/', '_') + " WHERE course='" + course.getTitle() + "'");
            quiz = scores.getDouble("quiz");
            attendance = scores.getDouble("attendance");
            test_1 = scores.getDouble("test_1");
            test_2 = scores.getDouble("test_2");
            assignment_1 = scores.getDouble("assignment_1");
            assignment_2 = scores.getDouble("assignment_2");
            finalExam = scores.getDouble("final_exam");
            resultsStmt.close();
            results_db.close();
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        this.courseTitle = course.getTitle();
        this.evalGrade();
    }

    /**
     * Sets the whole results the student holds
     * @param quiz the quiz
     * @param attendance attendance score
     * @param test_1 score from the 1st tests.test
     * @param test_2 score from the 2nd tests.test
     * @param assignment_1 score from the 1st assignment
     * @param assignment_2 score from the 2nd assignment
     * @param finalExam score from the final exam
     */
    public void setResult(double quiz, double attendance, double test_1, double test_2, double assignment_1,
                 double assignment_2, double finalExam) {
        this.quiz = quiz;
        this.attendance = attendance;
        this.test_1 = test_1;
        this.test_2 = test_2;
        this.assignment_1 = assignment_1;
        this.assignment_2 = assignment_2;
        this.finalExam = finalExam;
    }

    public int getCourseCreditHr() { return course.getCreditHour();}

    public String getCourseTitle() { return courseTitle;}

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public String getGrade() {
        return grade;
    }

    public String evalGrade() {
        double total = 0.0;

        /* Calculate the grade */
        if (quiz != null && attendance != null && test_1 != null && test_2 != null && assignment_1 != null &&
                assignment_2 != null && finalExam != null) {
            total += quiz + attendance + test_1 + test_2 + assignment_1 + assignment_2 + finalExam;
            total = Math.round(total);

            if (total < 40)
                grade = "F";
            else if (total < 50)
                grade = "C-";
            else if (total < 60)
                grade = "C";
            else if (total < 65)
                grade = "C+";
            else if (total < 70)
                grade = "B-";
            else if (total < 75)
                grade = "B";
            else if (total < 80)
                grade = "B+";
            else if (total < 85)
                grade = "A-";
            else if (total < 90)
                grade = "A";
            else
                grade = "A+";
        }
        else
            grade = "I";
        return grade;
    }

    public double getQuiz() {
        return quiz;
    }

    public void setQuiz(Double quiz) {
        this.quiz = quiz;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    public double getTest_1() {
        return test_1;
    }

    public void setTest_1(Double test_1) {
        this.test_1 = test_1;
    }

    public double getTest_2() {
        return test_2;
    }

    public void setTest_2(Double test_2) {
        this.test_2 = test_2;
    }

    public double getAssignment_1() {
        return assignment_1;
    }

    public void setAssignment_1(Double assignment_1) {
        this.assignment_1 = assignment_1;
    }

    public double getAssignment_2() {
        return assignment_2;
    }

    public void setAssignment_2(Double assignment_2) {
        this.assignment_2 = assignment_2;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Double finalExam) {
        this.finalExam = finalExam;
    }

}
