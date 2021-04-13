package Vino;

class botte {
	public int posti;
	public int litri;
	public botte() {
		posti = 3;
		litri = 250;
	}

	public synchronized void entra() {

		degustatore tizio = (degustatore) Thread.currentThread();	

		//all'inizio di ogni giro tutti i thread vengono svegliati, viene decrementato di uno il contatore dei posti liberi,
		//viene eseguito un math.random per calcolare quanti litri bevuto, viene aumentato di uno il contatore delle bevute dei thread (opzionale, v. nota)
		//e il flag haBevuto viene settato a true, per comunicare con degustatore.java
		if (posti > 0 && litri > 0) {
			notifyAll();
			posti--;
			litri = litri - (int) (Math.random() * (10 - 1) + 1);
			System.out.println(tizio.getName() + " degusta il suo vino. Rubinetti liberi: " + this.posti);
			tizio.bevute++;
			tizio.haBevuto = true;		
			if (litri > 0) {
				System.out.println("Rimangono " + litri + " litri.");

			} else {
				System.out.println(tizio.getName()+ " ha sgolato l'ultima goccia. Si chiude!");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				
				//OPZIONALE (v.nota): per la lista delle bevute stampiamo prima di tutto il nome dell'ultimo che ha bevuto, ancora in memoria...
				System.out.println("La Federazione Italiana Sommelier ringrazia tutti per la partecipazione.");
				System.out.println("E, soprattutto, per le belle bevute. Ricordiamole:");
				System.out.println("Bevute totali di " + tizio.getName() + ": " + tizio.getBevute());		
			}
			try {
				wait();
			} catch (InterruptedException e) {e.printStackTrace();}
		} else {
			System.out.println(tizio.getName()+" aspetta con il languorino in bocca il suo turno.");
			try {
				wait();
			} catch (InterruptedException e) {e.printStackTrace();}
		} 				
	}


	//i posti liberi vengono aumentati di uno
	//il flash haBevuto settato a false, per permettere di rifare il giro
	
	public synchronized void esce() { 

		degustatore tizio = (degustatore) Thread.currentThread();
		System.out.println(tizio.getName()+" ha finito di degustare il suo vinello e se ne va via barcollando felice.");
		posti++;
		tizio.haBevuto = false;		
		try {
			wait(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void stampaBevute() { 
		//per finire la lista dei bevitori (v. nota), facciamo entrare i thread in un ultimo ciclo
		degustatore tizio = (degustatore) Thread.currentThread();

		System.out.println("Bevute totali di " + tizio.getName() + ": " + tizio.getBevute());		
		if (tizio.getBevute() == 0) {
			System.out.println("(Eh, ci spiace. Toccherà a te guidare stanotte.)");		
		} else if (tizio.getBevute() > 15) {
			System.out.println("(Per la barba di Bacco! Finirai in coma etilico!)");		
		}
	}
}