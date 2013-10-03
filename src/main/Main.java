package main;

import java.io.*;
import java.net.*;
 
public class Main {
	
    public static void main(String[] args) throws IOException {
 
        Socket socket = new Socket("176.10.217.200", 10000);
        PrintWriter output = null;
        BufferedReader input = null;
        
        try {     	
        	// Create the input and output streams.
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not get the IO streams from host");
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