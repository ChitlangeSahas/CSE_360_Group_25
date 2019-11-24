package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("data_screen.fxml"));
        primaryStage.setTitle("Grade Analytics Tool");
        Scene scene = new Scene(root, 600, 352);
        primaryStage.setScene(scene);


        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
