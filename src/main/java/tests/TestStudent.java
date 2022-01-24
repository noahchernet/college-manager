package tests;

import com.student_info_manager.control.*;
import com.student_info_manager.models.*;
import org.junit.*;

import static junit.framework.TestCase.*;


public class TestStudent {
    Student student;

    @Before
    public void initiation() {
        student = new Login().connectStudent("ETS0856/19", "SpaceSatellite");
    }

    @Test
    public void testGetResult() {
        Course courseStudentTakes = student.getCourse("Probability and Random Processes");
        Result resultOfCourseStudentTakes = student.getResultOfCourse(courseStudentTakes);

        assertEquals(resultOfCourseStudentTakes.getCourse(), courseStudentTakes);
        assertEquals(resultOfCourseStudentTakes.getStudent(), student);
        assertEquals(resultOfCourseStudentTakes.getQuiz(), 4, 0.001);
        assertEquals(resultOfCourseStudentTakes.getAttendance(), 3, 0.001);
        assertEquals(resultOfCourseStudentTakes.getTest_1(), 14, 0.001);
        assertEquals(resultOfCourseStudentTakes.getTest_2(), 10, 0.001);
        assertEquals(resultOfCourseStudentTakes.getAssignment_1(), 9, 0.001);
        assertEquals(resultOfCourseStudentTakes.getAssignment_2(), 10, 0.001);
        assertEquals(resultOfCourseStudentTakes.getFinalExam(), 42, 0.001);

    }
}
