package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Label label;

    @FXML
    public  void handleAction(){
        label.setText("OK Button pressed");
    }

    @FXML
    private Button ok;

    @FXML
    private Button cancel;

    @FXML
    private Button help;


}
