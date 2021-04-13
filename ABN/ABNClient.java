package ABN;

import java.net.*;
import java.util.Scanner;
import java.io.*;

//questo file può essere runnato più volte
//se runnato prima del server o quando il server ha già cinque client connessi (= cinque istanze di questo file), ci sarà errore di connession

public class ABNClient {

	public static void main(String[] args) {

		PrintWriter out;
		BufferedReader in;

		try{
			//la porta è la stessa del client
			Socket socket=new Socket("127.0.0.1",4242);
			System.out.println("Client inizializzato!");
			System.out.println(socket); 

			//inizializziamo stream di input e output e lo scanner
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			in = new BufferedReader(isr);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			out = new PrintWriter(new BufferedWriter(osw), true);
			Scanner input = new Scanner(System.in);

			//chiediamo all'utente di inserire N...
			System.out.println(" ");
			System.out.println("Salve! Dimmi quanti numeri vuoi.");
			int n = input.nextInt();

			//... e poi A e B
			System.out.println("Dimmi uno dei due estremi...");
			int a = input.nextInt(); 
			System.out.println("... e ora dimmi l'altro");
			int b = input.nextInt();

			//mandiamo al server N, A e B
			out.println(n);
			out.println(a);
			out.println(b);

			System.out.println("Ecco " + n + " numeri compresi fra " + a + " e " + b);             
			
			//il server deve darci N numeri, quindi leggiamo quanto riceviamo dal server N volte e lo stampiamo a schermo
			//(dal server riceviamo N line, che convertiamo quindi in int)
			for(int i = 0; i < n; i++) {
				int numero = Integer.parseInt(in.readLine()); 
				System.out.println(numero);
			}        

			System.out.println(" ");      
			System.out.println("Finito! Grazie per aver utilizzato questo programma.");             
			
			input.close();
		} catch (IOException e) {
			System.out.println ("Impossibile effettuare la connessione.");
		}
	}
}