package prj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by McGuireMW on 4/20/14.
 */
public class Consumer extends Thread {
    int portNumber;
    private StringResourceServer server;

    public Consumer(int clientPort) {
        try {
            if (clientPort == 6650) {
                server = new StringResourceServer();
                while (true) {
                    try (
                            Socket client = new Socket();
                            OutputStream outToServer = client.getOutputStream();
                            DataOutputStream out = new DataOutputStream(outToServer);
                    ) {
                        out.writeUTF("Requesting from Thread from server");
                        InputStream inFromServer = client.getInputStream();
                        DataInputStream in = new DataInputStream(inFromServer);
                        System.out.println("<CONSUMED>: " + in.toString());
                    } catch (Exception e) {
                        System.err.println("Error when receiving message from Server" + e);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
