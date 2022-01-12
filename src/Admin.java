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
    }

    public boolean removeTeacher(Teacher teacher) {
        // TODO: remove the teacher from the database
    }

    public boolean addStudent(Student student) {
        // TODO: add the new student to the database
    }

    public boolean removeStudent(Student student) {
        // TODO: remove the student from the database
    }

    public boolean addCourse(Course course) {
        // TODO: add a new course to the database
    }

    public boolean removeCourse(Course course) {
        // TODO: removes course from the database
    }

    public void updateResult(Student student, Result result) {
        // TODO: when database usage is implemented, make this method update the result of @student
    }

    public String[] getName() {
        return new String[]{firstName, middleName, lastName};
    }
}
