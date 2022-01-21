package com.student_info_manager.models;

/**
 * Holds the result of a course for a student
 * Will be managed by a Teacher and a Department
 */
public class Result {
    private final Course course;
    private final Student student;
    private final Teacher teacher;
    private final Department department;

    private Double quiz = null;
    private Double attendance = 0.0;
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
     * @param teacher Which teacher the result of this course can be modified by
     * @param department The department that finally holds the responsibility of this object (the results of
     *                   the course of a student)
     */
    public Result(Course course, Student student, Teacher teacher, Department department) {
        this.course = course;
        this.student = student;
        this.teacher = teacher;
        this.department = department;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Department getDepartment() {
        return department;
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

    public Double getQuiz() {
        return quiz;
    }

    public void setQuiz(Double quiz) {
        this.quiz = quiz;
    }

    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    public Double getTest_1() {
        return test_1;
    }

    public void setTest_1(Double test_1) {
        this.test_1 = test_1;
    }

    public Double getTest_2() {
        return test_2;
    }

    public void setTest_2(Double test_2) {
        this.test_2 = test_2;
    }

    public Double getAssignment_1() {
        return assignment_1;
    }

    public void setAssignment_1(Double assignment_1) {
        this.assignment_1 = assignment_1;
    }

    public Double getAssignment_2() {
        return assignment_2;
    }

    public void setAssignment_2(Double assignment_2) {
        this.assignment_2 = assignment_2;
    }

    public Double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Double finalExam) {
        this.finalExam = finalExam;
    }

}
