import server.IServer;
import server.WebServer;

import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) {
        System.out.println("Prepare server");
        IServer server = new WebServer(8998);
        try {
            server.start();
            System.out.println("server started");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server not started");
        }

    }
}
