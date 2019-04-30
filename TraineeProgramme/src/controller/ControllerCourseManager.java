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
import model.enrolledTrainees.EnrolledTrainees;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.skala.Skala;
import model.skala.SkalaDaoImpl;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerCourseManager  implements Initializable {

    @FXML
    Label courseID;
    @FXML
    Label courseName;
    @FXML
    Label courseRoom;
    @FXML
    Label courseDescription;
    @FXML
    Label courseStartDate;
    @FXML
    Label courseEndDate;
    @FXML
    TableView<EnrolledTrainees> tableTraineesInCourse;
    @FXML
    TableColumn columnTraineeID;
    @FXML
    TableColumn columnTraineeName;
    @FXML
    TableColumn columnSkala;
    @FXML
    Button addTraineeToCourse;
    @FXML
    ComboBox<Trainee> traineeBox;
    @FXML
    ComboBox<Skala> skalaList;
    @FXML
    Button backBTN;

    private Course course;

    public void setCourse(Course course) {
        this.course=course;
        setLabelsInformation();
        setComboboxSkalaList();
        setComboboxTraineeList();
        EnrolledTraineesDaoImpl enrolledTraineesDao = refreshCourseTrainee();
        addDeleteCourseTrainee(enrolledTraineesDao);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLabelsInformation() {
        courseID.setText("CourseID : " + course.getCourseID());
        courseName.setText("Coursename: "+ course.getCourseName());
        courseStartDate.setText("Course Startdate: "+ course.getStartDate());
        courseEndDate.setText("Course Enddate: "+course.getEndDate());
        courseRoom.setText("Courseroom: "+course.getCourseRoom());
        courseDescription.setText("Course Description: " + course.getCourseDescription());
    }

    public EnrolledTraineesDaoImpl refreshCourseTrainee() {
        EnrolledTraineesDaoImpl enrolledTrainees = new EnrolledTraineesDaoImpl();
        List<EnrolledTrainees> enrolledTraineeList = enrolledTrainees.getEnrolledTraineesByTraineeID(course.getCourseID());
        tableTraineesInCourse.setItems(FXCollections.observableArrayList(enrolledTraineeList));

        columnTraineeName.setCellValueFactory(new PropertyValueFactory<>("trainee"));
        columnSkala.setCellValueFactory(new PropertyValueFactory<>("skala"));
        return enrolledTrainees;
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
                            Trainee trainee = tableTraineesInCourse.getItems().get(getIndex()).getTrainee();
                            Course course = tableTraineesInCourse.getItems().get(getIndex()).getCourse();
                            Skala skala = tableTraineesInCourse.getItems().get(getIndex()).getSkala();
                            boolean deleteEnrolled = enrolledTraineesDao.deleteEnrolledTrainees(trainee.getTraineeID(), course.getCourseID(), skala.getSkalaId());
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

        tableTraineesInCourse.getColumns().add(colBtn);
    }

    private void setComboboxTraineeList(){
        TraineeDao traineeDao = new TraineeDaoImpl();
        Set<Trainee> traineeSet = traineeDao.getAllTrainee();
        traineeBox.setItems(FXCollections.observableArrayList(traineeSet));
        traineeBox.setCellFactory(new Callback<ListView<Trainee>, ListCell<Trainee>>() {
            @Override
            public ListCell<Trainee> call(ListView<Trainee> p) {

                ListCell<Trainee> cell = new ListCell<Trainee>() {
                    protected void updateItem(Trainee c, boolean b) {
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


    private void setComboboxSkalaList(){
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
        enr.insertEnrolledTrainees(course,  skalaList.getValue(), traineeBox.getValue());

    }

    @FXML
    public void backToTrainee(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseView.fxml"));
         //   backBTN.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../view/back.png"))));
            backBTN.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }
}
