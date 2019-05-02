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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

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
    Label courseNameLabel;

    @FXML
    ComboBox courseRoomComboBox;

    @FXML
    TextField courseDescription;

    @FXML
    Button addNewCourse;

    @FXML
    Button cancelCourse;


    private int currentCourseId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCourseRoomComboBox();

    }

    @FXML
    public void addNewCourse() {
        if (isInputValide() && isDateValid(courseStartDate.getValue(), courseEndDate.getValue())) {
            CourseDao course = new CourseDaoImpl();
            Course cr = new Course();
            cr.setStartDate(courseStartDate.getValue());
            cr.setEndDate(courseEndDate.getValue());
            cr.setCourseName(courseName.getText());
            cr.setCourseRoom(courseRoomComboBox.getSelectionModel().getSelectedItem().toString());
            cr.setCourseDescription(courseDescription.getText());

            course.insertCourse(cr);
            backToCourse();
        }

    }

    @FXML
    public void cancelCourse() {
        backToCourse();
    }

    @FXML
    public void updateCourse() {
        if (isInputValide() && isDateValid(courseStartDate.getValue(), courseEndDate.getValue())) {

            CourseDao updatedCourse = new CourseDaoImpl();
            Course cr = new Course();
            cr.setCourseID(currentCourseId);
            cr.setStartDate(courseStartDate.getValue());
            cr.setEndDate(courseEndDate.getValue());
            cr.setCourseName(courseName.getText());
            cr.setCourseRoom(courseRoomComboBox.getSelectionModel().getSelectedItem().toString());
            cr.setCourseDescription(courseDescription.getText());

            courseNameLabel.setText(cr.toString());
            updatedCourse.updateCourse(cr);
            backToCourse();
        }

    }

    private void setCourseRoomComboBox() {

        CourseDao course = new CourseDaoImpl();
        List<String> crs = course.getAllRoomsValues();
        courseRoomComboBox.setItems(FXCollections.observableArrayList(crs));
    }

    private void backToCourse() {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseView.fxml"));
            addNewCourse.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCourse(Course course) {
        currentCourseId = course.getCourseID();
        courseStartDate.setValue(course.getStartDate());
        courseEndDate.setValue(course.getEndDate());
        courseName.setText(course.getCourseName());
        courseNameLabel.setText(course.toString());
        courseRoomComboBox.getSelectionModel().select(course.getCourseRoom());
        courseDescription.setText(course.getCourseDescription());

    }

    private boolean isDateValid(LocalDate start, LocalDate end) {
        if (end.compareTo(start) < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please insert a start date who is smaller than end date");
            alert.show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isInputValide() {
        String error = "Please fill all required fields ";
        if (!courseRoomComboBox.getSelectionModel().isEmpty()) {
            error += "Room not selected ";
        }
        if (!courseName.getText().trim().isEmpty()) {
            error += "courseName empty ";
        }
        if (courseEndDate.getValue() != null) {
            error += "courseEndDate not selected empty ";

        }
        if (courseStartDate.getValue() != null) {
            return true;
        } else {
            error += "courseStartDate not selected empty ";

        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText(error);

        alert.show();
        return false;
    }


}