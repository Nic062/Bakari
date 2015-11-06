package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {
	
	private ServerSocket serverSocket;
	private int nbConnexion = 4;
	private String[] message;
	private int nbMsg;
	
	public GameServer() {
		message=new String[1000];
		nbMsg=0;
	}
	
	public void enregistrementService(int port){
		try {
			serverSocket = new ServerSocket(port,nbConnexion);
			System.out.println("Le serveur est à l'écoute du port "+serverSocket.getLocalPort());
			while(!serverSocket.isClosed()) {
				Thread t = new Thread(new LeThread(serverSocket.accept(), this));
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		//nouvelleConnexion();
		//nouvelleConnexion();

	}
	
	private int nbConnecte = 0;
	public Socket nouvelleConnexion(){
		System.out.println("Serveur en attente d’une connexion...");
		Socket connexion = null;
		try {
			connexion = serverSocket.accept();
			System.out.println("Nouvelle connexion !");
			nbConnecte++;
			if (nbConnecte>=2){
				new LeThread(connexion, this).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connexion;
	}
	
	public void close() {
		try {
			serverSocket.close();
			System.out.println("fermeture serveur");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.interrupt();
		
	}
	
	
	private static class LeThread extends Thread{
		Socket socket;
		GameServer gameServer;
		public LeThread (Socket socket, GameServer gameServer){
			this.socket = socket;
			this.gameServer=gameServer;
			System.out.println("nouveau thread !");
		}
		
		public void run() {

				System.out.println("ok");
		        try{
		            BufferedReader buff = fluxEntrant(); 
		            PrintWriter print = fluxSortant(); 
		            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		            boolean fin = false;
		            boolean tour = true;
		            String message="";
		            while(!fin){
		            	if(tour){
		            		System.out.println("Client : ");
		                    message = clavier.readLine();
		                    print.println(message);
		                    print.flush();
		                    if(message.compareTo("fin")==0)
		                        fin = true;
		                    
		                           
		                }else{
		                           
			                System.out.println("Serveur >");
			                message = buff.readLine();
			                System.out.println(message);
		                }
		                tour = !tour;
		            }
		            socket.close();
		        }catch(Exception e){
		                System.exit(1);
		               
		        }
			
		}
		
		public BufferedReader fluxEntrant(){
			try {
				return new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		public PrintWriter fluxSortant(){
	        try{
	            return new PrintWriter(socket.getOutputStream());
	        }catch(Exception e){
	            System.exit(1);
	            return null;
	        }
		}
		
		
	}

}
