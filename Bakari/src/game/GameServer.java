package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class GameServer extends Thread {
	
	private int port;
	private ServerSocket serverSocket;
	private List<Thread> gameServerThreadList;
	
	public GameServer(int port) {
		this.port = port;
		this.gameServerThreadList = new LinkedList<Thread>();
		this.start();
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Serveur lancé (port : " + port + ")");
			while(!serverSocket.isClosed()) {
				Thread t = new Thread(new GameServerThread(serverSocket.accept()));
				gameServerThreadList.add(t);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		for(Thread gst : gameServerThreadList) {
			System.out.println("fermeture : " + gst.getName());
			gst.interrupt();
		}
		System.out.println("fermeture : ct");
		this.interrupt();
		try {
			serverSocket.close();
			System.out.println("fermeture serveur");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class GameServerThread implements Runnable {
		
		private int nbClient = 0;
		Socket socket;
		BufferedReader bufferedReader;
		
		public GameServerThread(Socket socket) {
			this.socket = socket;
			try {
				this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			String message = "";
		    System.out.println("Un nouveau client s'est connecte, no " + nbClient);
		    try {
		    	char charCur[] = new char[1];
		    	while(bufferedReader.read(charCur, 0, 1)!=-1) {
		    		if (charCur[0] != '\u0000' && charCur[0] != '\n' && charCur[0] != '\r')
		    			message += charCur[0];
		    		else if(!message.equalsIgnoreCase("")) {
		    			if(charCur[0]=='\u0000')
		    				System.out.println(message+""+charCur[0]);
		    			else System.out.println(message);
		    			message = "";
		    		}
		    	}
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		    finally 
		    {
		      try {
		    	  System.out.println("Le client no "+nbClient+" s'est deconnecte");
		    	  socket.close();
		      }
		      catch (IOException e) {
		    	  e.printStackTrace();
		      }
		    }
		}
	}
}
