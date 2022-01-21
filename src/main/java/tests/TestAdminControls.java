package tests;

import com.student_info_manager.control.*;
import com.student_info_manager.models.*;
import org.junit.*;

import static junit.framework.TestCase.*;
import static org.junit.Assert.*;

/**
 * Tests the adding and removing methods of Admin
 */
public class TestAdminControls {
    Admin admin;
    Login login;

    @Before
    public void initiate() {
        login = new Login();
        admin = login.connectAdmin("mekonin_g", "3break343");
    }

    @Test
    public void testAddNewTeacher() {
        Teacher newTeacherToAdd = new Teacher("Melaku", "Kebede", "Gemechis", "Mechanics I: Statics", "C,D,E",
                "Mechanical Engineering");
        admin.addTeacher(newTeacherToAdd, "melaku_k", "garamond_serif78");

        // To re-establish connection with the database. Removing this line will throw a database
        // not found SQL exception
        login = new Login();
        Teacher newTeacherFromDatabase = login.connectTeacher("melaku_k", "garamond_serif78");

        assertArrayEquals(newTeacherToAdd.getName(), newTeacherFromDatabase.getName());
        assertEquals(newTeacherToAdd.getSectionsTeaching(), newTeacherFromDatabase.getSectionsTeaching());
        assertEquals(newTeacherToAdd.getCourseTeaching(), newTeacherFromDatabase.getCourseTeaching());
        assertEquals(newTeacherToAdd.getDepartment(), newTeacherFromDatabase.getDepartment());

        // Delete teacher to ensure when the test is rerun, melaku_k is not in the database
        admin.removeTeacher("melaku_k");
    }

    @Test
    public void testAddNewStudent() {
        Student newStudentToAdd = new Student("Melaku", "Kebede", "Gemechis", "A", 2019, "Food Science",
                "ETS0856/11");
        admin.addStudent(newStudentToAdd, newStudentToAdd.getID(), "garamond_serif78");

        // To re-establish connection with the database. Removing this line will throw a database
        // not found SQL exception
        login = new Login();
        Student newStudentFromDatabase = login.connectStudent(newStudentToAdd.getID(), "garamond_serif78");

        assertArrayEquals(newStudentToAdd.getName(), newStudentFromDatabase.getName());
        assertEquals(newStudentToAdd.getDepartment(), newStudentFromDatabase.getDepartment());
        assertEquals(newStudentToAdd.getBatch(), newStudentFromDatabase.getBatch());
        assertEquals(newStudentToAdd.getSection(), newStudentFromDatabase.getSection());
        assertEquals(newStudentToAdd.getID(), newStudentFromDatabase.getID());

        // Delete teacher to ensure when the test is rerun, melaku_k is not in the database
        admin.removeStudent(newStudentToAdd.getID());
    }
}
