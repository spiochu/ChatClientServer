package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerThread extends Thread {

    @Override
    public void run() {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(ServerController.PORT);
            while (true) {
                new ClientHandler(listener.accept()).start();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
