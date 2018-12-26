package ChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static ChatServer.ServerController.*;

public class ClientHandler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;


        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            addNickToNicknameTable(name);
                            break;
                        }
                    }
                }

                out.println("NAMEACCEPTED");
                writers.add(out);
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        if (writer != out) {
                            writer.println("MESSAGE " + name + ": " + input);
                        }
                    }
                    System.out.println(name +": "+ input);
                    addDataToDataArea(name +": "+ input);
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

}
