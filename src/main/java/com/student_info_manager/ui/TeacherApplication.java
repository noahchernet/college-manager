package com.student_info_manager.ui;

import com.student_info_manager.models.Course;
import com.student_info_manager.models.Result;
import com.student_info_manager.models.Student;
import com.student_info_manager.models.Teacher;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherApplication extends Application {
    private Teacher teacher;
    @FXML
    public TableView<Student> scoresTable;
    @FXML
    public TableColumn idColScoresTable;
    @FXML
    public TableColumn nameColScoresTable;

    @FXML
    public TextField quiz;
    @FXML
    public TextField attendance;
    @FXML
    public TextField assignment_1;
    @FXML
    public TextField assignment_2;
    @FXML
    public TextField test_1;
    @FXML
    public TextField test_2;
    @FXML
    public TextField finalExam;
    @FXML
    public Label grade;
    @FXML
    public Button updateScore;


    // About Me Tab
    @FXML
    private Label teacherFirstname;
    @FXML
    private Label teacherMiddleName;
    @FXML
    private Label teacherLastName;
    @FXML
    private Label teacherDepartment;
    @FXML
    private Label teacherSections;
    @FXML
    private Label teacherBatches;
    @FXML
    private Label teacherCourse;

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

        idColScoresTable.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColScoresTable.setCellValueFactory(new PropertyValueFactory<>("formalName"));

        for (Student student : teacher.getMyStudents()) {
            for (Course course : student.getAllCourses()) {
                if (course.getTitle().equals(teacher.getCourseTeaching()))
                    scoresTable.getItems().add(student);
            }
        }

        scoresTable.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("---");
                Result selectedStudentResult =
                        scoresTable.getSelectionModel().getSelectedItem().getResultOfCourse(teacher.getCourse(teacher.getCourseTeaching()));
                quiz.setText("" + selectedStudentResult.getQuiz());
                attendance.setText("" + selectedStudentResult.getAttendance());
                assignment_1.setText("" + selectedStudentResult.getAssignment_1());
                assignment_2.setText("" + selectedStudentResult.getAssignment_2());
                test_1.setText("" + selectedStudentResult.getTest_1());
                test_2.setText("" + selectedStudentResult.getTest_2());
                finalExam.setText("" + selectedStudentResult.getFinalExam());
                grade.setText("" + selectedStudentResult.getGrade());
            }
        });

        teacherFirstname.setText(teacher.getFirstName());
        teacherMiddleName.setText(teacher.getMiddleName());
        teacherLastName.setText(teacher.getLastName());
        teacherDepartment.setText(teacher.getDepartment());
        teacherSections.setText(teacher.getSectionsTeaching());
        teacherBatches.setText("" + teacher.getBatchTeaching());
        teacherCourse.setText(teacher.getCourseTeaching());

    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void onUpdateScorePressed() {
        Result updatedResult =
                scoresTable.getSelectionModel().getSelectedItem().getResultOfCourse(teacher.getCourse(teacher.getCourseTeaching()));
        updatedResult.setQuiz(Double.parseDouble(quiz.getText()));
        updatedResult.setAttendance(Double.parseDouble(attendance.getText()));
        updatedResult.setAttendance(Double.parseDouble(attendance.getText()));
        updatedResult.setAssignment_1(Double.parseDouble(assignment_1.getText()));
        updatedResult.setAssignment_2(Double.parseDouble(assignment_2.getText()));
        updatedResult.setTest_1(Double.parseDouble(test_1.getText()));
        updatedResult.setTest_2(Double.parseDouble(test_2.getText()));
        updatedResult.setFinalExam(Double.parseDouble(finalExam.getText()));
        updatedResult.evalGrade();

        teacher.updateResult(scoresTable.getSelectionModel().getSelectedItem(), updatedResult);

        Result selectedStudentResult =
                scoresTable.getSelectionModel().getSelectedItem().getResultOfCourse(teacher.getCourse(teacher.getCourseTeaching()));
        quiz.setText("" + selectedStudentResult.getQuiz());
        attendance.setText("" + selectedStudentResult.getAttendance());
        assignment_1.setText("" + selectedStudentResult.getAssignment_1());
        assignment_2.setText("" + selectedStudentResult.getAssignment_2());
        test_1.setText("" + selectedStudentResult.getTest_1());
        test_2.setText("" + selectedStudentResult.getTest_2());
        finalExam.setText("" + selectedStudentResult.getFinalExam());
        grade.setText("" + selectedStudentResult.getGrade());
    }
}
