package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable {

    ////////TraineesView//////////////////
    @FXML
    ListView listViewTrainees;

    @FXML
    Label manageTrainee;

    @FXML
    Label manageCourses;

    @FXML
    Label enrolledTrainees;
    /////////////////////////////////////////////////////////
    @FXML
    TableView tableTrainees;

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
    TableColumn location;

    @FXML
    TableColumn skalaTrainee;

    @FXML
    TableColumn traineeCurentCourse;

    @FXML
    TableColumn modifyTrainee;

///////////////////////////////////////////////////////////
    @FXML
    Button addTrainee;

    @FXML
    Button addCourse1;

    @FXML
    Button enrollTrainee;
///////////////////////////////////////////////////////////
    @FXML
    TextField searchFieldTrainees;

    @FXML
    Button searchButtonTrainees;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TraineeDao traineeDao=new TraineeDaoImpl();
        Set<Trainee> trainees=traineeDao.getAllTrainee();
        tableTrainees.setItems(FXCollections.observableArrayList(trainees));
//        listManageTraineeProgram.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);      //nur eine Zeile selektieren
    }


}
