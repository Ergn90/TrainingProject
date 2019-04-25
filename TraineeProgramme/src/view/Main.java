package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ConnectionFactory;
import model.courses.Courses;
import model.courses.CoursesDaoImpl;

import java.sql.Connection;
import java.util.Collections;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TraineesView.fxml"));
        primaryStage.setTitle("TraineeProgram");
        primaryStage.setScene(new Scene(root, 900, 500 ));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //launch(args);

        CoursesDaoImpl courses= new CoursesDaoImpl();
        (courses.getAllCourses()).forEach(course-> System.out.println(course.getCourseName()));

    }
}