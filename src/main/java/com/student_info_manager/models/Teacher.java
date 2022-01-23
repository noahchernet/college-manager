package com.student_info_manager.models;

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
