package tests;

import com.student_info_manager.control.*;
import com.student_info_manager.models.*;
import org.junit.*;

import static junit.framework.TestCase.*;
import static org.junit.Assert.*;


public class TestLogin {
    Login login;

    @Before
    public void setupClass() {
        this.login = new Login();
    }

    @Test
    public void testStudentLogin() {
        Student a = login.connectStudent("ETS0101/12", "quick_brown");
        assertArrayEquals(a.getName(), new String[]{"Mekdes", "Eskindir", "Mamo"});
        assertEquals(a.getSection(), "C");
        assertEquals(a.getBatch(), 2021);
        assertEquals(a.getDepartment(), "Electrical Engineering");
    }

    @Test
    public void testTeacherLogin() {
        Teacher teacher = login.connectTeacher("hdesaleng", "evening_summer");
        assertArrayEquals(teacher.getName(), new String[]{"Haile", "Desaleng", "Gebru"});
        assertEquals(teacher.getCourseTeaching(), "Applied Mathematics I");
        assertEquals(teacher.getDepartment(), "Electrical Engineering");
        assertEquals(teacher.getSectionsTeaching(), "A,B");
    }

    @Test
    public void testAdminLogin() {
        Admin admin = login.connectAdmin("mekonin_g", "3break343");
        assertArrayEquals(admin.getName(), new String[]{"Mekonin", "Girma", "Abebe"});
        assertEquals(admin.getDepartment(), "Electrical Engineering");
    }

}
