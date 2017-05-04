package client;

/**
 * Created by Paweł Brzoza on 21.03.2016.
 */

import java.io.*;
import java.net.*;

public class TcpClient extends Thread {

    public String outputLine, inputLine, hostName = "0";

    public int portNumber = 4241;

    public boolean running = false;

    public TcpClient(String text) {
        this.hostName = text;
        inputLine = "";
    }

    public void run() {

            try {

                Socket clientSocket = new Socket(hostName, portNumber);

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                while(true) {

                    inputLine = in.readLine();
                    out.println(outputLine);
                }

                    //System.out.println("[CLIENT] : wiadomosc z serwera: " + inputLine);

            } catch (UnknownHostException e) {
                System.err.println("Don't know about host " + hostName);
                System.exit(1);

            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " + hostName);
                setHostName("0");
                System.exit(1);
            }
        }

    public String getInputLine() {
        return inputLine;
    }

    public void setOutputLine(String outputLine) {
        this.outputLine = outputLine;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

}