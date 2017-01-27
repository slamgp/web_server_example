package manager;

        import java.io.*;
        import java.net.Socket;
        import java.util.Date;

/**
 * Created by evgenyp on 1/23/2017.
 */
public class SocketManager implements Runnable {
    Socket socket;

    public  SocketManager(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        if (socket == null) {
            return;
        }
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            createResponce(new DataInputStream(in), new DataOutputStream(out));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR TO READ");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createResponce(DataInputStream in, DataOutputStream out) throws IOException {
        byte [] buff = new byte[1024];
        in.read(buff);
        String request = new String(buff);
        request = request.trim();
        out.writeBytes(createHTTPResponce(request));
        out.flush();

    }

    private String createHTTPResponce(String request) {
        String responce = "";
        String responceBody = createResponceBody();
        responce += createHTTPHeader(responceBody.length()-1);
        responce += responceBody;
        return responce;
    }

    private String createHTTPHeader( int responceBodyLenght) {

        return "\nHTTP/1.1 200 OK" +
               "\nServer: Panchenko WEB SERVER" +
                "\nLast-Modified: " + new Date() +
                "\nContent-Type: text/html; charset=UTF-8" +
                "\nAccept-Ranges: bytes" +
                "\nDate: " +  new Date() +
                "\nContent-Length: " + responceBodyLenght +
                "\nProxy-Connection:Keep-Alive" +
                "\n";
    }

    private String createResponceBody() {
        return "\nMy responce: Hello my friend! TIME: " + new Date();
    }
}
