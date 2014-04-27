package prj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by McGuireMW on 4/19/14.
 */
public class StringResourceServer {
    private final int PORT = 6650;
    private Thread stringThread;
    private StringProducer producer;

    public StringResourceServer() {
        try (
                ServerSocket stringServerSocket = new ServerSocket(PORT);
                Socket clientSocket = new Socket();
        ) {
            clientSocket.connect(stringServerSocket.getLocalSocketAddress());
            stringServerSocket.accept();
            producer = new StringProducer();
            producer.run();
            stringThread = producer.getThread();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
