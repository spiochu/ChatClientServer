package ChatServer;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class FXServer extends Application {

    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("serverGui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
