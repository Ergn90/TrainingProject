package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TraineesView.fxml"));
        primaryStage.getIcons().add(new Image("https://www.ejw-bernhausen.de/wp-content/uploads/2018/05/Trainee-Logo-blau.png"));
        primaryStage.setTitle("TraineeProgram");
        primaryStage.setScene(new Scene(root, 1100, 700 ));
        primaryStage.show();

    }

    //TODO Verbindung allen Controllers hier
    public static void main(String[] args) {
        launch(args);
    }
}