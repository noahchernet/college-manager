package com.student_info_manager.models;

public class Teacher {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String courseTeaching;
    private final char[] sectionsTeaching;
    private final String department;

    public Teacher(String firstName, String middleName, String lastName, String courseTeaching, char[] sectionsTeaching, String department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.courseTeaching = courseTeaching;
        this.sectionsTeaching = sectionsTeaching;
        this.department = department;
    }

    static public void getResultOfStudent(Student student, Course course){
        // TODO: Pull result of a course of a student from database
    }

    public void updateResult(Student student, Result result) {
        // TODO: when database usage is implemented, make this method update the result of @student
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

    public String[] getName() {
        return new String[]{firstName, middleName, lastName};
    }

    public String getCourseTeaching() {
        return courseTeaching;
    }

    public char[] getSectionsTeaching() {
        return sectionsTeaching;
    }

    public String getDepartment() {
        return department;
    }
}
