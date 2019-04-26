package controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import jdk.nashorn.internal.codegen.CompilerConstants;
import model.enrolledTrainees.EnrolledTrainees;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.location.Location;
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


    /////////////////////////////////////////////////////////
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
    TableColumn updateTrainee1;

    @FXML
    TableColumn deleteTrainee1;

    ///////////////////////////////////////////////////////////
    @FXML
    Button addTrainee;

    @FXML
    Button deleteTrainee;

    @FXML
    Button updateTrainee;
    ///////////////////////////////////////////////////////////
    @FXML
    TextField searchFieldTrainees;

    @FXML
    Button searchButtonTrainees;

    //////////////////////////////////////CourseForm Annotations
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

    ///////////////////////////////CourseView
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
    Label enrolledTraineesC;

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTraineeInfo();

//       listManageTraineeProgram.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);      //nur eine Zeile selektieren
    }

    public void getTraineeInfo() {
        TraineeDao traineeDao = refreshTraineeInfo();
        addDeleteButtonToTable(traineeDao);
        addUpdateButtonToTable(traineeDao);

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


    private void addDeleteButtonToTable(TraineeDao traineeDao) {
        TableColumn<Trainee, Void> colBtn = new TableColumn("Delete Trainee");

        Callback<TableColumn<Trainee, Void>, TableCell<Trainee, Void>> cellFactory = new Callback<TableColumn<Trainee, Void>, TableCell<Trainee, Void>>() {
            @Override
            public TableCell<Trainee, Void> call(final TableColumn<Trainee, Void> param) {
                final TableCell<Trainee, Void> cell = new TableCell<Trainee, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            EnrolledTraineesDao enrolledTraineesDao = new EnrolledTraineesDaoImpl();
                            Trainee trainee = tableTrainees.getItems().get(getIndex());
                            //TODO enrolledTraineesDao make delete with Trainee and delete  with Course
                            boolean deleteTrainee = traineeDao.deleteTrainee(trainee.getTraineeID());
                            System.out.println("delete: " + deleteTrainee);
                            refreshTraineeInfo();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableTrainees.getColumns().add(colBtn);
    }
    private void addUpdateButtonToTable(TraineeDao traineeDao) {
        TableColumn<Trainee, Void> colBtn = new TableColumn("Update Trainee");

        Callback<TableColumn<Trainee, Void>, TableCell<Trainee, Void>> cellFactory = new Callback<TableColumn<Trainee, Void>, TableCell<Trainee, Void>>() {
            @Override
            public TableCell<Trainee, Void> call(final TableColumn<Trainee, Void> param) {
                final TableCell<Trainee, Void> cell = new TableCell<Trainee, Void>() {

                    private final Button btn = new Button("Update");

                    {
                        //TODO call DIna forumlar
                        btn.setOnAction((ActionEvent event) -> {
//                            EnrolledTraineesDao enrolledTraineesDao = new EnrolledTraineesDaoImpl();
//                            Trainee trainee = tableTrainees.getItems().get(getIndex());
//                            boolean updateTrainee = traineeDao.updateTrainee(trainee);
//                            System.out.println("update Trainee " + updateTrainee);
//                            refreshTraineeInfo();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableTrainees.getColumns().add(colBtn);
    }
}
