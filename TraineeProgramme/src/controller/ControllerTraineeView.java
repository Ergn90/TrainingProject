package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerTraineeView implements Initializable {

    ////////TraineesView//////////////////

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

    @FXML
    Button addTrainee;

    @FXML
    Button deleteTrainee;

    @FXML
    Button manaageTrainee;

    @FXML
    TextField searchFieldTrainees;

    @FXML
    Button searchButtonTrainees;

    @FXML
    BorderPane mainBorderPane;

    @FXML
    Button courseView;


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
                            boolean deleteEnrolled = enrolledTraineesDao.deleteEnrolledTraineesByTrainee(trainee.getTraineeID());
                            boolean deleteTrainee = traineeDao.deleteTrainee(trainee.getTraineeID());
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

    @FXML
    public void showCoursesDialog(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseView.fxml"));

            courseView.getScene().setRoot(loader);
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }

    @FXML
    public void showManageTrainee(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/TraineeManager.fxml"));

            manaageTrainee.getScene().setRoot(loader);
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }


    @FXML
    public void showTraineeForm(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/TraineeForm.fxml"));

            addTrainee.getScene().setRoot(loader);
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }
}
