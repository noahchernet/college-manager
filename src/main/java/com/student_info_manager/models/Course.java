package com.student_info_manager.models;

/**
 * Represents a course, a single subject under a department's list of courses
 */
public class Course {
    private final String title;
    private final int credit_hour;
    private final int semester_given;
    private final String prerequisite;
    private final String department;

    /**
     * Initializes the of a course
     * @param title name of the course
     * @param credit_hour credit hour of the course
     * @param prerequisite the course required to learn before learning this course
     */
    public Course(String title, int credit_hour, int semester_given, String prerequisite, String department) {
        this.title = title;
        this.credit_hour = credit_hour;
        this.prerequisite = prerequisite;
        this.semester_given = semester_given;
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public int getCredit_hour() {
        return credit_hour;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public int getSemester_given() {
        return semester_given;
    }

    public String getDepartment() {
        return department;
    }
}
