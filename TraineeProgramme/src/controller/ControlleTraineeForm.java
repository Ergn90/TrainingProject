package controller;

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
import java.time.LocalDate;
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

    private boolean isBirthdayDateValide() {
        if (!isAgeRequirement(birthdayTrainee.getValue(), 18)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please insert a date to have 18 years old.");
            alert.show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isAgeRequirement(LocalDate localDate, int minAge) {
        int yearMin = LocalDate.now().getYear() - minAge;
        if (localDate.getYear() == yearMin) {
            if (localDate.getMonth().getValue() >= LocalDate.now().getMonth().getValue()) {
                if (localDate.getDayOfYear() >= LocalDate.now().getDayOfYear()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (localDate.getYear() > yearMin) {
            return false;

        } else {
            return true;
        }

    }

    @FXML
    public void addNewTrainee() {

        if (isInputValide()) {
            if (isBirthdayDateValide()) {
                insertInfoTrainee();
            }
        }

    }

    private void insertInfoTrainee() {
        TraineeDao trainee = new TraineeDaoImpl();

        Trainee tr = new Trainee();
        LocationDao loc = new LocationDaoImpl();

        tr.setLastName(lastNameTrainee.getText());
        tr.setFirstName(firstNameTrainee.getText());
        tr.setBirthday((birthdayTrainee.getValue()));
        tr.setAddress(addressTrainee.getText());
        tr.setSchool(schoolTrainee.getText());
        tr.setEmail(emailTrainee.getText());
        tr.setLocation(loc.getLocation(locationTraineeComboBox.getSelectionModel().getSelectedItem().getLocationId()));
        trainee.insertTrainee(tr);
        backToTrainee();

    }

    @FXML
    public void cancelTrainee() {
        backToTrainee();
    }

    @FXML
    public void updateTrainee() {
        if (isBirthdayDateValide() && isInputValide()) {

            TraineeDao updatedTrainee = new TraineeDaoImpl();
            Trainee tr = new Trainee();

            tr.setTraineeID(currentTraineeID);
            tr.setLastName(lastNameTrainee.getText());
            tr.setFirstName(firstNameTrainee.getText());
            tr.setBirthday(birthdayTrainee.getValue());
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
        birthdayTrainee.setValue(trainee.getBirthday());
        addressTrainee.setText(trainee.getAddress());
        schoolTrainee.setText(trainee.getSchool());
        emailTrainee.setText(trainee.getEmail());
        locationTraineeComboBox.getSelectionModel().select(trainee.getLocation());

    }


    private boolean isInputValide() {
        String error = "Please fill all required fields ";
        if (!locationTraineeComboBox.getSelectionModel().isEmpty()) {
            error += "location not selected ";
        }
        if (!lastNameTrainee.getText().trim().isEmpty()) {
            error += "lastName empty ";
        }
        if (!firstNameTrainee.getText().trim().isEmpty()) {
            error += "first name empty ";

        }
        if (!addressTrainee.getText().trim().isEmpty()) {
            error += "adress empty ";

        }
        if (!emailTrainee.getText().trim().isEmpty()) {
            error += "email empty ";

        }
        if (!schoolTrainee.getText().trim().isEmpty()) {
            error += "school empty ";

        }
        if (birthdayTrainee.getValue() != null) {
            return true;
        }else{
            error += "birthday not selected empty ";

        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText(error);

        alert.show();
        return false;
    }


}


