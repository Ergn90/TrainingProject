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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.course.Course;
import model.course.CourseDao;
import model.course.CourseDaoImpl;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerCourseView implements Initializable {

    @FXML
    TextField searchFielCourses;
    @FXML
    Button searchButtonCourses;
    @FXML
    TableColumn courseID;
    @FXML
    TableColumn courseName;
    @FXML
    TableColumn courseyear;
    @FXML
    TableColumn courseRoom;
    @FXML
    TableColumn courseDescriptionC;

    @FXML
    TableColumn startDate;

    @FXML
    TableColumn endDate;
    @FXML
    Button addCourseC;
    @FXML
    Button courseManage;
    @FXML
    Button backFromCourseViewToTraineeView;
    @FXML
    TableView<Course> coursesTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CourseDao courseDao = refreshCourseInfo();
        searchForCourse();
        addDeleteButtonToTable(courseDao);
        addUpdateButtonToTable(courseDao);
    }


    public CourseDao refreshCourseInfo() {
        CourseDao courseDao = new CourseDaoImpl();
        Set<Course> courses = courseDao.getAllCourses();
        coursesTable.setItems(FXCollections.observableArrayList(courses));

        courseID.setCellValueFactory(new PropertyValueFactory<>("CourseID"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        courseRoom.setCellValueFactory(new PropertyValueFactory<>("CourseRoom"));
        courseDescriptionC.setCellValueFactory(new PropertyValueFactory<>("CourseDescription"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        return courseDao;
    }

    private void addDeleteButtonToTable(CourseDao courseDao) {
        TableColumn<Course, Void> colBtn = new TableColumn("Delete Course");

        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<TableColumn<Course, Void>, TableCell<Course, Void>>() {
            @Override
            public TableCell<Course, Void> call(final TableColumn<Course, Void> param) {
                final TableCell<Course, Void> cell = new TableCell<Course, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            EnrolledTraineesDao enrolledCoursesDao = new EnrolledTraineesDaoImpl();
                            Course course = (Course) coursesTable.getItems().get(getIndex());
                            boolean deleteEnrolled = enrolledCoursesDao.deleteEnrolledTraineesByCourse(course.getCourseID());
                            boolean deleteCourse = courseDao.deleteCourse(course.getCourseID());
                            refreshCourseInfo();

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

        coursesTable.getColumns().add(colBtn);
    }

    private void searchForCourse() {
        CourseDao courseDaoo = new CourseDaoImpl();
        Set<Course> courses = courseDaoo.getAllCourses();
        ObservableList<Course> coursesList = FXCollections.observableArrayList(courses);
        FilteredList<Course> filteredData = new FilteredList<>(coursesList, c -> true);

        searchFielCourses.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(course -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (course.getCourseRoom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } /*else if (course..toLowerCase().contains(lowerCaseFilter)) {
                     return true;*/
                //}
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Course> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(coursesTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        coursesTable.setItems(sortedData);
    }

    @FXML
    public void backToTrainee(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/TraineesView.fxml"));
            backFromCourseViewToTraineeView.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../view/back.png"))));
            backFromCourseViewToTraineeView.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

    private void addUpdateButtonToTable(CourseDao courseDao) {
        TableColumn<Course, Void> colBtn = new TableColumn("Update Course");

        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<TableColumn<Course, Void>, TableCell<Course, Void>>() {
            @Override
            public TableCell<Course, Void> call(final TableColumn<Course, Void> param) {
                final TableCell<Course, Void> cell = new TableCell<Course, Void>() {

                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CourseForm.fxml"));
//                            loader = FXMLLoader.load(getClass().getResource("../view/TraineeForm.fxml"));
                                Parent root = (Parent) fxmlLoader.load();

                           /*     ControllerCourseForm controller = fxmlLoader.getController();
                                controller.setCourse(coursesTable.getItems().get(getIndex()));
                                controller.addNewCourse.setOnAction(event1 -> controller.updateCourse());
                                btn.getScene().setRoot(root);*/

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

        coursesTable.getColumns().add(colBtn);
    }


    @FXML
    public void manageCourse(ActionEvent actionEvent) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CourseManager.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            ControllerCourseManager controller = fxmlLoader.getController();
            if (isCourseSelected()) {
                Course course = coursesTable.getSelectionModel().getSelectedItem();
                controller.setCourse(course);
                courseManage.getScene().setRoot(root);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please select a course in the list ");
                alert.show();
            }
        } catch (
                NullPointerException npe) {
            System.out.println(npe.getMessage());
        }

    }

    private boolean isCourseSelected() {
        return coursesTable.getSelectionModel().getSelectedItem() != null;
    }

    @FXML
    public void addCourse(ActionEvent actionEvent) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../view/CourseForm.fxml"));
            addCourseC.getScene().setRoot(loader);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }


    }
}

