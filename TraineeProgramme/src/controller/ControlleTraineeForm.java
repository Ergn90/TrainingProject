package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import model.location.Location;
import model.location.LocationDao;
import model.location.LocationDaoImpl;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

public class ControlleTraineeForm implements Initializable {

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
    TextField firstNameTrainee;

    @FXML
    TextField lastNameTrainee;

    @FXML
    DatePicker birthdayTrainee;

    @FXML
    TextField addressTrainee;

    @FXML
    TextField schoolTrainee;

    @FXML
    TextField emailTrainee;

    @FXML
    ComboBox<Location> locationTraineeCombo;

    @FXML
    Button addNewTrainee;

    @FXML
    Button cancelTrainee;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getLocationComboBox();
    }

    public void getLocationComboBox() {
        LocationDao locationDao = new LocationDaoImpl();
        Set<Location> loc = locationDao.getAllLocation();
        locationTraineeCombo.setItems(FXCollections.observableArrayList(loc));
    }

    @FXML
    public void addNewTrainee() {

            TraineeDao trainee = new TraineeDaoImpl();

            Trainee tr = new Trainee();
            LocationDao loc = new LocationDaoImpl();

            tr.setLastName(lastNameTrainee.getText());
            tr.setFirstName(firstNameTrainee.getText());
            tr.setBirthday(Date.from(birthdayTrainee.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            tr.setAddress(addressTrainee.getText());
            tr.setSchool(schoolTrainee.getText());
            tr.setEmail(emailTrainee.getText());
            tr.setLocation(loc.getLocation(locationTraineeCombo.getSelectionModel().getSelectedItem().getLocationId()));

            trainee.insertTrainee(tr);
            Platform.exit();

        }
    @FXML
        public void cancelTrainee(){
        Platform.exit();
        }

    @FXML
    public void updateTrainee(Trainee trainee) {

        lastNameTrainee.setText(trainee.getLastName());
        firstNameTrainee.setText(trainee.getFirstName());
        birthdayTrainee.setValue(LocalDate.from(trainee.getBirthday().toInstant()));
        addressTrainee.setText(trainee.getAddress());
        schoolTrainee.setText(trainee.getSchool());
        emailTrainee.setText(trainee.getEmail());

        LocationDao loc = new LocationDaoImpl();
        locationTraineeCombo.setSelectionModel((SingleSelectionModel<Location>) loc.getAllLocation());
        locationTraineeCombo.getSelectionModel().select(trainee.getLocation());


        TraineeDao updatedTrainee = new TraineeDaoImpl();
        Trainee tr = new Trainee();


        tr.setLastName(lastNameTrainee.getText());
        tr.setFirstName(firstNameTrainee.getText());
        tr.setBirthday(Date.from(birthdayTrainee.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tr.setAddress(addressTrainee.getText());
        tr.setSchool(schoolTrainee.getText());
        tr.setEmail(emailTrainee.getText());
        tr.setLocation(loc.getLocation(locationTraineeCombo.getSelectionModel().getSelectedItem().getLocationId()));

        updatedTrainee.updateTrainee(tr);
        Platform.exit();

    }

}


