package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.course.Course;
import model.course.CourseDao;
import model.course.CourseDaoImpl;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerCourseView implements Initializable {

    @FXML
    TextField searchFielCourses;
    @FXML
    Button searchButtonCourses;
    @FXML
    TableColumn courseID;
    @FXML
    TableColumn courseName;
    @FXML
    TableColumn courseyear;
    @FXML
    TableColumn courseRoom;
    @FXML
    TableColumn courseDescriptionC;
    @FXML
    Button addCourseC;
    @FXML
    Button courseManage;
    @FXML
    Button backFromCourseViewToTraineeView;
    @FXML
    TableView coursesTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //     onBtnClick(backFromCourseViewToTraineeView);

      //  getCourseInfo();
    }


/*    public void getCourseInfo() {
        CourseDao courseDao = refreshCourseInfo();
    }


    public CourseDao refreshCourseInfo() {
        CourseDao courseeDao = new CourseDaoImpl();
        Set<Course> courses = courseeDao.getAllCourses();
        coursesTable.setItems(FXCollections.observableArrayList(courses));

        courseID.setCellValueFactory(new PropertyValueFactory<>("CourseID"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        courseyear.setCellValueFactory(new PropertyValueFactory<>("CourseDate"));
        courseRoom.setCellValueFactory(new PropertyValueFactory<>("CourseRoom"));
        courseDescriptionC.setCellValueFactory(new PropertyValueFactory<>("CourseDescription"));

        return courseeDao;
    }*/

    @FXML
    public void backToTrainee(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource(".../view/TraineeView.fxml"));

            backFromCourseViewToTraineeView.getScene().setRoot(loader);
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }

    @FXML
    public void manageCourse(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseManager"));

            courseManage.getScene().setRoot(loader);
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }

    @FXML
    public void addCourse(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseForm"));

            addCourseC.getScene().setRoot(loader);
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }
}

