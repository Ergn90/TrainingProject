package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ////////TraineesView//////////////////
    @FXML
    ListView listViewTrainees;

    @FXML
    Label manageTrainee;

    @FXML
    Label manageCourses;

    @FXML
    Label enrolledTrainees;
    /////////////////////////////////////////////////////////
    @FXML
    TableView tableTrainees;

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
    TableColumn location;

    @FXML
    TableColumn skalaTrainee;

    @FXML
    TableColumn traineeCurentCourse;

    @FXML
    TableColumn modifyTrainee;

///////////////////////////////////////////////////////////
    @FXML
    Button addTrainee;

    @FXML
    Button addCourse1;

    @FXML
    Button enrollTrainee;
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
    /////////////////////////Trainee Form

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
    Label scaleTraineeId;


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
    TextField locationTrainee;

    @FXML
    TextField scaleTrainee;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        listManageTraineeProgram.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);      //nur eine Zeile selektieren
    }


}
