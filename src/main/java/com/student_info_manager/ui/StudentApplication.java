package com.student_info_manager.ui;

import com.student_info_manager.models.Course;
import com.student_info_manager.models.Result;
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
    public TableView<Course> semesterCoursesTable;
    @FXML
    public TableColumn course_column_courses_table;
    @FXML
    public TableColumn credit_hour_column_courses_table;
    @FXML
    ChoiceBox<String> coursesTableSemesterPicker;

    @FXML
    public TableView<Result> semesterScoresTable;
    @FXML
    public TableColumn courseColResultsTable;
    @FXML
    public TableColumn creditHrColResultsTable;
    @FXML
    public TableColumn gradeColResultsTable;
    @FXML
    ChoiceBox<String> scoresTableSemesterPicker;

    @FXML
    public Label courseTitle;
    @FXML
    public Label quiz;
    @FXML
    public Label attendance;
    @FXML
    public Label assignment_1;
    @FXML
    public Label assignment_2;
    @FXML
    public Label test_1;
    @FXML
    public Label test_2;
    @FXML
    public Label finalExam;
    @FXML
    public Label grade;

    @FXML
    public TableView detailedCourseScore;
    @FXML
    public TableColumn specificScoreTypeColDetailsTable;
    @FXML
    public TableColumn spesificScoreColDetailsTable;


    // About Me Tab
    @FXML
    private Label studentID;
    @FXML
    private Label studentFirstname;
    @FXML
    private Label studentMiddleName;
    @FXML
    private Label studentLastName;
    @FXML
    private Label studentDepartment;
    @FXML
    private Label studentSection;
    @FXML
    private Label studentBatch;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentApplication.class.getResource("student_view.fxml"));
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

        courseColResultsTable.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        creditHrColResultsTable.setCellValueFactory(new PropertyValueFactory<>("courseCreditHr"));
        gradeColResultsTable.setCellValueFactory(new PropertyValueFactory<>("grade"));

        scoresTableSemesterPicker.setItems(FXCollections.observableList(semesters));
        scoresTableSemesterPicker.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                semesterScoresTable.getItems().clear();
                try {
                    if ((int) t1 == 2 * (year - student.getBatch()))
                    {
                        for (Course course: student.getAllCourses()) {
                            semesterScoresTable.getItems().add(student.getResultOfCourse(course));
                        }
                    }
                    else
                    {
                        for (Course course : student.getSemesterCourses((int) t1 + 1))
                            semesterScoresTable.getItems().add(student.getResultOfCourse(course));
                    }
                } catch (NullPointerException e) {
                    System.out.println("Semester scores is empty");
                }
            }
        });

        semesterScoresTable.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                try {
                    System.out.println("Course is: " +
                            semesterScoresTable.getSelectionModel().getSelectedItem().getCourseTitle());
                    Result selectedResult = semesterScoresTable.getSelectionModel().getSelectedItem();
                    courseTitle.setText(selectedResult.getCourseTitle());
                    quiz.setText("" + (int) selectedResult.getQuiz() + "/5");
                    attendance.setText("" + (int) selectedResult.getAttendance() + "/5");
                    assignment_1.setText("" + (int) selectedResult.getAssignment_1() + "/10");
                    assignment_2.setText("" + (int) selectedResult.getAssignment_2() + "/10");
                    test_1.setText("" + (int) selectedResult.getTest_1() + "/15");
                    test_2.setText("" + (int) selectedResult.getTest_1() + "/10");
                    finalExam.setText("" + (int) selectedResult.getFinalExam() + "/50");
                    grade.setText("" + selectedResult.getGrade());

                } catch (NullPointerException e ) {
                    System.out.println("No course is selected");
                }
            }
        });

        studentID.setText(student.getID());
        studentFirstname.setText(student.getFirstName());
        studentMiddleName.setText(student.getMiddleName());
        studentLastName.setText(student.getLastName());
        studentDepartment.setText(student.getDepartment());
        studentSection.setText(student.getSection());
        studentBatch.setText("" + student.getBatch());
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
