package com.student_info_manager.models;

public class Admin {
    private final String firstName;
    private final String middleName;
    private final String lastName;

    public Admin(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public boolean addTeacher(Teacher teacher) {
        // TODO: add the new teacher to the database
        return false;
    }

    public boolean removeTeacher(Teacher teacher) {
        // TODO: remove the teacher from the database
        return false;
    }

    public boolean addStudent(Student student) {
        // TODO: add the new student to the database
        return false;
    }

    public boolean removeStudent(Student student) {
        // TODO: remove the student from the database
        return false;
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

    public String[] getName() {
        return new String[]{firstName, middleName, lastName};
    }
}
