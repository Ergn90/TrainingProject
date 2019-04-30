package controller;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.location.Location;
import model.location.LocationDao;
import model.location.LocationDaoImpl;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable {



    @FXML
    TableView<Trainee> tableTrainees;

    @FXML
    TableColumn traineeID;

    @FXML
    TableColumn lastName;

    @FXML
    TableColumn firstName;

    @FXML
    TableColumn birthday;

    @FXML
    TableColumn address;

    @FXML
    TableColumn school;

    @FXML
    TableColumn email;

    @FXML
    TableColumn locationTrainees;

    @FXML
    Label lastNameCourseForm;

    @FXML
    Label birthdayCourseForm;

    @FXML
    Label addressCourseForm;

    @FXML
    ChoiceBox courseNameId;

    @FXML
    ChoiceBox courseYear;

    @FXML
    ChoiceBox courseRoom;

    @FXML
    TextArea courseDescription;


    @FXML
    TextField searchFielCourses;
    @FXML
    Button searchButtonCourses;
    @FXML
    ListView listViewCourses;

    @FXML
    Label manageTraineesC;

    @FXML
    Label manageCoursesC;


    @FXML
    TableColumn courseID;

    @FXML
    TableColumn courseName;

    @FXML
    TableColumn courseyear;

    @FXML
    TableColumn courseRoomC;

    @FXML
    TableColumn courseDescriptionC;

    @FXML
    TableColumn modifyCourse;

    @FXML
    Button addTraineeC;
    @FXML
    Button addCourseC;
    @FXML
    Button enrollTraineeC;


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
    Label emailTraineeForm;


    @FXML
    Label locationTraineeForm;

    @FXML
    Label scaleTraineeId;


    @FXML
    TextField firstNameTrainee;

    @FXML
    TextField lastNameTrainee;

    @FXML
    TextField birthdayTrainee;

    @FXML
    TextField addressTrainee;

    @FXML
    TextField schoolTrainee;

    @FXML
    TextField emailTrainee;

    @FXML
    TextField locationTrainee;

    @FXML
    TextField scaleTrainee;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

//       listManageTraineeProgram.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);      //nur eine Zeile selektieren
    }

    public void getTraineeInfo() {
        TraineeDao traineeDao = refreshTraineeInfo();

    }

    public TraineeDao refreshTraineeInfo() {
        TraineeDao traineeDao = new TraineeDaoImpl();
        Set<Trainee> trainees = traineeDao.getAllTrainee();
        tableTrainees.setItems(FXCollections.observableArrayList(trainees));

        traineeID.setCellValueFactory(new PropertyValueFactory<>("traineeID"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        school.setCellValueFactory(new PropertyValueFactory<>("school"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        locationTrainees.setCellValueFactory(new PropertyValueFactory<>("location"));
        return traineeDao;
    }
}
