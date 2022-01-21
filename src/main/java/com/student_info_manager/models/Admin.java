package com.student_info_manager.models;

public class Admin extends BasePerson{
    public Admin(String firstName, String middleName, String lastName, String department) {
        super(firstName, middleName, lastName, department);
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

}
