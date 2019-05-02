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
