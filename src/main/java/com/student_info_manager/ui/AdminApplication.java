package com.student_info_manager.ui;

import com.student_info_manager.models.Admin;
import com.student_info_manager.models.Course;
import com.student_info_manager.models.Student;
import com.student_info_manager.models.Teacher;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminApplication extends Application {
    private Admin admin;
    Stage addNewStudentDialogStage = new Stage();
    Stage addNewTeacherDialogStage = new Stage();
    Stage addNewCourseDialogStage = new Stage();

    // Students tab
    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn idColStudentsTable;
    @FXML
    private TableColumn firstNameColStudentsTable;
    @FXML
    private TableColumn middleNameColStudentsTable;
    @FXML
    private TableColumn lastNameColStudentsTable;
    @FXML
    private TableColumn departmentColStudentsTable;
    @FXML
    private TableColumn batchColStudentsTable;
    @FXML
    private TableColumn sectionColStudentsTable;

    @FXML
    private TextField studentID;
    @FXML
    private TextField studentFirstName;
    @FXML
    private TextField studentMiddleName;
    @FXML
    private TextField studentLastName;
    @FXML
    private TextField studentDepartment;
    @FXML
    private TextField studentBatch;
    @FXML
    private TextField studentSection;
    @FXML
    private TextField studentPassword;
    @FXML
    private TextField studentPasswordReentered;


    // Teachers tab
    @FXML
    private TableView<Teacher> teachersTable;
    @FXML
    private TableColumn firstNameColsTeachersTable;
    @FXML
    private TableColumn middleNameColsTeachersTable;
    @FXML
    private TableColumn lastNameColsTeachersTable;
    @FXML
    private TableColumn departmentColTeachersTable;
    @FXML
    private TableColumn courseTeachingColTeachersTable;
    @FXML
    private TableColumn sectionsTeachingColTeachersTable;
    @FXML
    private TableColumn batchTeachingColTeachersTable;

    @FXML
    private TextField teacherUname;
    @FXML
    private TextField teacherFirstName;
    @FXML
    private TextField teacherMiddleName;
    @FXML
    private TextField teacherLastName;
    @FXML
    private TextField teacherDepartment;
    @FXML
    private TextField courseTeaching;
    @FXML
    private TextField teacherBatchTeaching;
    @FXML
    private TextField teacherSectionsTeaching;
    @FXML
    private TextField teacherPassword;
    @FXML
    private TextField teacherPasswordReentered;

    // Courses Tab
    @FXML
    private TableView<Course> coursesTable ;
    @FXML
    private TableColumn titleColCoursesTable;
    @FXML
    private TableColumn creditHrColCoursesTable;
    @FXML
    private TableColumn semesterGivenColCoursesTable;
    @FXML
    private TableColumn departmentColCoursesTable;
    @FXML
    private TableColumn prerequisiteColCoursesTable;


    @FXML
    private TextField courseTitle;
    @FXML
    private TextField courseCreditHr;
    @FXML
    private TextField courseSemesterGiven;
    @FXML
    private TextField courseDepartment;
    @FXML
    private TextField coursePrerequisite;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("admin_view.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Welcome back " + admin.getName()[0] + "!");
        primaryStage.setScene(scene);
        primaryStage.show();

        idColStudentsTable.setCellValueFactory(new PropertyValueFactory<>("ID"));
        firstNameColStudentsTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameColStudentsTable.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        lastNameColStudentsTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        departmentColStudentsTable.setCellValueFactory(new PropertyValueFactory<>("department"));
        batchColStudentsTable.setCellValueFactory(new PropertyValueFactory<>("batch"));
        sectionColStudentsTable.setCellValueFactory(new PropertyValueFactory<>("section"));

        studentsTable.getItems().addAll(admin.getAllStudents());

        firstNameColsTeachersTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameColsTeachersTable.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        lastNameColsTeachersTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        departmentColTeachersTable.setCellValueFactory(new PropertyValueFactory<>("department"));
        courseTeachingColTeachersTable.setCellValueFactory(new PropertyValueFactory<>("courseTeaching"));
        sectionsTeachingColTeachersTable.setCellValueFactory(new PropertyValueFactory<>("sectionsTeaching"));
        batchTeachingColTeachersTable.setCellValueFactory(new PropertyValueFactory<>("batchTeaching"));

        teachersTable.getItems().addAll(admin.getAllTeachers());

        titleColCoursesTable.setCellValueFactory(new PropertyValueFactory<>("title"));
        creditHrColCoursesTable.setCellValueFactory(new PropertyValueFactory<>("creditHour"));
        semesterGivenColCoursesTable.setCellValueFactory(new PropertyValueFactory<>("semester_given"));
        departmentColCoursesTable.setCellValueFactory(new PropertyValueFactory<>("department"));
        prerequisiteColCoursesTable.setCellValueFactory(new PropertyValueFactory<>("prerequisite"));

        coursesTable.getItems().addAll(admin.getAllCourses());

    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void onAddStudentBtnClicked() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("add_student_dialog.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        addNewStudentDialogStage.setScene(scene);
        addNewStudentDialogStage.show();
    }

    public void onAddNewStudentDialogBtnClicked() {
        if (studentPassword.getText().equals("") || studentPasswordReentered.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter a password");
            alert.show();
            return;
        }

        if (!studentPassword.getText().equals(studentPasswordReentered.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please make sure the passwords match");
            alert.show();
            return;
        }

        if (studentFirstName.getText().equals("") || studentMiddleName.getText().equals("") || studentLastName.getText().equals("") ||
        studentSection.getText().equals("") || studentBatch.getText().equals("") || studentDepartment.getText().equals("") || studentID.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please make sure you have entered all the fields.");
            alert.show();
            return;
        }

        Student studentToAdd = new Student(studentFirstName.getText(), studentMiddleName.getText(),
                studentLastName.getText(), studentSection.getText(), Integer.parseInt(studentBatch.getText()),
                studentDepartment.getText(), studentID.getText());
        if (admin.addStudent(studentToAdd, studentPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The student has been added successfully!");
            alert.show();
            studentsTable.getItems().clear();
            studentsTable.getItems().addAll(admin.getAllStudents());
            addNewStudentDialogStage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The credentials you entered are wrong or the same student exists. Please review " +
                    "them before proceeding");
            alert.show();
        }
    }

    public void onCancelDialogBtnClicked(){
        addNewStudentDialogStage.close();
        addNewTeacherDialogStage.close();
        addNewCourseDialogStage.close();
    }

    public void onRemoveStudentBtnClicked() {
        if (studentsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select a student to remove");
            alert.show();
            return;
        }

        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to remove student " + selectedStudent.getFormalName() + " (ID: " + selectedStudent.getID() + ")?\nThis is irreversible!");

        Optional<ButtonType> alertButtonResult = alert.showAndWait();

        if (alertButtonResult.isPresent() && alertButtonResult.get() == ButtonType.OK) {
            admin.removeStudent(selectedStudent.getID());
            studentsTable.getItems().clear();
            studentsTable.getItems().addAll(admin.getAllStudents());
        }

    }



    public void onRemoveTeacherBtnClicked(){
        if (teachersTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select a teacher to remove");
            alert.show();
            return;
        }

        Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to remove teacher " + selectedTeacher.getFormalName()  + "?\nThis" +
                " is irreversible!");

        Optional<ButtonType> alertButtonResult = alert.showAndWait();

        if (alertButtonResult.isPresent() && alertButtonResult.get() == ButtonType.OK) {
            admin.removeTeacher(admin.getTeacherUsername(selectedTeacher));
            teachersTable.getItems().clear();
            teachersTable.getItems().addAll(admin.getAllTeachers());
        }
    }

    public void onAddTeacherBtnClicked() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("add_teacher_dialog.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        addNewTeacherDialogStage.setScene(scene);
        addNewTeacherDialogStage.show();
    }

    public void onAddNewTeacherDialogBtnClicked(){
        if (teacherPassword.getText().equals("") || teacherPasswordReentered.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter a password");
            alert.show();
            return;
        }

        if (!teacherPassword.getText().equals(teacherPasswordReentered.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please make sure the passwords match");
            alert.show();
            return;
        }

        if (teacherUname.getText().equals("") || teacherFirstName.getText().equals("") || teacherMiddleName.getText().equals("") ||
                teacherLastName.getText().equals("") || teacherDepartment.getText().equals("") || courseTeaching.getText().equals("")
                || teacherBatchTeaching.getText().equals("") || teacherSectionsTeaching.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please make sure you have entered all the fields.");
            alert.show();
            return;
        }

        Teacher teacherToAdd = new Teacher(teacherFirstName.getText(), teacherMiddleName.getText(),
                teacherLastName.getText(), courseTeaching.getText(), teacherSectionsTeaching.getText(),
                Integer.parseInt(teacherBatchTeaching.getText()), teacherDepartment.getText());
        if (admin.addTeacher(teacherToAdd, teacherUname.getText(), teacherPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The teacher has been added successfully!");
            alert.show();
            teachersTable.getItems().clear();
            teachersTable.getItems().addAll(admin.getAllTeachers());
            addNewTeacherDialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The credentials you entered are wrong or the same student exists. Please review " +
                    "them before proceeding");
            alert.show();
        }
    }


    public void onAddCourseBtnClicked() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("add_course_dialog.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        addNewCourseDialogStage.setScene(scene);
        addNewCourseDialogStage.show();
    }

    public void onRemoveCourseBtnClicked() {
        if (coursesTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select a course to remove");
            alert.show();
            return;
        }

        Course selectedCourse = coursesTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to remove the course '" + selectedCourse.getTitle() + "'?\nThis is irreversible!");

        Optional<ButtonType> alertButtonResult = alert.showAndWait();

        if (alertButtonResult.isPresent() && alertButtonResult.get() == ButtonType.OK) {
            admin.removeCourse(selectedCourse.getTitle());
            coursesTable.getItems().clear();
            coursesTable.getItems().addAll(admin.getAllCourses());
        }
    }

    public void onAddNewCourseDialogBtnClicked() {
        if (courseTitle.getText().equals("") || courseCreditHr.getText().equals("") || courseSemesterGiven.getText().equals("")
        || courseDepartment.getText().equals("") || coursePrerequisite.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please make sure you have entered all the fields.");
            alert.show();
            return;
        }

        Course courseToAdd = new Course(courseTitle.getText(), Integer.parseInt(courseCreditHr.getText()),
                Integer.parseInt(courseSemesterGiven.getText()), coursePrerequisite.getText(), courseDepartment.getText());

        if (admin.addCourse(courseToAdd)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The course has been added successfully!");
            alert.show();
            coursesTable.getItems().clear();
            coursesTable.getItems().addAll(admin.getAllCourses());
            addNewCourseDialogStage.close();
        }
    }

}
