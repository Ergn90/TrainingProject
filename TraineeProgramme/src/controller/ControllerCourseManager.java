package controller;

import javafx.fxml.Initializable;
import model.course.Course;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCourseManager  implements Initializable {
    //TODO this class
    private Course course;

    public void setCourse(Course course) {
        this.course=course;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
