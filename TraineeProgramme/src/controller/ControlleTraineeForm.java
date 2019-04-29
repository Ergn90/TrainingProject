package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.util.Calendar;
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
    private int currentTraineeID;


    public void setTrainee(Trainee trainee){
        currentTraineeID=trainee.getTraineeID();
        lastNameTrainee.setText(trainee.getLastName());
        firstNameTrainee.setText(trainee.getFirstName());

        birthdayTrainee.setValue(convertToLocalDateViaMilisecond(trainee.getBirthday()));
        addressTrainee.setText(trainee.getAddress());
        schoolTrainee.setText(trainee.getSchool());
        emailTrainee.setText(trainee.getEmail());
        locationTraineeCombo.getSelectionModel().select(trainee.getLocation());

    }

    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


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
            tr.setBirthday(convertToDateViaInstant(birthdayTrainee.getValue()));
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
    public void updateTrainee() {
        
        TraineeDao updatedTrainee = new TraineeDaoImpl();
        Trainee tr = new Trainee();

        tr.setTraineeID(currentTraineeID);
        tr.setLastName(lastNameTrainee.getText());
        tr.setFirstName(firstNameTrainee.getText());
        tr.setBirthday(convertToDateViaInstant(birthdayTrainee.getValue()));
        tr.setAddress(addressTrainee.getText());
        tr.setSchool(schoolTrainee.getText());
        tr.setEmail(emailTrainee.getText());
        Location location=new Location();
        location.setLocationId((locationTraineeCombo.getSelectionModel().getSelectedItem().getLocationId()));
        location.setLocationName((locationTraineeCombo.getSelectionModel().getSelectedItem().getLocationName()));
        tr.setLocation(location);

        updatedTrainee.updateTrainee(tr);
        Platform.exit();

    }

}


