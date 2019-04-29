package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.course.Course;
import model.course.CourseDaoImpl;
import model.enrolledTrainees.EnrolledTrainees;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.skala.Skala;
import model.skala.SkalaDaoImpl;
import model.trainee.Trainee;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;


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

    //    @Override
    public void initialize(URL location, ResourceBundle resources) {//
        setLabels();
        setComboboxCourseList();
        setComboboxSkalaList();
        handletraineeAddToCourse();
    }

    //TODO Muss in haupt controller übergeben werden
    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public void setLabels() {
        traineeID.setText("TraineeID : " + trainee.getTraineeID());
        firstNameTraineeForm.setText("Firstname: " + trainee.getFirstName());
        lastNameTraineeForm.setText("Lastname: " + trainee.getLastName());
        birthdayTraineeForm.setText("Birthday: " + trainee.getBirthday());
        addressTraineeForm.setText("Address: " + trainee.getAddress());
        emailTraineeForm.setText("Email: " + trainee.getEmail());
        locationTraineeForm.setText("Location: " + trainee.getLocation());
    }

//TODO Überarbeiten mit Guillomes Methoden

    public void setCourseTableView() {
        EnrolledTraineesDaoImpl enrolledTrainees = new EnrolledTraineesDaoImpl();
        List<EnrolledTrainees> enrTr = enrolledTrainees.getEnrolledTraineesByTraineeID(trainee.getTraineeID());

        courseEntered.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures param) {
                return null;
            }
        });
        skalaInCorrespondingCourse.setCellValueFactory(new PropertyValueFactory<>("SkalaName"));

    }


    private void setComboboxCourseList() {
        CourseDaoImpl courses = new CourseDaoImpl();
        Set<Course> courseSet = courses.getAllCourses();
        courseList.setItems(FXCollections.observableArrayList(courseSet));
        courseList.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> p) {

                ListCell<Course> cell = new ListCell<Course>() {
                    protected void updateItem(Course c, boolean b) {
                        super.updateItem(c, b);

                        if (c != null) {
                            setText(c.getCourseName());
                        }
                    }

                };

                return cell;
            }
        });
    }

    private void setComboboxSkalaList() {
        SkalaDaoImpl skala = new SkalaDaoImpl();
        Set<Skala> skalaSet = skala.getAllSkala();
        skalaList.setItems(FXCollections.observableArrayList(skalaSet));
        skalaList.setCellFactory(new Callback<ListView<Skala>, ListCell<Skala>>() {
            @Override
            public ListCell<Skala> call(ListView<Skala> p) {

                ListCell<Skala> cell = new ListCell<Skala>() {
                    protected void updateItem(Skala s, boolean b) {
                        super.updateItem(s, b);

                        if (s != null) {
                            setText(s.getSkalaName());
                        }
                    }

                };

                return cell;
            }
        });
    }


    @FXML
    protected void handletraineeAddToCourse() {
        traineeAddToCourse.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EnrolledTraineesDaoImpl enr = new EnrolledTraineesDaoImpl();
                enr.insertEnrolledTrainees((Course) courseList.getValue(), (Skala) skalaList.getValue(), trainee); //TODO Funktion überprüfen. Casten ist hier sehr ungünstig
            }
        });

    }

}
