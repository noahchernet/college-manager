package com.student_info_manager.ui;

import com.student_info_manager.models.Admin;
import com.student_info_manager.models.Student;
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
}
