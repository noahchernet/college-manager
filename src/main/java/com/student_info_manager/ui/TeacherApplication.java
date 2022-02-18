package com.student_info_manager.ui;

import com.student_info_manager.models.Teacher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherApplication extends Application {
    private Teacher teacher;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TeacherApplication.class.getResource("teacher_view.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Welcome back " + teacher.getName()[0] + "!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
