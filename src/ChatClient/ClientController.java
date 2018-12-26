package ChatClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {

    private static BufferedReader in;
    private static PrintWriter out;
    private static String name;
    private static TextArea tArea;

    @FXML
    TextArea textArea;
    @FXML
    TextField textField;
    @FXML
    Button sendButton;
    @FXML
    TextField ipAddres;
    @FXML
    TextField port;
    @FXML
    TextField nameTextField;

    @FXML
    public void initialize(){
        tArea = textArea;
    }

    public void send(ActionEvent event){
        textArea.appendText(textField.getText()+"\n");
        String message = textField.getText()+"\n";
        out.println(message);
        textField.setText("");
    }

    public void connectToServer(){
        try {
            Socket socket = new Socket(ipAddres.getText(), Integer.valueOf(port.getText()));
            name = nameTextField.getText();
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            Thread read = new Thread(new ReadFromServer());
            read.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void addMessage(String message){
        tArea.appendText(message+"\n");
    }


    public static class ReadFromServer extends Thread {

        public ReadFromServer() {
        }

        public void run() {

            try {
                // Get message from server.
                while (true) {
                    String line = in.readLine();
                    if (line.startsWith("SUBMITNAME")) {
                        out.println(name);
                    } else if (line.startsWith("NAMEACCEPTED")) {
                        addMessage("Name accepted");
                    } else if (line.startsWith("MESSAGE")) {

                        addMessage(line.substring(8) + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
