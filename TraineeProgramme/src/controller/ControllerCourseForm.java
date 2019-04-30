package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import model.course.Course;
import model.course.CourseDao;
import model.course.CourseDaoImpl;
import model.location.Location;
import model.location.LocationDao;
import model.location.LocationDaoImpl;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerCourseForm implements Initializable {

    @FXML
    Label startDate;

    @FXML
    Label endDate;

    @FXML
    Label name;

    @FXML
    Label room;

    @FXML
    Label description;

    @FXML
    DatePicker courseStartDate;

    @FXML
    DatePicker courseEndDate;

    @FXML
    TextField courseName;

    @FXML
    ComboBox courseRoomComboBox;

    @FXML
    TextField courseDescription;

    @FXML
    Button addNewTrainee;

    @FXML
    Button cancelCourse;


    private int currentCourseId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCourseRoomComboBox();

    }

    @FXML
    public void addNewCourse() {

        CourseDao course = new CourseDaoImpl();
        Course cr = new Course();

        cr.setStartDate(Controller.convertToDateViaInstant(courseStartDate.getValue()));
        cr.setEndDate(Controller.convertToDateViaInstant(courseEndDate.getValue()));
        courseName.setText(getCourseName(courseStartDate.getValue()));
        cr.setCourseName(courseName.getText());
        cr.setCourseRoom(courseRoomComboBox.getSelectionModel().getSelectedItem().toString());
        cr.setCourseDescription(courseDescription.getText());

        course.insertCourse(cr);
        backToCourse();
    }

    @FXML
    public void cancelCourse() {
        backToCourse();
    }

    @FXML
    public void updateCourse() {

        CourseDao updatedCourse = new CourseDaoImpl();
        Course cr = new Course();

        cr.setCourseID(currentCourseId);
        cr.setStartDate(Controller.convertToDateViaInstant(courseStartDate.getValue()));
        cr.setEndDate(Controller.convertToDateViaInstant(courseEndDate.getValue()));
        cr.setCourseName(courseName.getText());
        cr.setCourseRoom(courseRoomComboBox.getSelectionModel().getSelectedItem().toString());
        cr.setCourseDescription(courseDescription.getText());

        updatedCourse.updateCourse(cr);
        backToCourse();

    }

    public void setCourseRoomComboBox() {

        CourseDao course = new CourseDaoImpl();
        List<String> crs = course.getAllRoomsValues();
        courseRoomComboBox.setItems(FXCollections.observableArrayList(crs));
    }

    public void backToCourse() {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseView.fxml"));
            addNewTrainee.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getCourseName(LocalDate startDate){
        byte ending;
        if (startDate.getMonthValue() < 5){
            ending = 1;
        }else{
            ending = 2;
        }
        return "Java Trainee "+ startDate.getYear()+"." + ending;
    }
    public void setCourse(Course course) {

        currentCourseId= course.getCourseID();
        courseStartDate.setValue(Controller.convertToLocalDateViaMilisecond(course.getStartDate()));
        courseEndDate.setValue(Controller.convertToLocalDateViaMilisecond(course.getEndDate()));
        courseName.setText(getCourseName(courseStartDate.getValue()));
        courseRoomComboBox.getSelectionModel().select(course.getCourseRoom());
        courseDescription.setText(course.getCourseDescription());

    }


}