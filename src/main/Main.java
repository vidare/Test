package main;

import java.io.*;
import java.net.*;
 
public class Main {
	
    public static void main(String[] args) throws IOException {
 
        Socket socket = null;
        PrintWriter output = null;
        BufferedReader input = null;
        
//      String host = "192.168.0.110";	// Private IP
    	String host = "176.10.217.200";	// Public IP
//    	String host = "localhost";		// Local machine
    	int port = 10000;
 
        try {     	
        	socket = new Socket(host, port);
        	
        	// Create the input and output streams.
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host: " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not get the IO streams from: " + host);
            System.exit(1);
        }
 
        // Read from the console for now.
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromClient;

        // Listen to the server until the break command appear.
        while ((fromServer = input.readLine()) != null) {
        	System.out.println("Server: " + fromServer);
        	if (fromServer.equals("break"))
        		break;

        	fromClient = stdIn.readLine();
        	if (fromClient != null) {
        		System.out.println("Client: " + fromClient);
        		output.println(fromClient);
        	}
        }

        // Close everything!s
        output.close();
        input.close();
        stdIn.close();
        socket.close();
    }
}