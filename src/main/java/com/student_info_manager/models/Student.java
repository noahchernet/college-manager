package com.student_info_manager.models;

/**
 * Represents a student in the university/college
 */
public class Student extends BasePerson{
    private final String ID;
    private final String section;
    private final int batch;

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
    }

    public void getResultOfCourse(Course course, Teacher teacher){
        //return Teacher.getResultOfStudent(this, course);
    }

    public void getSemesterResults(int semester){
        // TODO: generate all Results from Courses taken in the current semester (fetched from Batch)
    }

    public void getAllCourses(){
        // TODO: get courses from Department
    }

    public void sendFeedbackToTeacher(Teacher teacher){
        // TODO: implement a feedback sending mechanism that sends a feedback String to a Teacher of a Course
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
