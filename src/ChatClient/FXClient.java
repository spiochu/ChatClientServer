package ChatClient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;


public class FXClient extends Application{



    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
