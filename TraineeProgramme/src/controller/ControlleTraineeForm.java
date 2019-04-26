package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.location.Location;
import model.location.LocationDao;
import model.location.LocationDaoImpl;

import java.net.URL;
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
    TextField birthdayTrainee;

    @FXML
    TextField addressTrainee;

    @FXML
    TextField schoolTrainee;

    @FXML
    TextField emailTrainee;

    @FXML
    ComboBox locationTraineeCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getLocationComboBox();
    }

    public void getLocationComboBox() {
        LocationDao locationDao = new LocationDaoImpl();
        Set<Location> loc = locationDao.getAllLocation();
        locationTraineeCombo.setItems(FXCollections.observableArrayList(loc));
    }

}
