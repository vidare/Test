package server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MultiServer {
	
    public static void main(String[] args) throws IOException {
    	
    	List<MultiServerThread> threads = new ArrayList<MultiServerThread>();
        ServerSocket serverSocket = null;
        boolean listening = true;

        // Create a socket to listen to.
        int port = 10000;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Version 2");
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port + ".");
            System.exit(-1);
        }

        // Listening until something connects through the specified port.
        while (listening) {
        	Socket client = serverSocket.accept();
        	MultiServerThread thread = new MultiServerThread(client);
        	thread.start();
        	threads.add(thread);
        	
        	// Remove all the dead threads from the list.
        	int i = 0;
        	while(i < threads.size()) {
        		if(!threads.get(i).isAlive()) {
        			threads.remove(i);
        		}else{
        			i++;
        		}
        	}
        	System.out.println("Current connections: " + threads.size() + ".");
        }

        serverSocket.close();
    }
}