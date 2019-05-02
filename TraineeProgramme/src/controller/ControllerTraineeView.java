package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerTraineeView implements Initializable {



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
    Button addTrainee;

    @FXML
    Button manaageTrainee;

    @FXML
    TextField searchFieldTrainees;

    @FXML
    BorderPane mainBorderPane;

    @FXML
    Button courseView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTraineeInfo();
        searchForTrainee();
        tableTrainees.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    private void getTraineeInfo() {
        TraineeDao traineeDao = refreshTraineeInfo();
        addDeleteButtonToTable(traineeDao);
        addUpdateButtonToTable(traineeDao);
    }

    private TraineeDao refreshTraineeInfo() {
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
                        btn.setOnAction((ActionEvent event) -> {

                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/TraineeForm.fxml"));
                                Parent root = (Parent) fxmlLoader.load();
                                ControlleTraineeForm controller = fxmlLoader.getController();
                                controller.setTrainee(tableTrainees.getItems().get(getIndex()));
                                controller.addNewTrainee.setOnAction(event1 -> controller.updateTrainee());
                                btn.getScene().setRoot(root);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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

    private void searchForTrainee() {
        TraineeDao traineeDao = new TraineeDaoImpl();
        Set<Trainee> trainees = traineeDao.getAllTrainee();
        ObservableList<Trainee> traineeList = FXCollections.observableArrayList(trainees);
        FilteredList<Trainee> filteredData = new FilteredList<>(traineeList, c -> true);

        searchFieldTrainees.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(trainee -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                LocalDate dateBirthday= trainee.getBirthday();

                if (trainee.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (trainee.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (trainee.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (trainee.getSchool().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (trainee.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (trainee.getLocation().getLocationName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (dateBirthday.toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Trainee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableTrainees.comparatorProperty());
        tableTrainees.setItems(sortedData);
    }

    @FXML
    public void showCoursesDialog(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseView.fxml"));

            courseView.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

    @FXML
    public void showManageTrainee(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/TraineeManager.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            ControllerTraineeManager controller = fxmlLoader.getController();
            if(isTraineeSelected()){
                Trainee trainee= tableTrainees.getSelectionModel().getSelectedItem();
                controller.setTrainee(trainee);
                manaageTrainee.getScene().setRoot(root);
            }else{
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please select a trainee in the list ");
                alert.show();
            }

        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

    private boolean isTraineeSelected() {
       return tableTrainees.getSelectionModel().getSelectedItem()!=null;
    }

    @FXML
    public void showTraineeForm(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/TraineeForm.fxml"));

            addTrainee.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

}
