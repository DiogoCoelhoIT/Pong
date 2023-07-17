package academiadecodigo.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

public Client(String host, int portNumber)
{
    try {
        client = new Socket(host, portNumber);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    } catch (IOException e) {
        System.out.println("Error creating client socket");
        throw new RuntimeException(e);
    }


}
public String receiveMessage() throws IOException {
        String received ="";
        StringBuilder fullMessage = new StringBuilder();
        while ((received=in.readLine())!=null)
        {
            fullMessage.append(received+"\n");
            if(!in.ready())
            {
                break;
            }
        }
        System.out.println("Client received: "+fullMessage);
        return fullMessage.toString();
    }
    public void sendMessage(String send)
    {
        System.out.println("Client send: "+send);
        out.println(send);
    }

    public void closeConnection()
    {

        try {
            out.close();
            in.close();
            client.close();
            System.out.println("Connection Closed");
        } catch (IOException e) {
            System.out.println("Error closing client connection");
            throw new RuntimeException(e);
        }
    }
}
