package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.course.Course;
import model.course.CourseDao;
import model.course.CourseDaoImpl;
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
    TableColumn startDate;

    @FXML
    TableColumn endDate;
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
        getCourseInfo();
        searchForCourse();
    }

    private void searchForCourse() {
        CourseDao courseDaoo = new CourseDaoImpl();
        Set<Course> courses = courseDaoo.getAllCourses();
        ObservableList<Course> coursesList = FXCollections.observableArrayList(courses);
        FilteredList<Course> filteredData = new FilteredList<>(coursesList, c -> true);

        searchFielCourses.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredData.setPredicate(course -> {
                 // If filter text is empty, display all persons.
                 if (newValue == null || newValue.isEmpty()) {
                     return true;
                 }

                 // Compare first name and last name of every person with filter text.
                 String lowerCaseFilter = newValue.toLowerCase();

                 if (course.getCourseRoom().toLowerCase().contains(lowerCaseFilter)) {
                     return true;
                 } /*else if (course..toLowerCase().contains(lowerCaseFilter)) {
                     return true;*/
                 //}
                 return false; // Does not match.
             });
         });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Course> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(coursesTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        coursesTable.setItems(sortedData);
    }


    public void getCourseInfo() {
        CourseDao courseDao = refreshCourseInfo();
    }

    public CourseDao refreshCourseInfo() {

        CourseDao courseDao = new CourseDaoImpl();
        Set<Course> courses = courseDao.getAllCourses();
        coursesTable.setItems(FXCollections.observableArrayList(courses));
        courseID.setCellValueFactory(new PropertyValueFactory<>("CourseID"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        courseyear.setCellValueFactory(new PropertyValueFactory<>("CourseDate"));
        courseRoom.setCellValueFactory(new PropertyValueFactory<>("CourseRoom"));
        courseDescriptionC.setCellValueFactory(new PropertyValueFactory<>("CourseDescription"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        return courseDao;
    }

    @FXML
    public void backToTrainee(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/TraineesView.fxml"));
            backFromCourseViewToTraineeView.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

    @FXML
    public void manageCourse(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseManager.fxml"));
            courseManage.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

    @FXML
    public void addCourse(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseForm.fxml"));
            addCourseC.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }
}

