package com.student_info_manager.ui;

import com.student_info_manager.control.Login;
import com.student_info_manager.models.Student;
import com.student_info_manager.models.Teacher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginApplication extends Application {

    private Stage primaryStage;
    @FXML
    private TextField student_id;
    @FXML
    private TextField teacher_id;
    @FXML
    private TextField admin_id;
    @FXML
    private PasswordField student_password;
    @FXML
    private PasswordField teacher_password;
    @FXML
    private PasswordField admin_password;
    @FXML
    private Button studentLoginBtn;
    @FXML
    private Button teacherLoginBtn;

    public LoginApplication() {
        primaryStage = new Stage();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.primaryStage = new Stage();
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    protected void studentLoginBtnClicked() throws IOException{
        Student student = new Login().connectStudent(student_id.getText(), student_password.getText());

        if (student != null) {
            primaryStage = (Stage) studentLoginBtn.getScene().getWindow();
            primaryStage.close();
            StudentApplication studentApplication = new StudentApplication();
            studentApplication.setStudent(student);
            studentApplication.start(primaryStage);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ID or password is incorrect");
            alert.show();
        }

    }

    @FXML
    protected void teacherLoginBtnClicked() throws IOException{
        Teacher teacher = new Login().connectTeacher(teacher_id.getText(), teacher_password.getText());

        if (teacher != null) {
            primaryStage = (Stage) teacherLoginBtn.getScene().getWindow();
            primaryStage.close();
            TeacherApplication teacherApplication = new TeacherApplication();
            teacherApplication.setTeacher(teacher);
            teacherApplication.start(primaryStage);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username or password is incorrect");
            alert.show();
        }


    }

    @FXML
    protected void adminLoginBtnClicked() {

    }
}