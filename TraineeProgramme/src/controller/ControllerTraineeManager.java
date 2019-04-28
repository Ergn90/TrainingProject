package controller;

import com.sun.org.omg.CORBA.Initializer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.course.Course;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.skala.Skala;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerTraineeManager implements Initializable {

//TODO Controller registrieren und auch noch den Trainee in Controller übergeben
    @FXML
    Label lastNameTraineeForm;

    @FXML
    Label firstNameTraineeForm;

    @FXML
    Label birthdayTraineeForm;

    @FXML
    Label addressTraineeForm;

    @FXML
    Label schoolTraineeForm;

    @FXML
    Label traineeID;

    @FXML
    Label emailTraineeForm;

    @FXML
    Label locationTraineeForm;

    @FXML
    Label scaleTraineeId;

    @FXML
    Button traineeAddToCourse;

    @FXML
    ComboBox courseList;

    @FXML
    ComboBox skalaList;

    @FXML
    TableView coursesEntered;

    @FXML
    TableColumn skalaInCorrespondingCourse;

    @FXML
    TableColumn courseEntered;

    //TODO macht hier die Trainee ID so sinn? oder soll evtl im Construktor mit übergeben werden?

    private Trainee trainee;
    // TODO The same

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabels();
    }

    //TODO Muss in haupt controller übergeben werden
    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public void setLabels()
    {
        traineeID.setText("TraineeID : " + trainee.getTraineeID());
        firstNameTraineeForm.setText("Firstname: " + trainee.getFirstName());
        lastNameTraineeForm.setText("Lastname: " + trainee.getLastName());
        birthdayTraineeForm.setText("Birthday: " + trainee.getBirthday());
        addressTraineeForm.setText("Address: " + trainee.getAddress());
        emailTraineeForm.setText("Email: " + trainee.getEmail());
        locationTraineeForm.setText("Location: " + trainee.getLocation());
    }

    public void setCourseTableView(){
        EnrolledTraineesDaoImpl enrolledTrainee = new EnrolledTraineesDaoImpl();
        List<Course> coursesForTrainee= enrolledTrainee.getAllCoursesByTrainee(trainee);
        List<Skala> scaleForCourses =  enrolledTrainee.getAllSkalaByTrainee(trainee);
        coursesEntered.setItems(FXCollections.observableArrayList(coursesForTrainee));
        courseEntered.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        skalaInCorrespondingCourse.setCellValueFactory(new PropertyValueFactory<>("SkalaName"));

    }
}
