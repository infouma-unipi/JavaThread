package ABN;

import java.io.*;
import java.net.*;

class ABNServer extends Thread {
	//inizializziamo socket, reader e writer

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	public static int c = 1; //contatore dei server
	public ABNServer(Socket sock) throws IOException {
		socket = sock;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
		out = new PrintWriter(new BufferedWriter(osw), true);
		start();
	}

	public void run() {
		try {
			//se ci sono meno di cinque (inclusi) client connessi, parte...

			if (c <= 5) {
				System.out.println(" ");
				System.out.println("Connesso a: "+ socket);
				System.out.println("Client attualmente connessi: "+ c);
				c++; //... e il contatore aumenta di uno
				System.out.println();
				
				//lettura dei numeri inviati dal client (come line, quindi li convertiamo in int)
				int n = Integer.parseInt(in.readLine());
				int a = Integer.parseInt(in.readLine());
				int b = Integer.parseInt(in.readLine());

				
				//generazione dei numeri casuali, nel caso A > B e nel caso B < A
				//la casistica in cui A e B sono uguali è irrilevante, può essere implicita in ciascuno dei due casi
				//(qui nel secondo)
				for(int i = 0; i < n; i++) {
					int numero;
					if (a>b) {
						numero = (int) ((Math.random() * (a+1 - b)) + b);
					} else {
						numero = (int) ((Math.random() * (b+1 - a)) + a);
					}
					
					//attesa di un minuto
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//invia il numero al client

					out.println(numero);
				}
				//dopo aver inviato tutti i numeri al client, decrementa il contatore dei client connessi

				c--;
				System.out.println("Client sconnesso.");
			} else {
				//se i client sono già > 5
				System.out.println("Server sovraccarico. Riprova più tardi.");
			}
		} catch (IOException e) {
			System.out.println("Errore di connessione");
		}
		//sconnette il client
		try {
			socket.close();
		} catch(IOException e) {}
	}
} 

public class ABNServerMain {
	static final int PORT = 4242;
	public static void main(String[] args) throws IOException {
		//poiché vogliamo sia sempre attivo, il serverSocket non viene chiuso
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server inizializzato");
		System.out.println(serverSocket);
		System.out.println("Client supportati in contemporanea: 5");
		while(true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println(" ");
			try {
				new ABNServer(clientSocket);
			} catch(IOException e) {
				System.out.println("Errore di connessione");
				clientSocket.close();
			}
		}		
	}
}
