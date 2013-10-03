package server;

import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {
	
	private Socket socket = null;

	public MultiServerThread(Socket socket) {
		super("MultiServerThread");
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// Create the input and output streams.
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine; 
			String outputLine = "Version 1";
			output.println(outputLine);

			// Listen to the client until the break command appear.
			while ((inputLine = input.readLine()) != null) {
				if (inputLine.equals("break")) {
					output.println("break");
					break;
				}else{
					outputLine = handleInput(inputLine);
					output.println(outputLine);
				}
			}
			
			// Close all the streams and the socket.
			output.close();
			input.close();
			socket.close();

		} catch (Exception e) {
			return;
		}
	}
	
	private String handleInput(String input) {
		StringBuilder sb = new StringBuilder();
		for(int i = input.length() - 1; i >= 0; i--) {
			sb.append(input.charAt(i));
		}
		return sb.toString();
	}
}