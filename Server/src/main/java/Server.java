import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server has started");
        System.out.println("Waiting for a client...");
        while(true) {
            Socket client = server.accept();
            new Thread(()->{
                try(Socket socket = client) {
                    ServerController serverController = new ServerController(socket);
                    serverController.definePage();
                }catch(Exception t){
                    System.out.println("error " + t.toString());
                }
            }).start();
        }
    }

}
