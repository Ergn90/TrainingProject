package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.course.Course;
import model.course.CourseDaoImpl;
import model.enrolledTrainees.EnrolledTrainees;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.skala.Skala;
import model.skala.SkalaDaoImpl;
import model.trainee.Trainee;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;


public class ControllerTraineeManager implements Initializable {

    @FXML
    Label lastNameTraineeForm;

    @FXML
    Label firstNameTraineeForm;

    @FXML
    Label birthdayTraineeForm;

    @FXML
    Label addressTraineeForm;

    @FXML
    Label traineeID;

    @FXML
    Label emailTraineeForm;

    @FXML
    Label locationTraineeForm;

    @FXML
    Button traineeAddToCourse;

    @FXML
    ComboBox<Course> courseList;

    @FXML
    ComboBox<Skala> skalaList;

    @FXML
    TableView<EnrolledTrainees> coursesEntered;

    @FXML
    TableColumn skalaInCorrespondingCourse;

    @FXML
    TableColumn courseEntered;

    @FXML
    Button backBTN;



    private Trainee trainee;


    //    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coursesEntered.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }


    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
        setLabels();
        setComboboxCourseList();
        setComboboxSkalaList();
        EnrolledTraineesDaoImpl enrolledTraineesDao = refreshCourseTrainee();
        addDeleteCourseTrainee(enrolledTraineesDao);
    }

    private void addDeleteCourseTrainee(EnrolledTraineesDaoImpl enrolledTraineesDao) {
        TableColumn<EnrolledTrainees, Void> colBtn = new TableColumn("Delete Trainee");

        Callback<TableColumn<EnrolledTrainees, Void>, TableCell<EnrolledTrainees, Void>> cellFactory = new Callback<TableColumn<EnrolledTrainees, Void>, TableCell<EnrolledTrainees, Void>>() {
            @Override
            public TableCell<EnrolledTrainees, Void> call(final TableColumn<EnrolledTrainees, Void> param) {
                final TableCell<EnrolledTrainees, Void> cell = new TableCell<EnrolledTrainees, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Trainee trainee = coursesEntered.getItems().get(getIndex()).getTrainee();
                            Course course = coursesEntered.getItems().get(getIndex()).getCourse();
                            Skala skala = coursesEntered.getItems().get(getIndex()).getSkala();
                            boolean deleteEnrolled = enrolledTraineesDao.deleteEnrolledTrainees(trainee.getTraineeID(), skala.getSkalaId(),course.getCourseID());
                            refreshCourseTrainee();
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

        coursesEntered.getColumns().add(colBtn);
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

    public EnrolledTraineesDaoImpl refreshCourseTrainee() {
        EnrolledTraineesDaoImpl enrolledTrainees = new EnrolledTraineesDaoImpl();
        List<EnrolledTrainees> enrolledTraineeList = enrolledTrainees.getEnrolledTraineesByTraineeID(trainee.getTraineeID());
        coursesEntered.setItems(FXCollections.observableArrayList(enrolledTraineeList));

        courseEntered.setCellValueFactory(new PropertyValueFactory<>("course"));
        skalaInCorrespondingCourse.setCellValueFactory(new PropertyValueFactory<>("skala"));
        return enrolledTrainees;
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
                            setText(c.toString());
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

        EnrolledTraineesDaoImpl enr = new EnrolledTraineesDaoImpl();
        enr.insertEnrolledTrainees(courseList.getValue(), skalaList.getValue(), trainee);
        refreshCourseTrainee();//TODO if null
    }

    @FXML
    public void backToTrainee(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/TraineesView.fxml"));
          //  backBTN.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../view/back.png"))));
            backBTN.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

}
