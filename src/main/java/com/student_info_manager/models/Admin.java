package com.student_info_manager.models;

import java.sql.*;

public class Admin extends BasePerson{
    private Connection users_db;
    private Connection courses_db;
    Statement stmt;

    public Admin(String firstName, String middleName, String lastName, String department) {
        super(firstName, middleName, lastName, department);
        try {
            Class.forName("org.sqlite.JDBC");
            users_db = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases" +
                    "/users.db");
            courses_db = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/student_info_manager/databases" +
                    "/courses.db");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(1);
        }
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
            stmt = users_db.createStatement();
            String password_hash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Check if there's a teacher with the same username
            ResultSet identical_usernames =
                    stmt.executeQuery("SELECT * FROM teachers WHERE username = '" + username + "'");
            if (!identical_usernames.isClosed())
                return false;

            stmt.executeUpdate(String.format("INSERT INTO teachers (username, first_name, middle_name, last_name, " +
                    "course_teaching, sections_teaching, batch_teaching,department, password) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %d, '%s', '%s');",
                    username, teacher.getName()[0], teacher.getName()[1], teacher.getName()[2],
                    teacher.getCourseTeaching(), teacher.getSectionsTeaching(), teacher.getBatchTeaching(),
                    teacher.getDepartment(),
                    password_hash));
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
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
            stmt = users_db.createStatement();

            // Check if there's a teacher with the same username
            ResultSet identical_username =
                    stmt.executeQuery("SELECT * FROM teachers WHERE username = '" + username + "'");
            if (identical_username.isClosed())
                return false;

            stmt.executeUpdate("DELETE FROM teachers WHERE username = '" + username + "'");

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        return true;
    }

    /**
     * Adds a Student to the database users.db
     * @param student new Student to be added.
     * @param password password of the new Student
     * @return true if the student is added successfully
     *         false if there's a student with the same ID already in the database
     */
    public boolean addStudent(Student student, String password) {
        try {
            // Establish connection to users.db database
            stmt = users_db.createStatement();
            String password_hash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Check if there's a teacher with the same username
            ResultSet identical_usernames =
                    stmt.executeQuery("SELECT * FROM students WHERE ID = '" + student.getID() + "'");
            if (!identical_usernames.isClosed())
                return false;

            stmt.executeUpdate("INSERT INTO students (ID, first_name, middle_name, last_name, section, batch, " +
                    "department, password) VALUES ('" + student.getID() + "', '" + student.getName()[0] + "', '" + student.getName()[1] + "', '" + student.getName()[2] + "', '" +
                    student.getSection() + "', '" + student.getBatch() + "', '" + student.getDepartment() + "', '" + password_hash + "')");

            // Establish connection to results.db and courses.db to create a list of results for the new Student
            Connection results_db = DriverManager.getConnection(
                    "jdbc:sqlite:src/main/java/com/student_info_manager/databases/results.db");
            Connection courses_db = DriverManager.getConnection(
                    "jdbc:sqlite:src/main/java/com/student_info_manager/databases/courses.db");
            Statement resultsStmt = results_db.createStatement();
            Statement coursesStmt = courses_db.createStatement();

            resultsStmt.executeUpdate("CREATE TABLE " + student.getID().replace('/', '_') + "(" +
                    "course TEXT PRIMARY KEY NOT NULL, " +
                    "quiz FLOAT," +
                    "attendance FLOAT," +
                    "test_1 FLOAT," +
                    "test_2 FLOAT," +
                    "assignment_1 FLOAT," +
                    "assignment_2 FLOAT," +
                    "final_exam FLOAT" +
                    ")");

            ResultSet coursesStudentTakes = coursesStmt.executeQuery(
                    "SELECT * FROM courses WHERE department='" + student.getDepartment() + "'");

            while (coursesStudentTakes.next()) {
                resultsStmt.executeUpdate(
                        "INSERT INTO " + student.getID().replace('/', '_') +
                                " (course, quiz, attendance, test_1, test_2, assignment_1, assignment_2, final_exam) " +
                                "VALUES ('" + coursesStudentTakes.getString("title") +
                                "', null, null, null, null, null, null, null);");
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
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
            stmt = users_db.createStatement();

            // Check if there's a teacher with the same username
            ResultSet identical_username =
                    stmt.executeQuery("SELECT * FROM students WHERE ID = '" + ID + "'");
            if (identical_username.isClosed())
                return false;

            stmt.executeUpdate("DELETE FROM students WHERE ID = '" + ID + "'");

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        return true;
    }

    /**
     * Finds a Course in courses.db
     * @param title Title of the Course, case-sensitive
     * @return a Course if a matching title is found, null otherwise
     */
    public Course getCourse(String title) {
        try {
            ResultSet found_course =
                    stmt.executeQuery("SELECT * FROM courses WHERE title = '" + title + "'");

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

    public boolean addCourse(Course course) {
        try {
            stmt = courses_db.createStatement();

            // Check if there's a course with the same title
            ResultSet identical_titles =
                    stmt.executeQuery("SELECT * FROM courses WHERE title = '" + course.getTitle() + "'");
            if (!identical_titles.isClosed())
                return false;

            stmt.executeUpdate("INSERT INTO courses (title, credit_hr, semester_given, prerequisite, department) VALUES ('" +
                    course.getTitle() + "', '" +
                    course.getCredit_hour() + "', '" +
                    course.getSemester_given() + "', '" +
                    course.getPrerequisite() + "', '" +
                    course.getDepartment() + "')");

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }

        return true;
    }

    public boolean removeCourse(String title) {
        try {
            stmt = courses_db.createStatement();

            // Check if there's a teacher with the same username
            ResultSet found_course =
                    stmt.executeQuery("SELECT * FROM courses WHERE title = '" + title + "'");
            if (found_course.isClosed())
                return false;

            stmt.executeUpdate("DELETE FROM courses WHERE title  = '" + title + "'");

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
            System.exit(1);
        }
        return true;
    }

    public void updateResult(Student student, Result result) {
        // TODO: when database usage is implemented, make this method update the result of @student
    }

}
