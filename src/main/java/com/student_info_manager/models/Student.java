package com.student_info_manager.models;

import java.sql.*;
import java.util.ArrayList;

/**
 * Represents a student in the university/college
 */
public class Student extends BasePerson{
    private final String ID;
    private final String section;
    private final int batch;
    Connection results_db;
    Connection courses_db;

    /**
     * Initializes a student
     * @param firstName first name of the student
     * @param middleName middle name of the student
     * @param lastName last name of the student
     * @param section section the Student is learning in
     * @param department the department the student is studying under
     * @param ID ID of the student, set by the Admin of the Department
     */
    public Student(String firstName, String middleName, String lastName,
                   String section, int batch, String department, String ID) {
        super(firstName, middleName, lastName, department);
        this.section = section;
        this.ID = ID;
        this.batch = batch;

        try {
            results_db = DriverManager.getConnection(
                    "jdbc:sqlite:src/main/java/com/student_info_manager/databases/results.db");
            courses_db = DriverManager.getConnection(
                    "jdbc:sqlite:src/main/java/com/student_info_manager/databases/courses.db");
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
    }

    /**
     * Get the Result of the Course this Student is taking
     * @param course the Course the Student is taking
     * @return Result of the corresponding Course of the Student
     *         or null if the Student doesn't take the Course or the Course doesn't exist in the database
     */
    public Result getResultOfCourse(Course course){
        try {
            Statement resultsStmt = results_db.createStatement();
            ResultSet resultSet = resultsStmt.executeQuery("SELECT * FROM " +
                    this.getID().replace('/', '_') + " WHERE course='" + course.getTitle() + "'");
            if (!resultSet.isClosed()) {
                Result studentsResult = new Result(course, this);
                studentsResult.setQuiz(resultSet.getDouble("quiz"));
                studentsResult.setAttendance(resultSet.getDouble("attendance"));
                studentsResult.setTest_1(resultSet.getDouble("test_1"));
                studentsResult.setTest_2(resultSet.getDouble("test_2"));
                studentsResult.setAssignment_1(resultSet.getDouble("assignment_1"));
                studentsResult.setAssignment_2(resultSet.getDouble("assignment_2"));
                studentsResult.setFinalExam(resultSet.getDouble("final_exam"));
                resultSet.close();
                resultsStmt.close();
                return studentsResult;
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        return null;
    }

    public void getSemesterResults(int semester){
        // TODO: generate all Results from Courses taken in the current semester (fetched from Batch)
    }


    /**
     * Finds a Course in courses.db
     * @param title Title of the Course, case-sensitive
     * @return a Course if a matching title is found, null otherwise
     */
    public Course getCourse(String title) {
        try {
            ResultSet found_course =
                    courses_db.createStatement().executeQuery("SELECT * FROM courses WHERE title = '" + title + "'");

            // If a matching course title is found, make a Course object from the row  the title was found and return it
            if (!found_course.isClosed()) {
                return new Course(found_course.getString("title"), found_course.getInt("credit_hr"),
                        found_course.getInt("semester_given"), found_course.getString("prerequisite"),
                        found_course.getString("department"));
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        return null;
    }
    
    public ArrayList<Course> getSemesterCourses(int semester){
        try {
            ResultSet coursesTaking =
                    courses_db.createStatement().executeQuery("SELECT * FROM courses WHERE semester_given = " + semester +
                            " AND department = '" + this.getDepartment() + "'");

            // If a matching course title is found, make a Course object from the row  the title was found and return it
            if (!coursesTaking.isClosed()) {
                ArrayList<Course> courses = new ArrayList<>();
                while (coursesTaking.next()) {
                    courses.add(new Course(coursesTaking.getString("title"), coursesTaking.getInt("credit_hr"),
                            coursesTaking.getInt("semester_given"), coursesTaking.getString("prerequisite"),
                            coursesTaking.getString("department")));
                }
                return courses;
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        return null;
    }

    public ArrayList<Course> getAllCourses(){
        try {
            ResultSet coursesTaking =
                    courses_db.createStatement().executeQuery("SELECT * FROM courses WHERE department = '" + this.getDepartment() + "'");

            // If a matching course title is found, make a Course object from the row  the title was found and return it
            if (!coursesTaking.isClosed()) {
                ArrayList<Course> courses = new ArrayList<>();
                while (coursesTaking.next()) {
                    courses.add(new Course(coursesTaking.getString("title"), coursesTaking.getInt("credit_hr"),
                            coursesTaking.getInt("semester_given"), coursesTaking.getString("prerequisite"),
                            coursesTaking.getString("department")));
                }
                return courses;
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        return null;
    }

    public String getSection() {
        return section;
    }

    public String getID() {
        return ID;
    }

    public int getBatch() {
        return batch;
    }
}
