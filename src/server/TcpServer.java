package server;

/**
 * Created by Paweł Brzoza on 21.03.2016.
 */

import common.Controller;

import java.io.*;
import java.net.*;

import static java.lang.System.out;

public class TcpServer extends Thread {

    public String inputLine = "0", outputLine;

    int portNumber = 4241;

    public void run() {

        try{

            ServerSocket serverSocket = new ServerSocket(portNumber);

                Socket clientSocket = serverSocket.accept();

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                while(true) {
                    out.println(outputLine);
                    inputLine = in.readLine();
                }

        } catch (IOException e) {
            out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            out.println(e.getMessage());
        }
    }

    public String getInputLine() {
        return inputLine;
    }

    public void setOutputLine(String outputLine) {
        this.outputLine = outputLine;
    }

}
