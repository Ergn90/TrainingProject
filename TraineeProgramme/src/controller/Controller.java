package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.courses.Courses;
import model.enrolled_trainees.Enrolled_trainees;
import model.trainee.Trainee;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    ListView  listManageTraineeProgram;

    @FXML
    Label manageTrainees;

    @FXML
    Label manageCourses;

    @FXML
    Label enrolledTrainees;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        listManageTraineeProgram.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);      //nur eine Zeile selektieren
    }


@FXML
    public void traineeManage(){

}

}
