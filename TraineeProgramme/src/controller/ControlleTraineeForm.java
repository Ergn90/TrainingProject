package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import java.time.Instant;
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
    ComboBox<Location> locationTraineeComboBox;

    @FXML
    Button addNewTrainee;

    @FXML
    Button cancelTrainee;
    private int currentTraineeID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLocationComboBox();
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
        tr.setLocation(loc.getLocation(locationTraineeComboBox.getSelectionModel().getSelectedItem().getLocationId()));

        if (checkInputOfNull(tr)) {
            trainee.insertTrainee(tr);
            backToTrainee();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please fill all required fields ");
            alert.showAndWait();
        }

    }

    @FXML
    public void cancelTrainee() {
        backToTrainee();
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
        Location location = new Location();
        location.setLocationId((locationTraineeComboBox.getSelectionModel().getSelectedItem().getLocationId()));
        location.setLocationName((locationTraineeComboBox.getSelectionModel().getSelectedItem().getLocationName()));
        tr.setLocation(location);

        updatedTrainee.updateTrainee(tr);
        backToTrainee();

    }
    public void setLocationComboBox() {

        LocationDao locationDao = new LocationDaoImpl();
        Set<Location> loc = locationDao.getAllLocation();
        locationTraineeComboBox.setItems(FXCollections.observableArrayList(loc));
    }

    public void backToTrainee() {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/TraineesView.fxml"));
            addNewTrainee.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTrainee(Trainee trainee) {

        currentTraineeID = trainee.getTraineeID();
        lastNameTrainee.setText(trainee.getLastName());
        firstNameTrainee.setText(trainee.getFirstName());
        birthdayTrainee.setValue(convertToLocalDateViaMilisecond(trainee.getBirthday()));
        addressTrainee.setText(trainee.getAddress());
        schoolTrainee.setText(trainee.getSchool());
        emailTrainee.setText(trainee.getEmail());
        locationTraineeComboBox.getSelectionModel().select(trainee.getLocation());

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
    public boolean checkInputOfNull(Trainee trainee){
        if(trainee.getFirstName() != null && trainee.getLastName()!=null && trainee.getBirthday() != null &&
        trainee.getAddress() != null && trainee.getLocation() != null && trainee.getSchool() != null && trainee.getEmail() !=null){
            return true;
        }
        return false;
    }


}


