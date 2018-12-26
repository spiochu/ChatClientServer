package ChatServer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


import java.io.IOException;

import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;

import java.net.UnknownHostException;
import java.util.HashSet;

public class ServerController {
    public static int PORT = 9001;


    public static HashSet<String> names = new HashSet<String>();

    public static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    private static TextArea nTable;
    private static TextArea dArea;

    @FXML
    TextArea dataArea;

    @FXML
    TextArea nicknameTable;

    @FXML
    Label ipLabel;

    @FXML
    TextField port;

    @FXML
    private void initialize(){
        setIpLabel();
        nTable = nicknameTable;
        dArea = dataArea;
    }
    public static void addDataToDataArea(String data){
        dArea.appendText(data+"\n");

    }

    public static void addNickToNicknameTable(String nickname){
        nTable.appendText(nickname+"\n");
    }

    public void setIpLabel(){
        try {
            String ip = Inet4Address.getLocalHost().getHostAddress();
            ipLabel.setText(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void startServer(){
        PORT = Integer.valueOf(port.getText());
        ServerThread serverThread = new ServerThread();
        serverThread.start();

    }

}
