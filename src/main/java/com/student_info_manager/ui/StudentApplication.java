package com.student_info_manager.ui;

import com.student_info_manager.models.Course;
import com.student_info_manager.models.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class StudentApplication extends Application {

    private Student student;
    @FXML
    public TabPane studentViewTabPane;
    @FXML
    public Tab coursesTab;
    @FXML
    public TableView<Course> semesterCoursesTable;
    @FXML
    public TableColumn course_column_courses_table;
    @FXML
    public TableColumn credit_hour_column_courses_table;

    @FXML
    public Tab gradesAndScoresTab;
    @FXML
    public ChoiceBox scoresTableSemesterPicker;
    @FXML
    public TableView SemesterScoresTable;
    @FXML
    public Label CourseTitle;
    @FXML
    public Label InstructorName;
    @FXML
    public TableView detailedCourseScore;
    @FXML
    public TableColumn CourseScores;
    @FXML
    ChoiceBox<String> coursesTableSemesterPicker;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("student_view.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Welcome back " + student.getName()[0] + "!");
        primaryStage.setScene(scene);
        primaryStage.show();

        int year = Calendar.getInstance().getWeekYear();
        ArrayList<String> semesters = new ArrayList<>();

        for (int i = 1; i <= 2 * (year - student.getBatch()); i++) {
            semesters.add("Semester " + i);
        }
        semesters.add("All semesters");
        coursesTableSemesterPicker.setItems(FXCollections.observableList(semesters));

        course_column_courses_table.setCellValueFactory(new PropertyValueFactory<>("title"));
        credit_hour_column_courses_table.setCellValueFactory(new PropertyValueFactory<>("creditHour"));

        coursesTableSemesterPicker.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                semesterCoursesTable.getItems().clear();
                try {
                    if ((int) t1 == 2 * (year - student.getBatch()))
                        semesterCoursesTable.getItems().addAll(student.getAllCourses());
                    else
                        semesterCoursesTable.getItems().addAll(student.getSemesterCourses((int) t1 + 1));
                } catch (NullPointerException e) {
                    System.out.println("Semester courses is empty");
                }
            }
        });
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
