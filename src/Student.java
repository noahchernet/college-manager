import java.util.ArrayList;

/**
 * Represents a student in the university/college
 */
public class Student {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String ID;
    private final Batch batch;
    private final Department department;

    /**
     * Initializes a student
     * @param firstName first name of the student
     * @param middleName middle name of the student
     * @param lastName last name of the student
     * @param ID ID of the student, set by the Admin of the Department
     */
    public Student(String firstName, String middleName, String lastName, String ID, Batch batch, Department department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ID = ID;
        this.batch = batch;
        this.department = department;
    }

    public Result getResultOfCourse(Course course, Teacher teacher){
        return Teacher.getResultOfStudent(this, course);
    }

    public void getSemesterResults(int semester){
        // TODO: generate all Results from Courses taken in the current semester (fetched from Batch)
    }

    public ArrayList<Course> getAllCourses(){
        // TODO: get courses from Department
    }

    public void sendFeedbackToTeacher(Teacher teacher){
        // TODO: implement a feedback sending mechanism that sends a feedback String to a Teacher of a Course
    }

    public String[] getName() {
        return new String[]{firstName, middleName, lastName};
    }

    public String getID() {
        return ID;
    }

    public Batch getBatch() {
        return batch;
    }

    public Department getDepartment() {
        return department;
    }
}
