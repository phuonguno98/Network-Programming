package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(1234);
		System.out.println("[SERVER] Listening on port 1234 ... ");
		
		int count = 1;
		while(true) {
			Socket socket = server.accept();
			System.out.println("[SERVER] Client " + count++ + " connected!");
			
			Thread thread = new ClientThread(socket);
			thread.start();
		}
		
	}
}
