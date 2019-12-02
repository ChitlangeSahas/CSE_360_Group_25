package main;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;

public class Main extends Application {
    private Scene prev_scene;
    private ArrayList<Grade> data_al = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // entry screen
        FXMLLoader entry_screen_loader = new FXMLLoader();
        entry_screen_loader.setLocation(getClass().getResource("entry_screen.fxml"));
        Parent entry_screen_root = entry_screen_loader.load();
        primaryStage.setTitle("Grade Analytics Tool");
        Scene entry_screen_scene = new Scene(entry_screen_root, 600, 352);

        // settings screen
        FXMLLoader settings_screen_loader = new FXMLLoader();
        settings_screen_loader.setLocation(getClass().getResource("settings_screen.fxml"));
        Parent settings_screen_root = (Parent) settings_screen_loader.load();
        primaryStage.setTitle("Grade Analytics Tool");
        Scene settings_screen_scene = new Scene(settings_screen_root, 600, 352);

        // data screen
        FXMLLoader data_screen_loader = new FXMLLoader();
        data_screen_loader.setLocation(getClass().getResource("data_screen.fxml"));
        Parent data_screen_root = (Parent) data_screen_loader.load();
        primaryStage.setTitle("Grade Analytics Tool");
        Scene data_screen_scene = new Scene(data_screen_root, 625, 352);

        // analysis screen
        FXMLLoader analysis_screen_loader = new FXMLLoader();
        analysis_screen_loader.setLocation(getClass().getResource("analysis_screen.fxml"));
        Parent analysis_screen_root = (Parent) analysis_screen_loader.load();
        primaryStage.setTitle("Grade Analytics Tool");
        Scene analysis_screen_screen = new Scene(analysis_screen_root, 680, 352);

        // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-ENTRY SCREEN HANDLERS-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-//
        // handler for when enter manually image is clicked
        ImageView enter_manually_img = (ImageView) entry_screen_scene.lookup("#enter_manually_img");
        enter_manually_img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(data_screen_scene);
                primaryStage.show();
                prev_scene = entry_screen_scene;
            }
        });

        // handler for when add file image is clicked
        ImageView add_file_img = (ImageView) entry_screen_scene.lookup("#add_file_img");
        add_file_img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // prompt for file
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt")
                        , new FileChooser.ExtensionFilter("CSV Files", "*.csv")
                );
                File selectedFile = fileChooser.showOpenDialog(new Stage());

                // TODO Add data to the tableview

                FileUtils fileUtils = new FileUtils();
                try {
                    // clear the data and add new.
                    data_al.clear();
                    data_al = fileUtils.file_to_tokens(selectedFile);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                System.out.println(data_al.size());

                    TableColumn columnTitle = new TableColumn<>("columnTitle");
                    TableColumn columnQuantity = new TableColumn<>("columnQuantity");
                    TableColumn columnUUID = new TableColumn<>("columnUUID");

                    TableView<Float> tableBookList = (TableView<Float>) data_screen_scene.lookup("#table_tb");
                    ObservableList<Float> table_data = FXCollections.<Float>observableArrayList();
                    ArrayList<Float> data_al_float = new ArrayList<Float>();
                    for (String element : data_al)
                    {
                        data_al_float.add(Float.parseFloat(element));
                    }

                    table_data.addAll();

                    columnTitle.setCellValueFactory(new PropertyValueFactory<Grade, Float>("column_one"));
                    columnQuantity.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("column_two"));
                    columnUUID.setCellValueFactory(new PropertyValueFactory<Grade, String>("column_three"));

                    tableBookList.setItems(table_data);

                    // This is working but adding three new columns every time :(
                    tableBookList.getColumns().addAll(columnTitle, columnQuantity, columnUUID);


                prev_scene = entry_screen_scene;
                primaryStage.setScene(data_screen_scene);
                primaryStage.show();
            }
        });

        // handler for when settings image is clicked
        ImageView settings_img = (ImageView) entry_screen_scene.lookup("#settings_img");
        settings_img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // show settings screen
                TextField left_boundary_tf = (TextField) settings_screen_scene.lookup("#grade_left_boundary");
                TextField right_boundary_tf = (TextField) settings_screen_scene.lookup("#grade_right_boundary");

                left_boundary_tf.setText(String.valueOf(Settings.GRADE_LEFT_BOUNDARY));
                right_boundary_tf.setText(String.valueOf(Settings.GRADE_RIGHT_BOUNDARY));

                prev_scene = entry_screen_scene;
                primaryStage.setScene(settings_screen_scene);
                primaryStage.show();
            }
        });

        // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-SETTINGS SCREEN HANDLERS-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-//
        Button apply_btn = (Button) settings_screen_scene.lookup("#apply_settings_btn");
        apply_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TextField left_boundary_tf = (TextField) settings_screen_scene.lookup("#grade_left_boundary");
                TextField right_boundary_tf = (TextField) settings_screen_scene.lookup("#grade_right_boundary");

                Settings.GRADE_LEFT_BOUNDARY = Integer.parseInt(left_boundary_tf.getText());
                Settings.GRADE_RIGHT_BOUNDARY = Integer.parseInt(right_boundary_tf.getText());

                if (prev_scene == entry_screen_scene) {
                    primaryStage.setScene(entry_screen_scene);
                } else if (prev_scene == data_screen_scene) {
                    primaryStage.setScene(data_screen_scene);
                }
                primaryStage.show();
            }
        });


        // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-DATA SCREEN HANDLERS-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-//
        Button data_scr_settings_btn = (Button) ((ToolBar) data_screen_scene.lookup("#upper_toolbar")).getItems().get(0);
        data_scr_settings_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TextField left_boundary_tf = (TextField) settings_screen_scene.lookup("#grade_left_boundary");
                TextField right_boundary_tf = (TextField) settings_screen_scene.lookup("#grade_right_boundary");

                left_boundary_tf.setText(String.valueOf(Settings.GRADE_LEFT_BOUNDARY));
                right_boundary_tf.setText(String.valueOf(Settings.GRADE_RIGHT_BOUNDARY));

                prev_scene = data_screen_scene;
                primaryStage.setScene(settings_screen_scene);
                primaryStage.show();
            }
        });
        Button data_screen_append_btn = (Button) ((ToolBar) data_screen_scene.lookup("#upper_toolbar")).getItems().get(1);
        data_screen_append_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt")
                        , new FileChooser.ExtensionFilter("CSV Files", "*.csv")
                );
                File selectedFile = fileChooser.showOpenDialog(new Stage());
                prev_scene = data_screen_scene;
            }
        });

        Button data_screen_delete_btn = (Button) data_screen_scene.lookup("#delete_btn");
        data_screen_delete_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Deleting Cell OK");
            }
        });
        Button data_screen_analyze_btn = (Button) data_screen_scene.lookup("#analyze_btn");
        data_screen_analyze_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(analysis_screen_screen);
                primaryStage.show();
                prev_scene = data_screen_scene;
            }
        });
        primaryStage.setScene(entry_screen_scene);
        primaryStage.show();
    }

}
